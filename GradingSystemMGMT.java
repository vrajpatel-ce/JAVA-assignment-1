import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int maxStudents, int maxGrades, int maxCourses) {
        students = new Student[maxStudents];
        grades = new Grade[maxGrades];
        courseCredits = new int[maxCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
            System.out.println("Grade added successfully.");
        } else {
            System.out.println("Maximum number of grades reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID >= 0 && courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
            System.out.println("Course credits added successfully.");
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    public double calculateGPA(int studentID) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int courseID = grades[i].getCourseID();
                int credits = courseCredits[courseID];
                int points = gradeToPoints(grades[i].getGrade());

                totalPoints += points * credits;
                totalCredits += credits;
            }
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            default: return 0;
        }
    }

    public void printGradeReport(int studentID) {
        Student student = null;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID() == studentID) {
                student = students[i];
                break;
            }
        }

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Grade Report for " + student.getName() + " (ID: " + student.getStudentID() + ")");
        System.out.println("Courses and Grades:");
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                System.out.println("Course ID: " + grades[i].getCourseID() + ", Grade: " + grades[i].getGrade());
            }
        }
        System.out.printf("GPA: %.2f\n", calculateGPA(studentID));
    }
}

public class GradingSystemMGMT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem gradingSystem = new GradingSystem(100, 1000, 50);

        while (true) {
            System.out.println("\nGrading and Result Declaration System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print Grade Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    gradingSystem.addStudent(new Student(studentID, name));
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    int gradeStudentID = scanner.nextInt();
                    System.out.print("Enter course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter grade (A, B, C, D, or F): ");
                    char grade = scanner.next().charAt(0);
                    gradingSystem.addGrade(new Grade(gradeStudentID, courseID, grade));
                    break;
                case 3:
                    System.out.print("Enter course ID: ");
                    int creditCourseID = scanner.nextInt();
                    System.out.print("Enter credits: ");
                    int credits = scanner.nextInt();
                    gradingSystem.addCourseCredits(creditCourseID, credits);
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    int gpaStudentID = scanner.nextInt();
                    double gpa = gradingSystem.calculateGPA(gpaStudentID);
                    System.out.printf("GPA for student %d: %.2f\n", gpaStudentID, gpa);
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    int reportStudentID = scanner.nextInt();
                    gradingSystem.printGradeReport(reportStudentID);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}