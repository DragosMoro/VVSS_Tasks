package tasks.model;

import java.util.Date;
import java.util.List;

public interface TaskListInterface {

    void add(Task task);

    boolean remove(Task task);

    int size();

    Task getTask(int index);

    List<Task> getAll();

    TaskListInterface incoming(Date from, Date to);
}
