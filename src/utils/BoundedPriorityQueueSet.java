package utils;

import java.util.NoSuchElementException;

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

    /**
     * add a task to the queue, it is rejected if queue is full or if there is already a task there
     * @param value the Task to add
     * @return true
     * @throws DuplicateElementException if found duplicate task
     * @throws IllegalStateException if queue is full
     */
    public boolean add(Task value){
        if(contains(value)){
            throw new DuplicateElementException("task is already there");
        }
        if(isFull()){
            throw new IllegalStateException("queue is full");
        }

        Node newNode = new Node(value);
        if(first == null){
            first = newNode;
            last = newNode;
        }
        //if value deadline is sooner than the first
        else if(value.getDeadline().compareTo(first.data.getDeadline()) < 0){
            newNode.next = first;
            first = newNode;
        }
        //if value deadline is smaller than the last
        else if(value.getDeadline().compareTo(last.data.getDeadline()) >= 0){
            last.next = newNode;
            last = newNode;
        }
        //guess it's somewhere in the middle then
        else{
            Node prev = first;
            Node current = first.next;
            //keep comparing if the current has later deadline than the value
            while(current.data.getDeadline().compareTo(value.getDeadline()) <= 0){
                prev = current;
                current = current.next;
            }
            //slip it in
            prev.next = newNode;
            newNode.next = current;
        }
        size++;
        return true;
    }

    /**
     * get the task with the closest deadline
     * @return the task with the closest deadline
     */
    public Task peek(){
        if(isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
        return first.data;
    }


    /**
     * remove closest deadline from the queue
     * @return removed task
     */
    public Task remove(){
        if(isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
        Node removed = first;
        first = first.next;
        size--;
        return removed.data;
    }

    /**
     * check to see if task is already there
     * @param target the task to find
     * @return true or false, if found or not
     */
    private boolean contains(Task target){
        if(isEmpty()){
            return false;
        }

        else if(first.data.equals(target) || last.data.equals(target)){
            return true;
        }
        else{
            Node current = first;
            while(current.next != null){
                if(current.data.equals(target)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
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
