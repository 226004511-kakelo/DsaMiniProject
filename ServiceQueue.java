public class ServiceQueue {

    private Student[] queue;
    private int front;
    private int rear;
    private int size;

    public ServiceQueue(int capacity) {
        queue = new Student[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add a student to the queue
    public void enqueue(Student student) {

        if (size == queue.length) {
            System.out.println("Queue is full.");
            return;
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = student;
        size++;

        System.out.println("Student added to waiting queue.");
    }

    // Remove the next student
    public Student dequeue() {

        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }

        Student student = queue[front];
        queue[front] = null;

        front = (front + 1) % queue.length;
        size--;

        return student;
    }

    // View the next student without removing
    public Student peek() {

        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }

    // Check whether queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Display all waiting students
    public void displayQueue() {

        if (isEmpty()) {
            System.out.println("Waiting queue is empty.");
            return;
        }

        System.out.println("\n===== WAITING QUEUE =====");

        int index = front;

        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + ". ");
            queue[index].displayStudent();

            index = (index + 1) % queue.length;
        }

        System.out.println("=========================");
    }
}