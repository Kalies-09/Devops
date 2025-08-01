package com.example.dtr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    int id;
    String description;
    boolean completed;

    Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return id + ". " + description + " - " + (completed ? "✅ Done" : "❌ Not Done");
    }
}

public class TaskReminder{
    private static final List<Task> tasks = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n== Daily Task Reminder ==");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String desc = sc.nextLine();
                    tasks.add(new Task(nextId++, desc));
                    System.out.println("✅ Task added!");
                    break;

                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("📭 No tasks found.");
                    } else {
                        for (Task task : tasks) {
                            System.out.println(task);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter task ID to mark as done: ");
                    int doneId = sc.nextInt();
                    boolean found = false;
                    for (Task task : tasks) {
                        if (task.id == doneId) {
                            task.markCompleted();
                            System.out.println("✅ Task marked as done!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("❌ Task not found.");
                    break;

                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int delId = sc.nextInt();
                    boolean removed = tasks.removeIf(task -> task.id == delId);
                    if (removed) {
                        System.out.println("🗑️ Task deleted!");
                    } else {
                        System.out.println("❌ Task not found.");
                    }
                    break;

                case 5:
                    System.out.println("👋 Exiting...");
                    run = false;
                    break;

                default:
                    System.out.println("⚠️ Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
