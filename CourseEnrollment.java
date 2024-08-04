import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String toString() {
        return "Course ID: " + courseID + ", Name: " + courseName + ", Credits: " + credits;
    }
}

class Enrollment {
    private int[][] studentCourses;
    private int[] count;

    public Enrollment(int maxStudents, int maxCourses) {
        studentCourses = new int[maxStudents][maxCourses];
        count = new int[maxStudents];
    }

    public void enroll(int studentID, int courseID) {
        if (count[studentID] < studentCourses[studentID].length) {
            studentCourses[studentID][count[studentID]] = courseID;
            count[studentID]++;
            System.out.println("Enrollment successful.");
        } else {
            System.out.println("Cannot enroll. Maximum courses reached for this student.");
        }
    }

    public void drop(int studentID, int courseID) {
        for (int i = 0; i < count[studentID]; i++) {
            if (studentCourses[studentID][i] == courseID) {
                // Move the last course to this position and decrement count
                studentCourses[studentID][i] = studentCourses[studentID][count[studentID] - 1];
                count[studentID]--;
                System.out.println("Course dropped successfully.");
                return;
            }
        }
        System.out.println("Course not found in student's enrollments.");
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) {
        if (count[studentID] == 0) {
            System.out.println("Student " + studentID + " is not enrolled in any courses.");
            return;
        }

        System.out.println("Enrolled courses for student " + studentID + ":");
        for (int i = 0; i < count[studentID]; i++) {
            int courseID = studentCourses[studentID][i];
            for (Course course : courseCatalog) {
                if (course.getCourseID() == courseID) {
                    System.out.println(course);
                    break;
                }
            }
        }
    }
}

public class CourseEnrollment {
    private static Course[] courseCatalog;
    private static Enrollment enrollment;

    public static void main(String[] args) {
        initializeCourseCatalog();
        enrollment = new Enrollment(100, 10); 

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Enrollment System");
            System.out.println("1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    enrollCourse(scanner);
                    break;
                case 2:
                    dropCourse(scanner);
                    break;
                case 3:
                    viewEnrolledCourses(scanner);
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

    private static void initializeCourseCatalog() {
        courseCatalog = new Course[]{
            new Course(101, "Introduction to Programming", 3),
            new Course(102, "Data Structures", 4),
            new Course(103, "Database Management", 3),
            new Course(104, "Web Development", 3),
            new Course(105, "Artificial Intelligence", 4)
        };
    }

    private static void enrollCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseID = scanner.nextInt();
        enrollment.enroll(studentID, courseID);
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter course ID to drop: ");
        int courseID = scanner.nextInt();
        enrollment.drop(studentID, courseID);
    }

    private static void viewEnrolledCourses(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        enrollment.getEnrolledCourses(studentID, courseCatalog);
    }
}