import java.util.Scanner;

class Student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    public Student(int studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public String toString() {
        return "Student ID: " + studentID + "\n" +" Name: " + name +  "\n" +" Age: " + age +  "\n" +" Department: " + department + "\n" ;
    }
}

class StudentRecordSystem {
    private Student[] students;
    private int count;

    public StudentRecordSystem(int capacity) {
        students = new Student[capacity];
        count = 0;
    }

    public void addStudent(Student student) {
        if (count < students.length) {
            students[count] = student;
            count++;
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student record system is full. Cannot add more students.");
        }
    }

    public Student getStudent(int studentID) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == studentID) {
                return students[i];
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (count == 0){
            System.out.println("No students in the system.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(students[i]);
            }
        }
    }
}

public class StudentRecordMGMT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRecordSystem recordSystem = new StudentRecordSystem(100);

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add new student");
            System.out.println("2. View student by ID");
            System.out.println("3. Display all students");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter student department: ");
                    String department = scanner.nextLine();

                    Student newStudent = new Student(id, name, age, department);
                    recordSystem.addStudent(newStudent);
                    break;

                case 2:
                    System.out.print("Enter student ID to search: ");
                    int searchID = scanner.nextInt();
                    Student foundStudent = recordSystem.getStudent(searchID);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    recordSystem.displayAllStudents();
                    break;

                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}