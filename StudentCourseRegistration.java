import java.util.ArrayList;
import java.util.Scanner;

// Course Class
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;
    int registeredStudents;

    public Course(String courseCode, String title, String description,
                  int capacity, String schedule) {

        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public int availableSeats() {
        return capacity - registeredStudents;
    }
}

// Student Class
class Student {

    String studentId;
    String studentName;

    ArrayList<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        studentId = id;
        studentName = name;
    }

    // Register Course
    public void registerCourse(Course course) {

        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
            return;
        }

        if (course.availableSeats() <= 0) {
            System.out.println("No seats available!");
            return;
        }

        registeredCourses.add(course);
        course.registeredStudents++;

        System.out.println("Course registered successfully.");
    }

    // Drop Course
    public void dropCourse(Course course) {

        if (registeredCourses.remove(course)) {
            course.registeredStudents--;
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    // View Registered Courses
    public void viewCourses() {

        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        System.out.println("\nRegistered Courses:");

        for (Course c : registeredCourses) {
            System.out.println(c.courseCode + " - " + c.title);
        }
    }
}

// Main Class
public class StudentCourseRegistration {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<>();

        // Course Database
        courses.add(new Course("CS101", "Java Programming",
                "Core Java Concepts", 3, "Mon 10 AM"));

        courses.add(new Course("CS102", "Data Structures",
                "Arrays, Linked Lists, Trees", 2, "Tue 2 PM"));

        courses.add(new Course("CS103", "Database Management",
                "SQL and DBMS", 2, "Wed 11 AM"));

        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        Student student = new Student(id, name);

        int choice;

        do {

            System.out.println("\n===============================");
            System.out.println(" Student Course Registration");
            System.out.println("===============================");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register Course");
            System.out.println("3. Drop Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\nAvailable Courses\n");

                    for (Course c : courses) {

                        System.out.println("--------------------------------");
                        System.out.println("Course Code : " + c.courseCode);
                        System.out.println("Title       : " + c.title);
                        System.out.println("Description : " + c.description);
                        System.out.println("Capacity    : " + c.capacity);
                        System.out.println("Available   : " + c.availableSeats());
                        System.out.println("Schedule    : " + c.schedule);
                    }

                    break;

                case 2:

                    System.out.print("Enter Course Code: ");

                    String registerCode = sc.next();

                    boolean found = false;

                    for (Course c : courses) {

                        if (c.courseCode.equalsIgnoreCase(registerCode)) {

                            student.registerCourse(c);
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                        System.out.println("Course not found.");

                    break;

                case 3:

                    System.out.print("Enter Course Code to Drop: ");

                    String dropCode = sc.next();

                    boolean exists = false;

                    for (Course c : courses) {

                        if (c.courseCode.equalsIgnoreCase(dropCode)) {

                            student.dropCourse(c);
                            exists = true;
                            break;
                        }
                    }

                    if (!exists)
                        System.out.println("Course not found.");

                    break;

                case 4:

                    student.viewCourses();

                    break;

                case 5:

                    System.out.println("Thank You!");

                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}