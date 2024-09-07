import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssessmentScheduleTest {

    @BeforeEach
    public void setUp() {
        // Clear the ArrayLists before each test
        AssessmentSchedule.subjectsArray.clear();
        AssessmentSchedule.assessmentNamesArray.clear();
        AssessmentSchedule.assessmentDescriptionsArray.clear();
        AssessmentSchedule.assessmentTypeArray.clear();
        AssessmentSchedule.assessmentDurationsArray.clear();
        AssessmentSchedule.assessmentStatusArray.clear();
        AssessmentSchedule.dueDateArray.clear();
        AssessmentSchedule.assessmentMarksArray.clear();
        AssessmentSchedule.lecturersArray.clear();
    }

    @Test
    public void testAddAssessments() {
        // Simulating adding an assessment
        AssessmentSchedule.assessmentNamesArray.add("First Semester Exam");
        AssessmentSchedule.subjectsArray.add("Mathematics");
        AssessmentSchedule.assessmentDescriptionsArray.add("Algebra, Geometry, Calculus");
        AssessmentSchedule.assessmentTypeArray.add("Exam");
        AssessmentSchedule.assessmentDurationsArray.add(3);
        AssessmentSchedule.assessmentStatusArray.add("Due");
        AssessmentSchedule.dueDateArray.add("2023-10-01");
        AssessmentSchedule.assessmentMarksArray.add("");
        AssessmentSchedule.lecturersArray.add("Mr. Smith");

        assertEquals(1, AssessmentSchedule.assessmentNamesArray.size());
        assertEquals("First Semester Exam", AssessmentSchedule.assessmentNamesArray.get(0));
        assertEquals("Mathematics", AssessmentSchedule.subjectsArray.get(0));
    }

    @Test
    public void testHandleAssessmentsDue() {
        // Simulating adding assessments
        AssessmentSchedule.assessmentNamesArray.add("First Semester Exam");
        AssessmentSchedule.assessmentStatusArray.add("Due");

        String expected = "First Semester Exam"; // It should show the due assessment
        assertEquals(expected, AssessmentSchedule.assessmentNamesArray.get(0));
    }

    @Test
    public void testShowAllAssessments() {
        // Adding assessments
        AssessmentSchedule.assessmentNamesArray.add("First Semester Exam");
        AssessmentSchedule.assessmentNamesArray.add("Final Exam");

        String allAssessments = String.join(", ", AssessmentSchedule.assessmentNamesArray);
        assertTrue(allAssessments.contains("First Semester Exam"));
        assertTrue(allAssessments.contains("Final Exam"));
    }

    @Test
    public void testDeleteAssessment() {
        // Adding assessments
        AssessmentSchedule.assessmentNamesArray.add("First Semester Exam");
        AssessmentSchedule.assessmentNamesArray.add("Final Exam");

        // Delete the assessment
        String assessmentToDelete = "First Semester Exam";
        boolean assessmentDeleted = false;

        for (int i = 0; i < AssessmentSchedule.assessmentNamesArray.size(); i++) {
            if (AssessmentSchedule.assessmentNamesArray.get(i).equalsIgnoreCase(assessmentToDelete)) {
                AssessmentSchedule.assessmentNamesArray.remove(i);
                assessmentDeleted = true;
                break;
            }
        }

        assertTrue(assessmentDeleted);
        assertEquals(1, AssessmentSchedule.assessmentNamesArray.size());
        assertFalse(AssessmentSchedule.assessmentNamesArray.contains("First Semester Exam"));
    }

    @Test
    public void testMarkAssessment() {
        // Adding assessment
        AssessmentSchedule.assessmentNamesArray.add("First Semester Exam");
        AssessmentSchedule.assessmentStatusArray.add("Due");

        // Marking the assessment
        String selectedAssessmentStr = "1";
        int selectedAssessment = Integer.parseInt(selectedAssessmentStr) - 1;

        AssessmentSchedule.assessmentStatusArray.set(selectedAssessment, "Marked");

        assertEquals("Marked", AssessmentSchedule.assessmentStatusArray.get(0));
    }
}

