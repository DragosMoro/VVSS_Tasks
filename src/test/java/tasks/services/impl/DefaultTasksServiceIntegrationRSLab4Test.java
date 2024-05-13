package tasks.services.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class DefaultTasksServiceIntegrationRSLab4Test {

    @Mock
    private Task task;

    @InjectMocks
    private ArrayTaskList repo;

    private DefaultTasksService service;

    private ArrayList<Task> tasks = new ArrayList<>();

    private ObservableList<Task> observableList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(0).getTitle()).thenReturn("t1");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(1).getTitle()).thenReturn("t2");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(2).getTitle()).thenReturn("t3");
        tasks.add(Mockito.mock(Task.class));
        Mockito.when(tasks.get(3).getTitle()).thenReturn("t4");

        repo = new ArrayTaskList();
        repo.add(tasks.get(0));
        repo.add(tasks.get(1));
        repo.add(tasks.get(2));
        repo.add(tasks.get(3));
        service = new DefaultTasksService(repo);

        observableList = FXCollections.observableList((ArrayList)tasks);
    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void integration_test_getAllTasks() {

        ArrayList<Task> expected = tasks;
        ArrayList<Task> result = (ArrayList<Task>) service.getAllTasks();

        assertEquals(expected.size(), result.size());
    }

    @Test
    void integration_test_getObservableList() {

        ObservableList<Task> expected = observableList;
        ObservableList<Task> result = service.getObservableList();

        assertEquals(expected.size(), result.size());
    }
}