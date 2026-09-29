public class Student {

    private String studentNumber;
    private String studentName;
    private String serviceType;
    private int estimatedServiceTime;

    public Student(String studentNumber, String studentName,
                   String serviceType, int estimatedServiceTime) {

        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getEstimatedServiceTime() {
        return estimatedServiceTime;
    }

    public void displayStudent() {
        System.out.println(
            "Student No: " + studentNumber +
            " | Name: " + studentName +
            " | Service: " + serviceType +
            " | Time: " + estimatedServiceTime + " min"
        );
    }
}