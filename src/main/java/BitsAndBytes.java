import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by rquinn on 7/9/17.
 */
public class BitsAndBytes {

    private static final int CHUNK_PRINT_SIZE = 4;

    /*
    Given an input number,convert it to a string with its binary representation, without built­in functions.
    Return a string in groups of 4 bits. Prepend up to three 0s if necessary.
    */
    public static String convertToBinaryString(Integer input) {
        // is there an efficient way to do string prepend?
        // i would prefer to work with right shift so i don't have to discard a bunch of 0s for positive numbers
        String out = "";
        // track the number of characters in the current group, reset when it gets to CHUNK_PRINT_SIZE, and print a space
        int groupCount = 0;
        while (input != 0) {
            if (groupCount > 0 && (groupCount & (CHUNK_PRINT_SIZE - 1)) == 0) {
                out = " " + out;
                groupCount = 0;
            }
            if ((input & 0b1) == 1) {
                out = "1" + out;
            } else {
                out = "0" + out;
            }

            groupCount++;

            // use logical right shift, to backfill with 0s instead of backfilling the sign bit
            // otherwise the while loop would never terminate
            input = input >>> 1;
        }

        // if the string is less than chunk size, just pad to chunk size and return
        while (groupCount < CHUNK_PRINT_SIZE) {
            out = "0" + out;
            groupCount++;
        }

        return out;
    }


    /*
    Given a 64 bit unsigned int.
    Check if Least Significant Bit (LSB) is set
    Check if Most Significant Bit (MSB) is set
    Check if the nth bit is set. LSB=0th bit
    */

    public static boolean isLsbSet(long input) {
        return (input & 0x1) != 0;
    }
    public static boolean isMsbSet(long input) {
        return (input & ((long) (1 << 15))) != 0;
    }
    public static boolean isBitSet(long input, int bitIndex) {
        return (input & (long) (1 << bitIndex)) != 0;
    }


    /*
        Given a 64 bit unsigned int
        Set the nth bit of a number
        Clear the n th bit of a number
        Toggle the n th bit of a number
     */

    public static int clearNthBit(int input, int bitIndex) {
        // need to have all 1s except for a 0 at the bitIndex specified
        // create this by setting a 1 at the appropriate index, and then flipping all bits
        int mask = ~ (1 << bitIndex);
        return input & mask;
    }

    public static int setNthBit(int input, int bitIndex, boolean value) {
        int cleared = clearNthBit(input, bitIndex);
        if (value) {
            return (1 << bitIndex) | cleared;
        }
        return cleared;
    }

    public static int toggleNthBit(int input, int bitIndex) {
        return input ^ (1 << bitIndex);
    }

    /*
    Given a 64 bit unsigned int
    Check if it divisible by 2, 4, 8, 16 and 32
    Count the number of bits that are set to zero and number of bits set to one
    Get the most significant bit that is set of a number
    Get the least significant bit that is set of a number
    */

    /**
     * @param input
     * @param divisibleBy - this must be a power of 2, > 0
     * @return
     */
    public static boolean isDivisibleBy(int input, int divisibleBy) {
        return (input & (divisibleBy >> 1)) == 0;
    }

    public static int numBitsSetToZero(long input) {
        int numZeros = 32;
        // dealing with a 32 bit int, only using the least significant 32 bits of the long input
        if (input == 0) {
            return numZeros;
        }
        // brute force we just start shifting right until the value is 0.
        while (input > 0) {
            if ((input & 0x1) == 1) {
                numZeros--;
            }
            input = input >> 1;
        }
        return numZeros;
    }

    public static int numBitsSetToOne(long input) {
        return 32 - numBitsSetToZero(input);
    }

    /**
     * return the index of the most significant bit
     * @param input
     * @return - returns -1 if input is 0, otherwise returns the index of the most significant bit
     */
    public static int getMostSignificantBit(long input) {
        if (input == 0) {
            return -1;
        }
        long mask = (long) 1 << 31;
        int index = 31;
        while ((mask & input) == 0) {
            mask = mask >> 1;
            index--;
        }
        return index;
    }

    public static int getLeastSignificantBit(long input) {
        if (input == 0) {
            return -1;
        }
        long mask = 1;
        int index = 0;
        while ((mask & input) == 0) {
            mask = mask << 1;
            index++;
        }
        return index;
    }

    public static int countLeadingOnes(long input) {
        if (input == 0) {
            return 0;
        }
        int onesCount = 0;
        long mask = 1L << 31;
        while ((mask & input) != 0) {
            onesCount++;
            mask = mask >> 1;
        }
        return onesCount == 32 ? 0 : onesCount;
    }

