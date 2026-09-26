import java.util.*;

// 1. Definition for singly-linked list node
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 2. Custom Min-Heap Class built from scratch
class MinHeap {
    private ListNode[] heap;
    private int size;

    public MinHeap(int capacity) {
        this.heap = new ListNode[capacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Insert a node into the heap
    public void push(ListNode node) {
        heap[size] = node;
        siftUp(size);
        size++;
    }

    // Extract and return the minimum node
    public ListNode pop() {
        if (size == 0) return null;
        ListNode minNode = heap[0];
        heap[0] = heap[size - 1]; // Move last element to root
        size--;
        siftDown(0);             // Restore heap property
        return minNode;
    }

    // Moves an element up to maintain min-heap property
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].val < heap[parentIndex].val) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Moves an element down to maintain min-heap property
    private void siftDown(int index) {
        while (index * 2 + 1 < size) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int smallest = leftChild;

            if (rightChild < size && heap[rightChild].val < heap[leftChild].val) {
                smallest = rightChild;
            }

            if (heap[index].val > heap[smallest].val) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        ListNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}

// 3. Solution using custom MinHeap
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Initialize custom Min-Heap with capacity = number of lists
        MinHeap minHeap = new MinHeap(lists.length);

        // Add the head of each list to the Min-Heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.push(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Extract minimum and insert next pointer
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.pop();
            tail.next = smallest;
            tail = tail.next;

            if (smallest.next != null) {
                minHeap.push(smallest.next);
            }
        }

        return dummy.next;
    }
}