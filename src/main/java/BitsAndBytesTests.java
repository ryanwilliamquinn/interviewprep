import sun.jvm.hotspot.utilities.Bits;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BitsAndBytesTests {

    public static void main(String[] args) {
        System.out.println("Convert to Binary String (32 bit integer input)");
        System.out.println(7 + ": " + BitsAndBytes.convertToBinaryString(7));
        System.out.println(-7 + ": " + BitsAndBytes.convertToBinaryString(-7));
        System.out.println(32 + ": " + BitsAndBytes.convertToBinaryString(32));
        System.out.println(75 + ": " + BitsAndBytes.convertToBinaryString(75));
        System.out.println(128 + ": " + BitsAndBytes.convertToBinaryString(128));
        System.out.println(24 + ": " + BitsAndBytes.convertToBinaryString(24));

        System.out.println("********************************************************************************");
        System.out.println("Is LSB set");
        System.out.println(0 + ": " + BitsAndBytes.isLsbSet(0));
        System.out.println(1 + ": " + BitsAndBytes.isLsbSet(1));
        System.out.println(2 + ": " + BitsAndBytes.isLsbSet(2));
        System.out.println(129 + ": " + BitsAndBytes.isLsbSet(129));

        System.out.println("********************************************************************************");
        System.out.println("Is MSB set");
        System.out.println("This is tricky, since there are no unsigned types in java that I know of");
        System.out.println(0 + ": " + BitsAndBytes.isMsbSet(0));
        System.out.println(1 + ": " + BitsAndBytes.isMsbSet(1));
        System.out.println(2 + ": " + BitsAndBytes.isMsbSet(2));
        System.out.println(-1 + ": " + BitsAndBytes.isMsbSet(-1));
        System.out.println("Long.MIN_VALUE: " + BitsAndBytes.isMsbSet(Long.MIN_VALUE));

        System.out.println("********************************************************************************");
        System.out.println("isBitSet");
        System.out.println(0 + ": " + BitsAndBytes.isBitSet(0, 1));
        System.out.println(1 + ": " + BitsAndBytes.isBitSet(1, 1));
        System.out.println(2 + ": " + BitsAndBytes.isBitSet(2, 1));
        System.out.println(-1 + ": " + BitsAndBytes.isBitSet(2, 63));

        System.out.println("********************************************************************************");
        System.out.println("clearBit");
        System.out.println("clear first bit of 3: " + BitsAndBytes.clearNthBit(3, 0));

        System.out.println("********************************************************************************");
        System.out.println("toggleBit");
        System.out.println("toggle first bit of 2: " + BitsAndBytes.toggleNthBit(2, 1));

        System.out.println("********************************************************************************");
        System.out.println("isDivisibleBy");
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(6, 2));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(6, 4));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(128, 2));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(129, 2));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(144, 12));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(-144, 12));
        System.out.println("is 6 divisible by 2: " + BitsAndBytes.isDivisibleBy(-14, 12));


        System.out.println("********************************************************************************");
        System.out.println("numBitsSetToZero");
        System.out.println("num zeros in 0: " + BitsAndBytes.numBitsSetToZero(0L));
        System.out.println("num zeros in 1: " + BitsAndBytes.numBitsSetToZero(1L));
        System.out.println("num zeros in 3: " + BitsAndBytes.numBitsSetToZero(3L));

        System.out.println("********************************************************************************");
        System.out.println("numBitsSetToOne");
        System.out.println("num ones in 0: " + BitsAndBytes.numBitsSetToOne(0L));
        System.out.println("num ones in 1: " + BitsAndBytes.numBitsSetToOne(1L));
        System.out.println("num ones in 3: " + BitsAndBytes.numBitsSetToOne(3L));
        System.out.println("num ones in 128: " + BitsAndBytes.numBitsSetToOne(128L));

        System.out.println("********************************************************************************");
        System.out.println("mostSignificantBit");
        System.out.println("most significant bit in 128: " + BitsAndBytes.getMostSignificantBit(128));

        System.out.println("********************************************************************************");
        System.out.println("leastSignificantBit");
        System.out.println("least significant bit in 3: " + BitsAndBytes.getLeastSignificantBit(3));
        System.out.println("least significant bit in 4: " + BitsAndBytes.getLeastSignificantBit(4));

        System.out.println("********************************************************************************");
        System.out.println("countLeadingOnes");
        System.out.println("leading ones in 1: " + BitsAndBytes.countLeadingOnes(1));
        System.out.println("leading ones in 3 << 30: " + BitsAndBytes.countLeadingOnes( 3L << 30));

        System.out.println("********************************************************************************");
        System.out.println("countLeadingZeros");
        System.out.println("leading zeros in 1: " + BitsAndBytes.countLeadingZeros(1));
        System.out.println("leading ones in 1 << 30: " + BitsAndBytes.countLeadingZeros( 1L << 30));

        System.out.println("********************************************************************************");
        System.out.println("countTrailingOnes");
        System.out.println("trailing ones in 1: " + BitsAndBytes.countTrailingOnes(1));
        System.out.println("trailing ones in 7: " + BitsAndBytes.countTrailingOnes(7));
        System.out.println("trailing ones in 1 << 3: " + BitsAndBytes.countTrailingOnes( 1L << 3));

        System.out.println("********************************************************************************");
        System.out.println("countTrailingZeros");
        System.out.println("trailing zeros in 1: " + BitsAndBytes.countTrailingZeros(1));
        System.out.println("trailing zeros in 2: " + BitsAndBytes.countTrailingZeros(1 << 1));
        System.out.println("trailing zeros in 128: " + BitsAndBytes.countTrailingZeros(1 << 7));
        System.out.println("trailing zeros in 1 << 30: " + BitsAndBytes.countTrailingZeros( 1L << 30));

        System.out.println("********************************************************************************");
        System.out.println("binaryAnagrams");
        System.out.println("1 and 2 should be binary anagrams: " + BitsAndBytes.binaryAnagrams(1, 2));
        System.out.println("1 and 3 should not be binary anagrams: " + BitsAndBytes.binaryAnagrams(1, 3));

        System.out.println("********************************************************************************");
        System.out.println("convertToBCD");
        for (Byte b : BitsAndBytes.convertToBCD(20)) {
            System.out.print(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));
        }
        System.out.println();
        for (Byte b : BitsAndBytes.convertToBCD(-1)) {
            System.out.print(String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0'));
        }

        System.out.println("********************************************************************************");
        System.out.println("convertFromBCD");
        System.out.println("25 to BCD: " + BitsAndBytes.convertFromBCD(BitsAndBytes.convertToBCD(25)));
        System.out.println("20 to BCD: " + BitsAndBytes.convertFromBCD(BitsAndBytes.convertToBCD(20)));
        System.out.println("-21 to BCD: " + BitsAndBytes.convertFromBCD(BitsAndBytes.convertToBCD(-21)));


        System.out.println("********************************************************************************");
        System.out.println("divisibleBy3");
        System.out.println("is 9 divisible by 3: " + BitsAndBytes.divisibleBy3(9));
        System.out.println("is -9 divisible by 3: " + BitsAndBytes.divisibleBy3(-9));
        System.out.println("is 8 divisible by 3: " + BitsAndBytes.divisibleBy3(8));
        System.out.println("is -8 divisible by 3: " + BitsAndBytes.divisibleBy3(-8));
        System.out.println("is 0 divisible by 3: " + BitsAndBytes.divisibleBy3(0));
        System.out.println("is 1 divisible by 3: " + BitsAndBytes.divisibleBy3(1));
        System.out.println("is 2 divisible by 3: " + BitsAndBytes.divisibleBy3(1));

    }
}
