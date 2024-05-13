package tasks.services.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.Task;
import tasks.model.TaskListInterface;
import tasks.model.TasksOperations;
import tasks.services.TasksService;

import java.util.Date;
import java.util.List;

public class DefaultTasksService implements TasksService {

    private TaskListInterface tasks;

    public DefaultTasksService(TaskListInterface tasks) {
        this.tasks = tasks;
    }

    public List<Task> getAllTasks(){
        return tasks.getAll();
    }

    @Override
    public ObservableList<Task> getObservableList() {
        return FXCollections.observableArrayList(getAllTasks());
    }

    @Override
    public String getIntervalInHours(Task task) {
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DefaultDateTimeService.SECONDS_IN_MINUTE;
        int hours = minutes / DefaultDateTimeService.MINUTES_IN_HOUR;
        minutes = minutes % DefaultDateTimeService.MINUTES_IN_HOUR;
        return DefaultDateTimeService.formTimeUnit(hours) + ":" + DefaultDateTimeService.formTimeUnit(minutes);//hh:MM
    }

    @Override
    public Iterable<Task> filterTasks(Date start, Date end) {
        TasksOperations tasksOps = new TasksOperations(getObservableList());

        return tasksOps.incoming(start, end);
        //Iterable<Task> filtered = tasksOps.incoming(start, end);
        //Iterable<Task> filtered = tasks.incoming(start, end);

        // return filtered;
    }
}
