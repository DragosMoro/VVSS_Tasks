package tasks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class TaskList implements Iterable<Task>, Serializable, TaskListInterface {

    public abstract Iterator<Task> iterator();

    @Override
    public TaskListInterface incoming(Date from, Date to)
    {
        TaskListInterface incomingTasks;
        if (this instanceof ArrayTaskList){
            incomingTasks = new ArrayTaskList();
        }
        else {
            incomingTasks = new LinkedTaskList();
        }

        for(int i = 0; i < this.size(); i++){
            if(getTask(i).nextTimeAfter(from) != null && getTask(i).nextTimeAfter(from).before(to)){
                incomingTasks.add(getTask(i));
                System.out.println(getTask(i).getTitle());
            }
        }
        return incomingTasks;
    }

}