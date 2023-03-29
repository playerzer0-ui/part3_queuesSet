package tests;

import org.junit.jupiter.api.Test;
import utils.BoundedPriorityQueueSet;
import utils.Task;

import java.time.LocalDate;

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
}