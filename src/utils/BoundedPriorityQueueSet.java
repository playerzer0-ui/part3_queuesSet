package utils;

public class BoundedPriorityQueueSet {

    private Node first;
    private Node last;
    private int size;

    private final int MAX_CAPACITY;

    public BoundedPriorityQueueSet(){
        first = null;
        last = null;
        MAX_CAPACITY = 10;
        size = 0;
    }

    public BoundedPriorityQueueSet(int size){
        if(size <= 0){
            throw new IllegalArgumentException("size should be more than 0");
        }
        first = null;
        last = null;
        MAX_CAPACITY = size;
        size = 0;
    }

    /**
     * get the size of queue
     * @return size
     */
    public int size(){
        return size;
    }


    /**
     * check if queue is empty
     * @return true or false, if empty or not
     */
    public boolean isEmpty(){
        return size <= 0;
    }


    /**
     * check if queue is full
     * @return true or false, if full or not
     */
    public boolean isFull(){
        return size >= MAX_CAPACITY;
    }

    private static class Node{
        private Task data;
        private Node next;

        public Node(Task data){
            this.data = data;
            next = null;
        }
    }

    @Override
    public String toString() {
        String output = "{";
        Node current = first;
        while(current.next != null){
            output += current.data + ", ";
            current = current.next;
        }
        output += current.data + "}";
        return output;
    }

}
