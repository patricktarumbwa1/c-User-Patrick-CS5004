// This class represents one student
public class Student {
  String firstName;
  String lastName;
  String studentID;
  String email;

  // Constructor - this runs when we make a new Student
  public Student(String fName, String lName, String id, String mail) {
    firstName = fName;
    lastName = lName;
    studentID = id;
    email = mail;
  }

  // Method to print out a student's details
  public void printInfo() {
    System.out.println(firstName + " " + lastName + " (" + studentID + ") - " + email);
  }
}
