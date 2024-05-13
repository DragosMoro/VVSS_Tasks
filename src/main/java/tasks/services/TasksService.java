package tasks.services;

import javafx.collections.ObservableList;
import tasks.model.Task;

import java.util.Date;

public interface TasksService {

    ObservableList<Task> getObservableList();
    String getIntervalInHours(Task task);
    Iterable<Task> filterTasks(Date start, Date end);
}
