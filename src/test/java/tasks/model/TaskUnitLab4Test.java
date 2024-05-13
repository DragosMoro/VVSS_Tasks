package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskUnitLab4Test {

    long oneDayMillis = 24*60*60*1000;
    int intervalSeconds = 3600;

    @Test
    void whenTaskIsNotRepeated_ShouldReturnTime() {
        //arrange
        Task task = new Task("task1",new Date(System.currentTimeMillis()+oneDayMillis));
        task.setActive(true);
        //act
        Date result = task.nextTimeAfter(new Date(System.currentTimeMillis()));
        //assert
        assertEquals(task.getTime(),result);
    }

    @Test
    void  whenTaskIsRepeated_ShouldReturnTimeBetweenIntervals() {
        //arrange
        Date current = new Date(System.currentTimeMillis());
        Date next = new Date(current.getTime()+intervalSeconds*1000);
        Task task = new Task("task1",current,new Date(System.currentTimeMillis()+oneDayMillis),intervalSeconds);
        task.setActive(true);
        //act
        Date result = task.nextTimeAfter(new Date(System.currentTimeMillis()+600*1000));
        //assert
        assertEquals(next,result);
    }
}