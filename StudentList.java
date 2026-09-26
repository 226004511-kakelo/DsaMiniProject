public class StudentList {

    private Node head;

    // Node class
    private class Node {

        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    // Insert at beginning
    public void insertAtBeginning(Student student) {

        Node newNode = new Node(student);

        newNode.next = head;
        head = newNode;

        System.out.println("Student inserted at the beginning.");
    }

    // Insert at end
    public void insertAtEnd(Student student) {

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            System.out.println("Student inserted at the end.");
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        System.out.println("Student inserted at the end.");
    }

    // Insert at a specific position
    public void insertAtPosition(Student student, int position) {

        if (position <= 1) {
            insertAtBeginning(student);
            return;
        }

        Node newNode = new Node(student);
        Node current = head;

        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Position does not exist.");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;

        System.out.println("Student inserted at position " + position + ".");
    }

    // Delete a student using student number
    public void deleteStudent(String studentNumber) {

        if (head == null) {
            System.out.println("Student list is empty.");
            return;
        }

        if (head.student.getStudentNumber().equals(studentNumber)) {
            head = head.next;
            System.out.println("Student record deleted.");
            return;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentNumber()
                    .equals(studentNumber)) {

                current.next = current.next.next;

                System.out.println("Student record deleted.");
                return;
            }

            current = current.next;
        }

        System.out.println("Student not found.");
    }

    // Search for a student
    public Student searchStudent(String studentNumber) {

        Node current = head;

        while (current != null) {

            if (current.student.getStudentNumber()
                    .equals(studentNumber)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Display all students
    public void displayStudents() {

        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n===== STUDENT SERVICE RECORDS =====");

        Node current = head;
        int position = 1;

        while (current != null) {

            System.out.print(position + ". ");
            current.student.displayStudent();

            current = current.next;
            position++;
        }

        System.out.println("===================================");
    }
}