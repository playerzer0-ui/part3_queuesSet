package utils;

import java.time.LocalDate;

public class Task implements Comparable<Task> {

    private String owner;
    private String desc;
    private LocalDate deadline;
    private final LocalDate now = LocalDate.now();

    Task(){}

    public Task(String owner, String desc, LocalDate deadline) {
        // > 0 = overdue / < 0 = still have time
        if(now.compareTo(deadline) >= 0){
            throw new IllegalArgumentException("the deadline is past today");
        }
        this.owner = owner;
        this.desc = desc;
        this.deadline = deadline;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if(now.compareTo(deadline) >= 0){
            throw new IllegalArgumentException("the deadline is past today");
        }
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!owner.equals(task.owner)) return false;
        if (!desc.equals(task.desc)) return false;
        return deadline.equals(task.deadline);
    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + desc.hashCode();
        result = 31 * result + deadline.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "owner='" + owner + '\'' +
                ", desc='" + desc + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    @Override
    public int compareTo(Task o) {
        return this.deadline.compareTo(o.getDeadline());
    }
}

