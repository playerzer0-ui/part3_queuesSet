package tests;

import org.junit.jupiter.api.Test;
import utils.BoundedPriorityQueueSet;
import utils.DuplicateElementException;
import utils.Task;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BoundedPriorityQueueSetTest {
    Task x = new Task("jer", "do class", LocalDate.parse("2023-04-02"));
    Task y = new Task("jer", "do class", LocalDate.parse("2023-04-04"));
    Task z = new Task("jer", "do class", LocalDate.parse("2023-04-03"));
    Task a = new Task("jer", "do class", LocalDate.parse("2023-04-01"));
    Task b = new Task("jer", "do class", LocalDate.parse("2023-04-05"));
    Task c = new Task("jer", "do class", LocalDate.parse("2023-04-06"));
    Task d = new Task("jer", "do class", LocalDate.parse("2023-04-07"));
    Task e = new Task("jer", "do class", LocalDate.parse("2023-04-08"));
    Task f = new Task("jer", "do class", LocalDate.parse("2023-04-09"));
    Task g = new Task("jer", "do class", LocalDate.parse("2023-04-10"));

    /**
     * size method, normal scenario
     */
    @Test
    void size_normal() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        int exp = 2;
        q.add(x);
        q.add(y);
        int act = q.size();
        assertEquals(exp, act);
    }

    /**
     * size method, but it's empty
     */
    @Test
    void size_empty() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        int exp = 0;
        int act = q.size();
        assertEquals(exp, act);
    }

    /**
     * isEmpty method, it is empty
     */
    @Test
    void isEmpty_isEmpty() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        boolean exp = true;
        boolean act = q.isEmpty();
        assertEquals(exp, act);
    }

    /**
     * isEmpty method, it is NOT empty
     */
    @Test
    void isEmpty_butNot() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        boolean exp = false;
        q.add(x);
        boolean act = q.isEmpty();
        assertEquals(exp, act);
    }

    /**
     * isFull method, and it is FULL
     */
    @Test
    void isFull_isIndeedFull() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        boolean exp = true;
        q.add(x);
        q.add(y);
        q.add(z);
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);
        q.add(e);
        q.add(f);
        q.add(g);
        boolean act = q.isFull();
        assertEquals(exp, act);
    }

    /**
     * isFull method, and it still has some remains
     */
    @Test
    void isFull_butHasRemains() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        boolean exp = false;
        q.add(x);
        q.add(y);
        q.add(z);
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);
        q.add(e);
        boolean act = q.isFull();
        assertEquals(exp, act);
    }

    /**
     * add method, normal scenario
     */
    @Test
    void add_normal() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        assertTrue(q.add(a));
        q.add(b);
        q.add(c);
        q.add(d);
        int exp = 4;
        int act = q.size();
        //test size, which means added successfully
        assertEquals(exp, act);

        //check if it is the thing
        Task found = q.peek();
        assertEquals(a, found);

        //check if it is sorted
        String ex = "{Task{owner='jer', desc='do class', deadline=2023-04-01}, Task{owner='jer', " +
                "desc='do class', deadline=2023-04-05}, Task{owner='jer', desc='do class', deadline=2023-04-06}, " +
                "Task{owner='jer', desc='do class', deadline=2023-04-07}}";
        String ac = q.toString();
        assertEquals(ex, ac);
    }

    /**
     * add method, but a duplicate is found
     */
    @Test
    void add_duplicate_found() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        q.add(a);
        q.add(b);
        assertThrows(DuplicateElementException.class, ()->{q.add(a);});
    }

    /**
     * add method, but it is too full
     */
    @Test
    void add_but_too_full() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        q.add(x);
        q.add(y);
        q.add(z);
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);
        q.add(e);
        q.add(f);
        q.add(g);
        assertThrows(IllegalStateException.class, ()->{
            q.add(new Task("some", "one", LocalDate.parse("2077-07-07")));
        });
    }

    /**
     * peek method, normal scenario
     */
    @Test
    void peek_normal() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        q.add(a);
        q.add(b);

        Task found = q.peek();
        assertEquals(a, found);
    }

    /**
     * peek method, but it is empty
     */
    @Test
    void peek_empty() {
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();

        assertThrows(NoSuchElementException.class, q::peek);
    }
}