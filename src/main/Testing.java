package main;

import utils.BoundedPriorityQueueSet;
import utils.Task;

import java.time.LocalDate;

public class Testing {
    public static void main(String[] args) {
        LocalDate deadline = LocalDate.parse("2023-04-30");
        LocalDate now = LocalDate.now();
//        System.out.println(now.compareTo(deadline));

        Task a = new Task("jer", "do class", LocalDate.parse("2023-04-01"));
        Task b = new Task("jer", "do class", LocalDate.parse("2023-04-05"));
        Task c = new Task("jer", "do class", LocalDate.parse("2023-04-06"));
        Task d = new Task("jer", "do class", LocalDate.parse("2023-04-07"));
        BoundedPriorityQueueSet q = new BoundedPriorityQueueSet();
        q.add(a);
        q.add(b);
        q.add(c);
        q.add(d);
        System.out.println(q);
    }

}
