package bro.tasks;

import java.util.ArrayList;

import bro.utils.FileIo;

/**
 * Manages an ArrayList of tasks, allowing addition, deletion, marking,
 * unmarking, and listing of tasks.
 */
public class Tasks {
    private ArrayList<Task> tasks;

    /**
     * Creates a new Tasks object with an empty task list.
     */
    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new Tasks object with the given task list.
     *
     * @param tasks The initial list of tasks.
     */
    public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getEntry(int index) {
        return tasks.get(index);
    }

    public String addTask(Task task) {
        tasks.add(task);
        return String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                tasks.get(tasks.size() - 1),
                tasks.size());
    }

    public String deleteTask(int index) {
        Task task = tasks.get(index); // For printing after removal
        tasks.remove(index);
        return String.format(
                "Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(),
                tasks.size());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }
        return sb.toString();
    }
}
