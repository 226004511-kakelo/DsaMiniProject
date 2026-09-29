import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ServiceQueue waitingQueue = new ServiceQueue(100);
    static StudentList studentRecords = new StudentList();

    static int[] dailyServiceTimes = new int[100];
    static int servicesCompleted = 0;

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("       DSA521S SERVICE CENTRE");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            System.out.print("Select option: ");

            int option = readInt();

            switch (option) {

                case 1:
                    addStudentToQueue();
                    break;

                case 2:
                    serveNextStudent();
                    break;

                case 3:
                    waitingQueue.displayQueue();
                    break;

                case 4:
                    addStudentRecord();
                    break;

                case 5:
                    studentRecords.displayStudents();
                    break;

                case 6:
                    searchStudent();
                    break;

                case 7:
                    removeStudentRecord();
                    break;

                case 8:
                    displayDailyStatistics();
                    break;

                case 9:
                    sortServiceTimes();
                    break;

                case 10:
                    SortingExperiment.runExperiment();
                    break;

                case 11:
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Display menu
    public static void displayMenu() {

        System.out.println("\n==========================================");
        System.out.println("          CAMPUS SERVICE CENTRE");
        System.out.println("==========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
        System.out.println("==========================================");
    }

    // Option 1
    public static void addStudentToQueue() {

        Student student = createStudent();

        waitingQueue.enqueue(student);
    }

    // Option 2
    public static void serveNextStudent() {

        Student student = waitingQueue.dequeue();

        if (student == null) {
            return;
        }

        System.out.println("\nServing student:");

        student.displayStudent();

        if (servicesCompleted < dailyServiceTimes.length) {

            dailyServiceTimes[servicesCompleted] =
                    student.getEstimatedServiceTime();

            servicesCompleted++;
        }

        System.out.println("Student has been served.");
    }

    // Option 4
    public static void addStudentRecord() {

        Student student = createStudent();

        System.out.println("\nChoose insertion position:");
        System.out.println("1. Beginning");
        System.out.println("2. End");
        System.out.println("3. Specific position");

        System.out.print("Choice: ");

        int choice = readInt();

        if (choice == 1) {

            studentRecords.insertAtBeginning(student);

        } else if (choice == 2) {

            studentRecords.insertAtEnd(student);

        } else if (choice == 3) {

            System.out.print("Enter position: ");

            int position = readInt();

            studentRecords.insertAtPosition(
                    student, position);

        } else {

            System.out.println("Invalid choice.");
        }
    }

    // Option 6
    public static void searchStudent() {

        System.out.print("Enter student number: ");

        String studentNumber = scanner.nextLine();

        Student student =
                studentRecords.searchStudent(studentNumber);

        if (student != null) {

            System.out.println("\nStudent found:");
            student.displayStudent();

        } else {

            System.out.println("Student record not found.");
        }
    }

    // Option 7
    public static void removeStudentRecord() {

        System.out.print("Enter student number: ");

        String studentNumber = scanner.nextLine();

        studentRecords.deleteStudent(studentNumber);
    }

    // Option 8
    public static void displayDailyStatistics() {

        if (servicesCompleted == 0) {

            System.out.println(
                "No students have been served yet."
            );

            return;
        }

        int totalStudents = servicesCompleted;
        int totalServiceTime = 0;
        int highest = dailyServiceTimes[0];
        int lowest = dailyServiceTimes[0];
        int longerThanTen = 0;

        for (int i = 0; i < servicesCompleted; i++) {

            int time = dailyServiceTimes[i];

            totalServiceTime += time;

            if (time > highest) {
                highest = time;
            }

            if (time < lowest) {
                lowest = time;
            }

            if (time > 10) {
                longerThanTen++;
            }
        }

        double average =
                (double) totalServiceTime / totalStudents;

        System.out.println("\n=================================");
        System.out.println("       DAILY STATISTICS");
        System.out.println("=================================");
        System.out.println(
            "Total students served: " + totalStudents
        );
        System.out.println(
            "Total service time: " + totalServiceTime + " minutes"
        );
        System.out.printf(
            "Average service time: %.2f minutes%n",
            average
        );
        System.out.println(
            "Highest service time: " + highest + " minutes"
        );
        System.out.println(
            "Lowest service time: " + lowest + " minutes"
        );
        System.out.println(
            "Services longer than 10 minutes: " +
            longerThanTen
        );
        System.out.println("=================================");
    }

    // Option 9
    public static void sortServiceTimes() {

        if (servicesCompleted == 0) {

            System.out.println(
                "No service times available."
            );

            return;
        }

        int[] array =
                new int[servicesCompleted];

        for (int i = 0; i < servicesCompleted; i++) {
            array[i] = dailyServiceTimes[i];
        }

        System.out.println("\nChoose sorting algorithm:");
        System.out.println("1. Selection Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Merge Sort");
        System.out.println("4. Quick Sort");

        System.out.print("Choice: ");

        int choice = readInt();

        SortResult result;

        if (choice == 1) {

            result =
                SortingAlgorithms.selectionSort(array);

        } else if (choice == 2) {

            result =
                SortingAlgorithms.insertionSort(array);

        } else if (choice == 3) {

            result =
                SortingAlgorithms.mergeSort(array);

        } else if (choice == 4) {

            result =
                SortingAlgorithms.quickSort(array);

        } else {

            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("\nSorted service times:");

        SortingAlgorithms.displayArray(array);

        System.out.println(
            "Comparisons: " + result.comparisons
        );

        System.out.println(
            "Swaps/Shifts: " + result.swaps
        );
    }

    // Create a student
    public static Student createStudent() {

        System.out.print("Enter student number: ");
        String number = scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter service type: ");
        String service = scanner.nextLine();

        System.out.print("Enter estimated service time (minutes): ");
        int time = readInt();

        return new Student(
                number,
                name,
                service,
                time
        );
    }

    // Read integer safely
    public static int readInt() {

        while (true) {

            try {

                String input = scanner.nextLine();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.print(
                    "Please enter a valid number: "
                );
            }
        }
    }
}