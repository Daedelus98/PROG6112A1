import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private ArrayList<Main.Student> studentList;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        // Reset System.in to its original stream
        System.setIn(originalSystemIn);
    }

    @Test
    public void testSaveStudents() {
        String userInput = "10111\nJ\nBloggs\n19\nj.bloggs@ymail.com\ndisd\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student.saveStudents(studentList, new Scanner(System.in));

        Main.Student savedStudent = studentList.get(0);
        assertEquals(10111, savedStudent.getStudentID());
        assertEquals("J", savedStudent.getFirstName());
        assertEquals("Bloggs", savedStudent.getLastName());
        assertEquals(19, savedStudent.getStudentAge());
        assertEquals("j.bloggs@ymail.com", savedStudent.getStudentEmail());
        assertEquals("disd", savedStudent.getStudentCourse());
    }

    @Test
    public void testSearchStudent() {
        Main.Student student = new Main.Student(10111, "J", "Bloggs", 19, "j.bloggs@ymail.com", "disd");
        studentList.add(student);

        String userInput = "10111\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student result = Main.Student.searchStudent(studentList, new Scanner(System.in));
        assertEquals(student, result);
    }

    @Test
    public void testSearchStudent_StudentNotFound() {
        String userInput = "999\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Main.Student result = Main.Student.searchStudent(studentList, new Scanner(System.in));
        assertNull(result);
    }

    @Test
    public void testDeleteStudent() {
        Main.Student student = new Main.Student(10111, "J", "Bloggs", 19, "j.bloggs@ymail.com", "disd");
        studentList.add(student);

        String userInput = "10111\ny\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        boolean isDeleted = Main.Student.deleteStudent(studentList, new Scanner(System.in));
        assertTrue(isDeleted);
        assertTrue(studentList.isEmpty());
    }

    @Test
    public void testDeleteStudent_StudentNotFound() {
        String userInput = "999\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        boolean isDeleted = Main.Student.deleteStudent(studentList, new Scanner(System.in));
        assertFalse(isDeleted);
    }

    @Test
    public void testStudentAgeValid() {
        int age = 19;
        assertTrue(Main.Student.isStudentAgeValid(age));
    }

    @Test
    public void testStudentAgeInvalid() {
        int age = 15;
        assertFalse(Main.Student.isStudentAgeValid(age));
    }

    @Test
    public void testStudentAge_StudentAgeInvalidCharacter() {
        String userInput = "invalidAge\n";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        assertThrows(NumberFormatException.class, () -> {
            String input = scanner.nextLine();
            int age = Integer.parseInt(input);
            Main.Student.isStudentAgeValid(age);
        });
    }
}

