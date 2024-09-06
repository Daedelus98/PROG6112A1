import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private ArrayList<Main.Student> studentList;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
    }

    @Test
    public void testSaveStudents() {
        String userInput = "10111\nJ\nBloggs\n19\nj.bloggs@ymail.com\ndisd\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student.saveStudents(studentList, new Scanner(System.in));

        Main.Student savedStudent = studentList.get(0);
        assertEquals(10111, savedStudent.getStudentID());
        assertEquals("J", savedStudent.firstName);
        assertEquals("Bloggs", savedStudent.lastName);
        assertEquals(19, savedStudent.studentAge);
        assertEquals("j.bloggs@ymail.com", savedStudent.studentEmail);
        assertEquals("disd", savedStudent.studentCourse);
    }

    @Test
    public void testSearchStudent() {
        // Adding a student to the list
        Main.Student student = new Main.Student(10111, "J", "Bloggs", 19, "j.bloggs@ymail.com", "disd");
        studentList.add(student);

        // Simulate searching for the student by ID
        String userInput = "10111\n"; // Assuming 1 is the ID we are searching for
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student result = Main.Student.searchStudent(studentList, new Scanner(System.in));
        assertEquals(student, result); // Ensure the same student is returned
    }

    @Test
    public void testSearchStudent_StudentNotFound() {
        // Simulate searching for a student that does not exist
        String userInput = "999\n"; // Assuming 999 is an ID that does not exist
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student result = Main.Student.searchStudent(studentList, new Scanner(System.in));
        assertNull(result); // Ensure null is returned for not found
    }

    @Test
    public void testDeleteStudent() {
        // Adding a student to the list
        Main.Student student = new Main.Student(10111, "J", "Bloggs", 19, "j.bloggs@ymail.com", "disd");
        studentList.add(student);

        // Simulate deleting the student by ID
        String userInput = "10111\n"; // Assuming 1 is the ID to delete
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        boolean isDeleted;
        if (Main.Student.deleteStudent(studentList, new Scanner(System.in))) isDeleted = true;
        else isDeleted = false;
        assertTrue(isDeleted); // Ensure the student was deleted
        assertTrue(studentList.isEmpty()); // Ensure the list is now empty
    }

    @Test
    public void testDeleteStudent_StudentNotFound() {
        // Simulate deleting a student that does not exist
        String userInput = "999\n"; // Assuming 999 is an ID that does not exist
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        boolean isDeleted;
        if (Main.Student.deleteStudent(studentList, new Scanner(System.in))) isDeleted = true;
        else isDeleted = false;
        assertFalse(isDeleted); // Ensure the student was not deleted
    }

    @Test
    public void testStudentAgeValid() {
        int age = 19;
        assertTrue(Main.Student.isStudentAgeValid(age)); // Ensure age is valid
    }

    @Test
    public void testStudentAgeInvalid() {
        int age = -1;
        assertFalse(Main.Student.isStudentAgeValid(age)); // Ensure age is invalid
    }

    @Test
    public void testStudentAge_StudentAgeInvalidCharacter() {
        String userInput = "invalidAge\n"; // Invalid input
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        assertThrows(NumberFormatException.class, () -> {
            int age = Integer.parseInt(scanner.nextLine());
            Main.Student.isStudentAgeValid(age); // This should not be reached
        });
    }
}