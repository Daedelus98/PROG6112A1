import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //Declarations
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("STUDENT MANAGEMENT APPLICATION");
        System.out.println("***********************************");
        System.out.println("Enter (1) to launch menu or any other key to exit\n");
        int launch = scanner.nextInt();
        System.out.println("");

        if(launch == 1) {
            int command;
            do {
                String menu = """
                    
                    Please select one of the following menu items:
                    1)Capture a new student
                    2)Search for a student
                    3)Delete a student
                    4)Print student report
                    5)Exit application
                    """;
            System.out.println(menu);
            command = scanner.nextInt();

                switch (command){
                    case 1://Add student
                        Student.saveStudents(studentList, new Scanner(System.in));
                        break;
                    case 2://Search for student
                        Student.searchStudent(studentList, new Scanner(System.in));
                        break;
                    case 3://Delete student
                        Student.deleteStudent(studentList, new Scanner(System.in));
                        break;
                    case 4://Display students and details
                        Student.studentReport();
                        break;
                    case 5://Exit
                        Student.exitStudentApplication();
                        break;
                    default:
                        System.out.println("Invalid entry please try again!");
                        break;
                }
            } while (command !=5);
        }else{
            Student.exitStudentApplication();
        }
    }

    public static class Student{
        private int studentID;
        String firstName;
        String lastName;
        int studentAge;
        String studentEmail;
        String studentCourse;

        public Student(int studentID, String firstName, String lastName, int studentAge, String studentEmail, String studentCourse){
            this.studentID = studentID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.studentAge = studentAge;
            this.studentEmail = studentEmail;
            this.studentCourse = studentCourse;
        }

        public static boolean isStudentAgeValid(int age) {
            return age > 16;
        }

        public int getStudentID(){
            return studentID;
        }

        @Override
        public String toString(){
            return "STUDENT ID: " + studentID + "\nSTUDENT NAME: " + firstName + " " + lastName + "\nSTUDENT AGE: " + studentAge + "\nSTUDENT EMAIL: " + studentEmail + "\nSTUDENT COURSE: " + studentCourse;
        }

        public static void saveStudents(ArrayList<Student> studentList, Scanner scanner){

                System.out.println("");
                System.out.println("CAPTURE NEW STUDENT");
                System.out.println("***********************************");

                System.out.println("Enter the student ID:");
                int studentID = Main.scanner.nextInt();

                System.out.println("Enter the student first name:");
                String firstName = Main.scanner.next();

                System.out.println("Enter the student last name:");
                String lastName = Main.scanner.next();

                System.out.println("Enter the student age:");
                int studentAge = Main.scanner.nextInt();
                while (true) {
                    try {
                        studentAge = Integer.parseInt(scanner.next());
                        if (isStudentAgeValid(studentAge)) {
                            break; // Valid age, exit the loop
                        } else {
                            System.out.println("You have entered an incorrect student age!!!\nPlease re-enter the student age >>");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You have entered an incorrect student age!!!\nPlease re-enter the student age >>");
                    }
                }

                System.out.println("Enter the student email:");
                String studentEmail = Main.scanner.next();

                System.out.println("Enter the student course:");
                String studentCourse = Main.scanner.next();

                System.out.println("STUDENT ID: " + studentID + "\nSTUDENT NAME: " + firstName + " " + lastName + "\nSTUDENT AGE: " + studentAge + "\nSTUDENT EMAIL: " + studentEmail + "\nSTUDENT COURSE: " + studentCourse);

                Student student = new Student(studentID, firstName, lastName, studentAge, studentEmail, studentCourse);
                Main.studentList.add(student);

                System.out.println("");
                System.out.println("Student information captured successfully.");
            }

        public static Student searchStudent(ArrayList<Student> studentList, Scanner scanner){
            System.out.print("Enter student ID to search: ");
            int studentID = Main.scanner.nextInt();
            System.out.println("-------------------------------------------");

            boolean found = false;
            for (Student student : Main.studentList) {
                if (student.getStudentID() == studentID) {
                    System.out.println("Student found: " + student);
                    found = true;
                }
            }
            if (!found){
                System.out.println("Student with ID: " + studentID + " was not found.");
            }
            System.out.println("-------------------------------------------");

            System.out.println("\nEnter (1) to launch menu or any other key to exit\n");
            int launch = Main.scanner.nextInt();
            return null;
        }
        public static boolean deleteStudent(ArrayList<Student> studentList, Scanner scanner){
            System.out.println("Enter the student ID to delete:");
            int studentID = Main.scanner.nextInt();

            // Find the student to delete
            Student studentToDelete = null;
            for (Student student : Main.studentList) {
                if (student.getStudentID() == studentID) {
                    studentToDelete = student;
                    break;
                }
            }

            if (studentToDelete != null) {
                // Confirm deletion
                System.out.println("Student found: " + studentToDelete);
                System.out.print("Are you sure you want to delete student " + studentID + " from the system? Enter (y) to delete.\n");
                String confirmation = Main.scanner.next();

                if (confirmation.equalsIgnoreCase("y")) {
                    Main.studentList.remove(studentToDelete);
                    System.out.println("-------------------------------------------");
                    System.out.println("Student with Student ID: " + studentID + " WAS deleted!");
                } else {
                    System.out.println("-------------------------------------------");
                    System.out.println("Deletion cancelled.");
                }

            } else {
                System.out.println("-------------------------------------------");
                System.out.println("Student with ID " + studentID + " not found.");
            }
            System.out.println("-------------------------------------------");

            System.out.println("\nEnter (1) to launch menu or any other key to exit\n");
            int launch = Main.scanner.nextInt();
            return false;
        }
        public static void studentReport() {
            if (studentList.isEmpty()) {
                System.out.println("No student data available.");
                return;
            }

            System.out.println("Student Report:");
            for (int i = 0; i < studentList.size(); i++) {
                Student student = studentList.get(i);
                System.out.println("");
                System.out.println("Student " + (i+1));
                System.out.println("-------------------------------------------");
                System.out.println(student);
                System.out.println("-------------------------------------------");
            }
        }
        public static void exitStudentApplication(){
            System.out.println("Thank you for using the student management application! Goodbye.");
        }
    }
}
