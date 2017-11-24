import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.List;

public class QotdJuly17 {
    // i guess we are implementing linked lists?
    private class LinkedListNode<T> {
        T value;
        LinkedListNode<T> next = null;

        public void add(LinkedListNode<T> elem) {
            elem.next = this;
        }

        public void append(LinkedListNode<T> elem) {
            if (this.next == null) {
                this.next = elem;
            } else {
                this.next.append(elem);
            }
        }

        public void deleteHead() {
            this.value = null;
        }

        public void deleteTail() {
            // the list is only one element long. delete our value, and thats all we can do
            if (this.next == null) {
                this.value = null;
            } else if (this.next.next == null) {
                this.next = null;
            } else {
                this.next.deleteTail();
            }
        }

        public int getLength() {
            if (this.next == null) {
                return 1;
            } else {
                return 1 + this.next.getLength();
            }
        }

        public LinkedListNode<T> getKthNode(int index) {
            int length = getLength();
            if (index >= length) {
                return null;
            } else {
                // could do this iteratively just as easilyj
                if (index == 0) {
                    return this;
                } else {
                    return this.next.getKthNode(index - 1);
                }

            }
        }

        public void insertAfterKthNode(LinkedListNode<T> node, int index) {
            LinkedListNode<T> kthNode = getKthNode(index);
            if (kthNode == null) {
                return;
            }
            node.next = kthNode.next;
            kthNode.next = node;
        }

        public void deleteKthNode(int index) {
            if (index == 0) {
                // what does it mean to delete the head of a linked list?
                return;
            }
            // get the node before index
            LinkedListNode<T> node = getKthNode(index - 1);
            if (node == null || node.next == null) {
                return;
            }
            // remove the kth node from the list
            node.next = node.next.next;
        }

        public void printNumberOfElements() {
            System.out.println(getLength());
        }

        public LinkedListNode<T> reverseRec(LinkedListNode<T> forward, LinkedListNode<T> backward) {
            if (forward == null) {
                return backward;
            }

            LinkedListNode<T> nextForward = forward.next;
            forward.next = backward;

            return reverseRec(nextForward, backward);
        }

        public LinkedListNode<T> reverse() {
            return reverseRec(this, null);
        }

        public void deleteNodeWithValue(T value) {
            if (this.next == null) {
                return;
            }

            if (this.next.value == value) {
                this.next = this.next.next;
            }

            this.next.deleteNodeWithValue(value);
        }
    }

    public void insertSortedRec(LinkedListNode<Integer> node, LinkedListNode<Integer> sorted) {
        // sorted.value will always be less than node.value
        if (sorted.next == null) {
            sorted.next = node;
        } else if (sorted.next.value < node.value) {
            insertSortedRec(node, sorted.next);
        } else {
            // sorted.next.value >= node.value, so we can insert node here, between sorted and sorted.next
            node.next = sorted.next;
            sorted.next = node;
        }
    }

    // assuming list is sorted in ascending order
    public LinkedListNode<Integer> insertSorted(LinkedListNode<Integer> node, LinkedListNode<Integer> sorted) {
        // if sorted is null, return node
        if (sorted == null) {
            return node;
        }

        // if the first element of sorted is larger, prepend node and return it
        if (sorted.value > node.value) {
            node.next = sorted;
            return node;
        }

        // otherwise recurse and insert as necessary
        insertSortedRec(node, sorted);
        // return the head of the sorted list
        return sorted;


    }


    public List<LinkedListNode<Integer>> oddAndEvenListsRec(LinkedListNode<Integer> node, LinkedListNode<Integer> even, LinkedListNode<Integer> odd) {
        if (node == null) {
            return Arrays.asList(even, odd);
        }
        if (node.value % 2 == 0) {
            node.next = even;
            return oddAndEvenListsRec(node.next, node, odd);
        } else {
            node.next = odd;
            return oddAndEvenListsRec(node.next, even, node);
        }
    }

    public List<LinkedListNode<Integer>> oddAndEvenLists(LinkedListNode<Integer> node) {
        return oddAndEvenListsRec(node, null, null);
    }

    public void mergeSortedRec(LinkedListNode<Integer> accumulator, LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
        if (first == null) {
            accumulator.next = second;
        } else if (second == null) {
            accumulator.next = first;
        } else {
            if (first.value < second.value) {
                accumulator.next = first;
                mergeSortedRec(accumulator.next, first.next, second);
            } else {
                accumulator.next = second;
                mergeSortedRec(accumulator.next, first, second.next);
            }
        }
    }

    public LinkedListNode<Integer> mergeSorted(LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
        // check for null lists
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        // figure out the first element for the accumulator
        LinkedListNode<Integer> accumulator;
        if (first.value < second.value) {
            accumulator = first;
            first = first.next;
        } else {
            accumulator = second;
            first = second.next;
        }

        // merge the lists
        mergeSortedRec(accumulator, first, second);

        // return the head of the list
        return accumulator;
    }


    public List<LinkedListNode<Integer>> splitEven(LinkedListNode<Integer> node) {
        LinkedListNode<Integer> tempNode = node;
        int sum = 0;
        while (tempNode != null) {
            sum += tempNode.value;
            tempNode = tempNode.next;
        }
        if (sum % 2 != 0) {
            return null;
        }
        int half = sum / 2;
        int firstHalfSum = 0;
        // reset tempNode to the head of the list
        tempNode = node;
        LinkedListNode<Integer> frontList = tempNode;

        // go through the list until we hit the sum
        while (firstHalfSum != half) {
            firstHalfSum += tempNode.value;
            tempNode = tempNode.next;
        }

        // once we've hit the sum, set the backList to the next element
        LinkedListNode<Integer> backList = tempNode.next;
        tempNode.next = null;
        return Arrays.asList(frontList, backList);
    }

    public LinkedListNode<Integer> removeDuplicatesFromSortedLinkedList(LinkedListNode<Integer> node) {
        if (node == null) {
            return node;
        }
        LinkedListNode<Integer> tempNode = node;
        LinkedListNode<Integer> toDelete;
        while (tempNode.next != null) {
            if (tempNode.value == tempNode.next.value) {
                toDelete = tempNode.next;
                tempNode.next = toDelete.next;
                toDelete.next = null;
            } else {
                tempNode = tempNode.next;
            }
        }
        return node;
    }

    public LinkedListNode<Integer> findThirdFromLastInOnePass(LinkedListNode<Integer> node) {
        // two pointer method, move lead pointer to 3rd element, then progress both pointers at same rate
        // when lead is at end, second is at 3rd from last
        LinkedListNode<Integer> lead = node;
        LinkedListNode<Integer> trail = null;
        int count = 0;
        while (count < 3 && node != null) {
            lead = lead.next;
            count++;
        }
        trail = node;
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        return trail;
    }

    public LinkedListNode<Integer> reverseInBuckets(LinkedListNode<Integer> node) {
        return null;

    }


}
