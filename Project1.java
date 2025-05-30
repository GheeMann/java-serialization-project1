// Project1.java
// Author: Chris Garate

import java.io.*;
import java.util.Scanner;

// Provided Data from Professor
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int[] scores;

    public Student(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public double getAverageScore() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / (double) scores.length;
    }
}

public class Project1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the filename to save student records: ");
        String filename = scanner.nextLine();
        // Provided Data from Professor
        Student[] students = {
            new Student("Alice", new int[] {85, 90, 92}),
            new Student("Bob", new int[] {78, 85, 88}),
            new Student("Charlie", new int[] {90, 93, 85}),
            new Student("David", new int[] {70, 75, 80}),
            new Student("Eva", new int[] {88, 92, 89}),
            new Student("Frank", new int[] {80, 85, 84}),
            new Student("Grace", new int[] {95, 98, 92}),
            new Student("Hank", new int[] {60, 65, 70}),
            new Student("Ivy", new int[] {85, 90, 88}),
            new Student("Jack", new int[] {75, 80, 78})
        };

        // Serialization
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(students);
            System.out.println("Student records saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving student records: " + e.getMessage());
            return;
        }

        // Deserialization + Error Handling
        Student[] loadedStudents = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            loadedStudents = (Student[]) in.readObject();
            System.out.println("Student records loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return;
        } catch (IOException e) {
            System.out.println("Error reading student records: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found error: " + e.getMessage());
            return;
        }

        // Results?
        System.out.println("\nStudent Averages:");
        for (Student s : loadedStudents) {
            System.out.printf("%s: %.2f%n", s.getName(), s.getAverageScore());
        }
    }
}
