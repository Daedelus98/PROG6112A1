import javax.swing.*;
import java.util.ArrayList;

public class AssessmentSchedule {

    // Declarations
    // Strings
    public static String commandOption;
    public static String assessmentName;
    public static String assessmentDescription;
    public static String assessmentSubject;
    public static String assessmentSubjectLecturer;
    public static String assessmentType;
    public static String assessmentDueDate;

    // ArrayLists
    public static ArrayList<String> subjectsArray = new ArrayList<>();
    public static ArrayList<String> lecturersArray = new ArrayList<>();
    public static ArrayList<String> assessmentNamesArray = new ArrayList<>();
    public static ArrayList<Integer> assessmentDurationsArray = new ArrayList<>();
    public static ArrayList<String> assessmentStatusArray = new ArrayList<>();
    public static ArrayList<String> assessmentDescriptionsArray = new ArrayList<>();
    public static ArrayList<String> assessmentTypeArray = new ArrayList<>();
    public static ArrayList<String> dueDateArray = new ArrayList<>();
    public static ArrayList<String> assessmentMarksArray = new ArrayList<>(); // New array to store marks

    public static void main(String[] args) {
        // Welcome
        JOptionPane.showMessageDialog(null, "Welcome to the Assessment Schedule Manager!", "Assessment Schedule Manager", JOptionPane.INFORMATION_MESSAGE);

        // Main Menu
        commandOption = """
                    Enter one of the following commands:
                    1 - Add Assessments
                    2 - Show Assessments Due
                    3 - Show Assessments Submitted
                    4 - Show Assessments Marked
                    5 - Show All Assessments
                    6 - Search by Assessment Name
                    7 - Search by Assessment Subject
                    8 - Search by Subject Lecturer
                    9 - Delete Assessment
                    0 - Quit""";
        int command = Integer.parseInt(JOptionPane.showInputDialog(null, commandOption, "Main Menu", JOptionPane.INFORMATION_MESSAGE));
        while (command != 0) {
            switch (command) {
                case 1: // Initiate Tasks
                    addAssessments();
                    break;
                case 2: // Assessments Due
                    handleAssessmentsDue();
                    break;
                case 3: // Assessments Submitted
                    handleAssessmentsSubmitted();
                    break;
                case 4: // Assessments Marked
                    showAssessmentsMarked();
                    break;
                case 5: // All Assessments
                    showAllAssessments();
                    break;
                case 6: // Search by Assessment Name
                    searchAssessmentName();
                    break;
                case 7: // Search by Assessment Subject
                    searchAssessmentSubject();
                    break;
                case 8: // Search by Subject Lecturer
                    searchSubjectLecturer();
                    break;
                case 9: // Delete Assessments
                    deleteAssessments();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid entry please try again", "Error!", JOptionPane.INFORMATION_MESSAGE);
            }
            command = Integer.parseInt(JOptionPane.showInputDialog(null, commandOption, "Main Menu", JOptionPane.INFORMATION_MESSAGE));
        }

        // End Application
        JOptionPane.showMessageDialog(null, "Thank you for using the Assessment Schedule Manager!", "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void addAssessments() {
        int numberOfAssessments = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of assessments to be added", "Add New Assessment:", JOptionPane.INFORMATION_MESSAGE));

        for (int i = 0; i < numberOfAssessments; i++) {
            assessmentName = JOptionPane.showInputDialog(null, "Enter Assessment Name", "New Assessment Details:", JOptionPane.INFORMATION_MESSAGE);

            assessmentDescription = JOptionPane.showInputDialog(null, "Enter Task Description (max 50 characters): ", "New Assessment Details:", JOptionPane.INFORMATION_MESSAGE);
            if (assessmentDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter an assessment description of less than 50 characters.", "Assessment Description Invalid!", JOptionPane.INFORMATION_MESSAGE);
                continue;
            }

            assessmentSubject = JOptionPane.showInputDialog(null, "Enter Assessment Subject: ", "New Assessment Details", JOptionPane.INFORMATION_MESSAGE);
            assessmentSubjectLecturer = JOptionPane.showInputDialog(null, "Enter Subject's Lecturer: ", "New Assessment Details", JOptionPane.INFORMATION_MESSAGE);

            int typeOption = JOptionPane.showOptionDialog(null, "Select Assessment Type:", "Assessment Type",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"ICE Task", "Assignment", "Test", "Exam"}, "ICE Task");

            int assessmentDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Assessment Duration (in hours): ", "New Assessment Details", JOptionPane.INFORMATION_MESSAGE));
            assessmentDueDate = JOptionPane.showInputDialog(null, "Enter Assessment Due Date: (Sit-in Date for Tests or Exams)", "New Assessment Details", JOptionPane.INFORMATION_MESSAGE);

            int statusOption = JOptionPane.showOptionDialog(null, "Select Assessment Status:", "Assessment Status",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Due", "Submitted", "Marked"}, "Due");

            // Store assessment details in ArrayLists
            subjectsArray.add(assessmentSubject);
            assessmentNamesArray.add(assessmentName);
            assessmentDescriptionsArray.add(assessmentDescription);
            assessmentTypeArray.add(new String[]{"ICE Task", "Assignment", "Test", "Exam"}[typeOption]);
            assessmentDurationsArray.add(assessmentDuration);
            assessmentStatusArray.add(new String[]{"Due", "Submitted", "Marked"}[statusOption]);
            dueDateArray.add(assessmentDueDate);
            assessmentMarksArray.add(""); // Initialize mark as empty
            lecturersArray.add(assessmentSubjectLecturer);

            if ("Marked".equals(assessmentStatusArray.get(assessmentStatusArray.size() - 1))) {
                String mark = JOptionPane.showInputDialog(null, "Enter the mark for this assessment:");
                assessmentMarksArray.set(assessmentMarksArray.size() - 1, mark); // Store the mark
            }

            JOptionPane.showMessageDialog(null, "Assessment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void handleAssessmentsDue() {
        StringBuilder assessmentsDue = new StringBuilder();
        boolean foundDue = false;
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if ("Due".equals(assessmentStatusArray.get(i))) {
                assessmentsDue.append(i + 1).append(". ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n\n");
                foundDue = true;
            }
        }
        if (foundDue) {
            String[] options = {"Update Status", "Back to Menu"};
            int response = JOptionPane.showOptionDialog(null, assessmentsDue.toString(), "Assessments Due:", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                updateAssessmentStatus("Due", "Submitted");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There are currently no assessments that are due", "Assessments Due:", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void handleAssessmentsSubmitted() {
        StringBuilder assessmentsSubmitted = new StringBuilder();
        boolean foundSubmitted = false;
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if ("Submitted".equals(assessmentStatusArray.get(i))) {
                assessmentsSubmitted.append(i + 1).append(". ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n\n");
                foundSubmitted = true;
            }
        }
        if (foundSubmitted) {
            String[] options = {"Update Status", "Back to Menu"};
            int response = JOptionPane.showOptionDialog(null, assessmentsSubmitted.toString(), "Assessments Submitted:", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                updateAssessmentStatus("Submitted", "Marked");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There are currently no assessments that are submitted", "Assessments Submitted:", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void updateAssessmentStatus(String currentStatus, String newStatus) {
        String selectedAssessmentStr = JOptionPane.showInputDialog(null, "Enter the number of the assessment to update:", "Update Assessment Status", JOptionPane.INFORMATION_MESSAGE);
        int selectedAssessment;
        try {
            selectedAssessment = Integer.parseInt(selectedAssessmentStr) - 1;
            if (selectedAssessment < 0 || selectedAssessment >= assessmentNamesArray.size() || !currentStatus.equals(assessmentStatusArray.get(selectedAssessment))) {
                JOptionPane.showMessageDialog(null, "Invalid selection or status mismatch.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            assessmentStatusArray.set(selectedAssessment, newStatus);
            if ("Marked".equals(newStatus)) {
                String mark = JOptionPane.showInputDialog(null, "Enter the mark for this assessment:");
                assessmentMarksArray.set(selectedAssessment, mark); // Store the mark
            }
            JOptionPane.showMessageDialog(null, "Assessment status updated to '" + newStatus + "'.", "Status Update", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showAssessmentsMarked() {
        StringBuilder assessmentsMarked = new StringBuilder();
        boolean foundMarked = false;
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if ("Marked".equals(assessmentStatusArray.get(i))) {
                assessmentsMarked.append(i + 1).append(". ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n")
                        .append("Mark: ").append(assessmentMarksArray.get(i)).append("\n\n");
                foundMarked = true;
            }
        }
        if (foundMarked) {
            JOptionPane.showMessageDialog(null, assessmentsMarked.toString(), "Assessments Marked:", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "There are currently no assessments that are marked", "Assessments Marked:", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void showAllAssessments() {
        StringBuilder allAssessments = new StringBuilder();
        for (int j = 0; j < assessmentNamesArray.size(); j++) {
            allAssessments.append("Task ").append(j + 1).append(":\n")
                    .append("Assessment Name: ").append(assessmentNamesArray.get(j)).append("\n")
                    .append("Subject: ").append(subjectsArray.get(j)).append("\n")
                    .append("Assessment Description: ").append(assessmentDescriptionsArray.get(j)).append("\n")
                    .append("Assessment Type: ").append(assessmentTypeArray.get(j)).append("\n")
                    .append("Assessment Duration: ").append(assessmentDurationsArray.get(j)).append(" hours\n")
                    .append("Assessment Status: ").append(assessmentStatusArray.get(j)).append("\n")
                    .append("Assessment Due Date: ").append(dueDateArray.get(j)).append("\n")
                    .append("Mark: ").append(assessmentMarksArray.get(j)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, allAssessments.toString(), "All Assessments:", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void searchAssessmentName() {
        String searchAssessmentName = JOptionPane.showInputDialog(null, "Enter Task Name to search:", "Task Name Search:", JOptionPane.INFORMATION_MESSAGE);
        boolean foundAssessmentName = false;
        StringBuilder searchAssessmentByName = new StringBuilder();
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if (assessmentNamesArray.get(i).equalsIgnoreCase(searchAssessmentName)) {
                searchAssessmentByName.append("Assessment Name: ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n")
                        .append("Mark: ").append(assessmentMarksArray.get(i)).append("\n\n");
                foundAssessmentName = true;
            }
        }
        if (foundAssessmentName) {
            JOptionPane.showMessageDialog(null, searchAssessmentByName.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No assessments found with the given Assessment Name", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void searchAssessmentSubject() {
        String searchSubject = JOptionPane.showInputDialog(null, "Enter Subject's Name to search:", "Assessment Subject Search", JOptionPane.INFORMATION_MESSAGE);
        boolean foundSubject = false;
        StringBuilder searchSubjectByName = new StringBuilder();
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if (subjectsArray.get(i).toLowerCase().contains(searchSubject.toLowerCase())) {
                searchSubjectByName.append("Assessment Name: ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n")
                        .append("Mark: ").append(assessmentMarksArray.get(i)).append("\n\n");
                foundSubject = true;
            }
        }
        if (foundSubject) {
            JOptionPane.showMessageDialog(null, searchSubjectByName.toString(), "Assessments for Subject: " + searchSubject, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No assessments found for the given Subject", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void searchSubjectLecturer() {
        String searchLecturer = JOptionPane.showInputDialog(null, "Enter Lecturer's Name to search:", "Assessment Lecturer Search", JOptionPane.INFORMATION_MESSAGE);
        boolean foundLecturer = false;
        StringBuilder searchLecturerByName = new StringBuilder();
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if (lecturersArray.get(i).toLowerCase().contains(searchLecturer.toLowerCase())) {
                searchLecturerByName.append("Assessment Name: ").append(assessmentNamesArray.get(i)).append("\n")
                        .append("Subject: ").append(subjectsArray.get(i)).append("\n")
                        .append("Assessment Type: ").append(assessmentTypeArray.get(i)).append("\n")
                        .append("Assessment Duration: ").append(assessmentDurationsArray.get(i)).append(" hours\n")
                        .append("Assessment Due Date: ").append(dueDateArray.get(i)).append("\n")
                        .append("Mark: ").append(assessmentMarksArray.get(i)).append("\n\n");
                foundLecturer = true;
            }
        }
        if (foundLecturer) {
            JOptionPane.showMessageDialog(null, searchLecturerByName.toString(), "Assessments for Lecturer: " + searchLecturer, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No assessments found for the given Lecturer", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void deleteAssessments() {
        String deleteAssessment = JOptionPane.showInputDialog(null, "Enter Task Name to delete:", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
        boolean assessmentDeleted = false;
        for (int i = 0; i < assessmentNamesArray.size(); i++) {
            if (assessmentNamesArray.get(i).equalsIgnoreCase(deleteAssessment)) {
                // Remove element from all ArrayLists
                subjectsArray.remove(i);
                assessmentNamesArray.remove(i);
                assessmentTypeArray.remove(i);
                assessmentDurationsArray.remove(i);
                assessmentStatusArray.remove(i);
                dueDateArray.remove(i);
                assessmentMarksArray.remove(i);

                assessmentDeleted = true;
                JOptionPane.showMessageDialog(null, "Task '" + deleteAssessment + "' deleted successfully", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        if (!assessmentDeleted) {
            JOptionPane.showMessageDialog(null, "The task '" + deleteAssessment + "' cannot be found. Please try again.", "Delete Task", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}







