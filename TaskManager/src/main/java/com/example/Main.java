package com.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        char ch;
        int numberOfTasks;
        boolean continueFlag = true;
        int choice;
        boolean validInput;
        while (continueFlag) {
            System.out.println("Доступные опции: ");
            System.out.println("1) Посмотреть список задач");
            System.out.println("2) Добавить новую задачу");
            System.out.println("3) Отметить задачу как выполненную");
            System.out.println("4) Удалить задачу");
            System.out.println("5) Очистить список");
            System.out.println("6) Выйти из программы\n");
            choice = -1;
            validInput = false;

            while (!validInput) {
                System.out.print("Выберите опцию: ");
                String input = scanner.nextLine();
                try {
                    choice = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число!");
                }
            }

            switch (choice) {
                case 1:
                    taskManager.showTasks();
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.print("Введите имя новой задачи: ");
                    String taskName = scanner.nextLine();
                    System.out.println("\nВведите описание новой задачи: ");
                    String taskDescription = scanner.nextLine();
                    Task clearTask = new Task(taskName, taskDescription);
                    taskManager.addTask(clearTask);
                    System.out.println("Задача успешно добавлена\n");
                    break;
                case 3:
                    taskManager.showTasks();
                    System.out.println("\nВведите номер задачи: ");
                    numberOfTasks = scanner.nextInt();
                    scanner.nextLine();
                    if (taskManager.makeCompleted(numberOfTasks))
                        System.out.println("Успешно!\n");
                    else
                        System.out.println("Ошибка индексации\n");
                    break;
                case 4:
                    taskManager.showTasks();
                    System.out.println("\nВведите номер задачи: ");
                    numberOfTasks = scanner.nextInt();
                    scanner.nextLine();
                    if (taskManager.removeTask(numberOfTasks))
                        System.out.println("Успешно!\n");
                    else
                        System.out.println("Ошибка индексации\n");
                    break;
                case 5:
                    taskManager.showTasks();
                    System.out.println("Вы действительно хотите очистить список?[y/N]");
                    do {
                        ch = scanner.next().charAt(0);
                        scanner.nextLine();
                    } while (ch != 'y' && ch != 'N');
                    if (ch == 'y')
                        taskManager.clearList();
                    break;
                case 6:
                    taskManager.showTasks();
                    System.out.println("Вы действительно хотите выйти?[y/N]");
                    do {
                        ch = scanner.next().charAt(0);
                        scanner.nextLine();
                    } while (ch != 'y' && ch != 'N');
                    if (ch == 'y')
                        continueFlag = false;
                    break;
            }
        }
        System.out.println("Работа программы завершена успешно.");
    }
}

class Task {
    private String title;
    private String description;
    private boolean isCompleted;

    // конструктор

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        isCompleted = false;
    }

    public Task() {
        this.title = null;
        this.description = null;
        isCompleted = false;
    }

    // геттеры

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // сеттеры

    public void setParameters(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[+] " : "[?] ") + title + ": " + description;
    }
}

class TaskManager {
    private List<Task> taskList = new LinkedList<>();

    public void showTasks() {
        if (taskList.isEmpty()) {
            System.out.println("Список задач пуст");
        } else {
            System.out.println("=== Список задач ===");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + ": " + taskList.get(i));
            }
            System.out.println("====================");
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public boolean removeTask(int index) {
        if (index < 0 || index > taskList.size()) {
            return false;
        } else {
            taskList.remove(index);
            return true;
        }
    }

    public boolean makeCompleted(int index) {
        if (index < 0 || index > taskList.size()) {
            return false;
        } else {
            taskList.get(index).setCompleted(true);
            return true;
        }
    }

    public void clearList() {
        taskList.clear();
    }
}