    public static int countLeadingZeros(long input) {
        if (input == 0) {
            return 0;
        }
        return 31 - getMostSignificantBit(input);
    }

    public static int countTrailingOnes(long input) {
        int onesCount = 0;
        long mask = 1L;
        while ((mask & input) != 0) {
            onesCount++;
            mask = mask << 1;
        }
        return onesCount;
    }

    public static int countTrailingZeros(long input) {
        if (input == 0) {
            return 0;
        }

        return getLeastSignificantBit(input);
    }

    // Given two numbers, check if their binary representations are anagrams of each other
    public static boolean binaryAnagrams(int first, int second) {
        return numBitsSetToOne(first) == numBitsSetToOne(second);
    }

    /*
        Binary Coded Decimal (BCD), known as packet decimal, is a representation where numbers 0 through 9
        converted to four-digit binary. The number 25, for example, would have a BCD number of 0010 0101 or
        00100101. However, in binary, 25 is represented as 11001. Write two functions to convert between BCD
        and Decimal.
     */
    private static final Byte negative = 0b00001101;
    private static final Byte positive = 0b00001100;

    public static Byte[] convertToBCD(int input) {
        byte currentByte;
        if (input < 0) {
            currentByte = negative;
            input = (~ input) + 1;
        } else if (input == 0) {
            return new Byte[] {positive};
        } else {
            currentByte = positive;
        }
        LinkedList<Byte> byteList = new LinkedList<>();

        boolean isSecondHalf = true;
        int remainder = 0;
        while (input != 0) {
            // remainder will always fit in the first 4 bits, so top 4 most significant bits are always zero
            // we need to set either the first half or second half of the current byte
            remainder = input % 10;
            if (isSecondHalf) {
                remainder = remainder << 4;
            }
            currentByte = (byte) (currentByte | remainder);
            byteList.push(currentByte);
            input = input / 10;
            if (isSecondHalf) {
                currentByte = 0b00000000;
                isSecondHalf = false;
            }
        }
        return byteList.toArray(new Byte[byteList.size()]);
    }

    public static int convertFromBCD(Byte[] input) {
        // get the sign from the first four bits, then start shifting
        int byteIdx = input.length - 1;
        boolean isNegative = (input[byteIdx] & negative) == negative;
        // first half of initial byte is the sign, so start on the second half;
        byte currentByte = (byte) (input[byteIdx] >>> 4);
        // read the second half of the first byte
        int sum = 0;
        sum = sum + (int) currentByte;
        byteIdx--;
        int powerOfTen = 1;
        while (byteIdx >= 0) {
            currentByte = input[byteIdx];
            sum += (0b00001111 & currentByte) * Math.pow(10, powerOfTen);
            powerOfTen++;
            sum += (currentByte >> 4) * Math.pow(10, powerOfTen);
            powerOfTen++;
            byteIdx--;
        }
        if (isNegative) {
            sum = (~ sum) + 1;
        }
        return sum;
    }

    public static boolean divisibleBy3(int input) {
        if (input < 0) {
            input = (~input) + 1;
        }

        if (input == 0) {
            return true;
        }
        if (input == 1) {
            return false;
        }
        int temp = input;
        int bitsInOdd = 0;
        int bitsInEven = 0;
        for (int i = 0; i < 32; i++) {
            if ((i & 1) == 1) {
                // odd number
                if ((temp & 1) == 1) {
                    bitsInOdd++;
                }
            } else {
                // even number
                if ((temp & 1) == 1) {
                    bitsInEven++;
                }
            }
            temp = temp >>> 1;
        }
        int difference = bitsInEven - bitsInOdd;
        if (difference < 0) {
            difference = (~ difference) + 1;
        }
        return divisibleBy3(difference);
    }


    /*
    HT Pattern: You have access to a getTosses function, that returns a 20 bit number representing 20 coin
    flips. H is coded as 0, and T is coded as 1. Your goal is to get a batch of tosses, and if you find the pattern
    3H 2T 3H, in 8 consecutive tosses, you stop, otherwise, you need to request another batch of 20 tosses
    and continue till you find this pattern.
     */

    private static final byte tossSearch = 0b00011000;
    /**
     * this is just a function that checks an individual 20 bit number.
     * @param input
     * @return
     */
    public static boolean findPattern(long input) {
        int count = 0;
        // if input == 0, we know it can't match.
        while (input != 0 && count < 13) {
            if ((tossSearch & input) == input) {
                return true;
            }

            input = input >>> 1;
            count++;
        }
        return false;
    }

    public long getTosses() {
        return 1L;
    }

    public long getMatch(long input) {
        boolean match = false;
        long toss = 0L;
        while (!match) {
            toss = getTosses();
            match = findPattern(toss);
        }
        return toss;
    }

}
