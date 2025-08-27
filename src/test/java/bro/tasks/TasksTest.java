package bro.tasks;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TasksTest {
    @Test
    public void addTask_validTodo_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"todo", "testTodo"};
        tasks.addTask(input);

        ArrayList<Task> taskList = new ArrayList<Task>();
        Todo todo = new Todo("testTodo");
        taskList.add(todo);
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }

    @Test
    public void addTask_validDeadline_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"deadline", "testDeadline", "2/12/2019 0000"};
        tasks.addTask(input);

        ArrayList<Task> taskList = new ArrayList<Task>();
        Deadline deadline = new Deadline("testDeadline", "2/12/2019 0000");
        taskList.add(deadline);
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }

    @Test
    public void addTask_validEvent_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"event", "testEvent", "2/12/2019 0000", "2/12/2019 1000"};
        tasks.addTask(input);

        ArrayList<Task> taskList = new ArrayList<Task>();
        Event event = new Event("testEvent", "2/12/2019 0000", "2/12/2019 1000");
        taskList.add(event);
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }

    @Test
    public void deleteTask_validTodo_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"todo", "testTodo"};
        tasks.addTask(input);
        tasks.deleteTask(new String[] {"delete", "1"});

        ArrayList<Task> taskList = new ArrayList<Task>();
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }

    @Test
    public void deleteTask_validDeadline_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"deadline", "testDeadline", "2/12/2019 0000"};
        tasks.addTask(input);
        tasks.deleteTask(new String[] {"delete", "1"});

        ArrayList<Task> taskList = new ArrayList<Task>();
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }

    @Test
    public void deleteTask_validEvent_success() {
        Tasks tasks = new Tasks();
        String[] input = new String[] {"event", "testEvent", "2/12/2019 0000", "2/12/2019 1000"};
        tasks.addTask(input);
        tasks.deleteTask(new String[] {"delete", "1"});

        ArrayList<Task> taskList = new ArrayList<Task>();
        Tasks checkTasks = new Tasks(taskList);
        assertEquals(checkTasks.toString(), tasks.toString());
    }
}
