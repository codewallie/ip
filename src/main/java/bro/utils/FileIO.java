package bro.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.Task;
import bro.tasks.Todo;

/**
 * Handles file input and output operations for storing and retrieving tasks.
 */
public class FileIo {
    public static final String FOLDER_PATH = "./data";
    public static final String FILE_PATH = FOLDER_PATH + "/bro.txt";

    /**
     * Checks if the data folder exists, and creates it if it does not.
     * @return true if the folder exists or was created successfully, false otherwise.
     */
    private static boolean folderExists() {
        try {
            Path path = Paths.get(FOLDER_PATH);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Checks if the data file exists, and creates it if it does not.
     * @return true if the file exists or was created successfully, false otherwise.
     */
    private static boolean fileExists() {
        try {
            if (!folderExists()) {
                return false;
            }
            File broFile = new File(FILE_PATH);
            broFile.createNewFile();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Appends a new entry to the data file.
     * @param entry The entry to append.
     */
    public static void addEntry(String entry) {
        if (fileExists()) {
            try {
                Files.write(
                        Paths.get(FILE_PATH),
                        entry.getBytes(),
                        StandardOpenOption.APPEND);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Updates an existing entry in the data file based on its description.
     * @param updatedEntry The updated entry to replace the existing one.
     */
    public static void updateByDescription(String updatedEntry) {
        String type = updatedEntry.substring(0, 1);
        String description = updatedEntry.split("[|]")[2];
        if (fileExists()) {
            try {
                String currentData = "";
                File data = new File(FILE_PATH);
                Scanner reader = new Scanner(data);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] lineData = line.split("[|]");
                    if (lineData[0].equals(type) && lineData[2].equals(description)) {
                        currentData += updatedEntry + System.lineSeparator();
                    } else {
                        currentData += line + System.lineSeparator();
                    }
                }
                reader.close();
                Files.write(
                        Paths.get(FILE_PATH),
                        currentData.getBytes());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Deletes an entry from the data file.
     * @param deletedEntry The entry to delete.
     */
    public static void deleteByEntry(String deletedEntry) {
        if (fileExists()) {
            try {
                String currentData = "";
                File data = new File(FILE_PATH);
                Scanner reader = new Scanner(data);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (!line.equals(deletedEntry)) {
                        currentData += line + System.lineSeparator();
                    }
                }
                reader.close();
                Files.write(
                        Paths.get(FILE_PATH),
                        currentData.getBytes());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the data file into an ArrayList. Used during application startup.
     * @return An ArrayList of tasks loaded from the file.
     */
    public static ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        if (fileExists()) {
            try {
                File data = new File(FILE_PATH);
                Scanner reader = new Scanner(data);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] lineData = line.split("[|]");
                    boolean isDone = lineData[1].equals("1");
                    switch (lineData[0]) {
                    case "T":
                        Todo todo = new Todo(lineData[2], isDone);
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(lineData[2], isDone, lineData[3]);
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(lineData[2], isDone, lineData[3], lineData[4]);
                        tasks.add(event);
                        break;
                    default:
                        System.out.println("Corrupted file!");
                    }
                }
                reader.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ArrayList<Task>();
            }
        }
        return tasks;
}}
