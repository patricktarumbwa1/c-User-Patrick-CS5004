// This program reads student data from a file and shows it on the screen
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentReader {
  public static void main(String[] args) {

    // Make an ArrayList to store all students
    ArrayList<Student> students = new ArrayList<Student>();

    try {
      // Open the students.txt file
      File file = new File("students.txt");
      Scanner input = new Scanner(file);

      int lineNumber = 1;

      // Read the file line by line
      while (input.hasNextLine()) {
        String line = input.nextLine();
        String[] parts = line.split(" "); // Split each word by space

        // Check that the line has all 4 parts
        if (parts.length == 4) {
          String firstName = parts[0];
          String lastName = parts[1];
          String id = parts[2];
          String email = parts[3];

          // Make a new Student object and add it to the list
          Student s = new Student(firstName, lastName, id, email);
          students.add(s);
        } else {
          System.out.println("Skipping bad line " + lineNumber);
        }

        lineNumber++;
      }

      // Close the file
      input.close();

      // Print all students
      System.out.println("Student List:");
      for (int i = 0; i < students.size(); i++) {
        System.out.print((i + 1) + ". ");
        students.get(i).printInfo();
      }

    } catch (FileNotFoundException e) {
      System.out.println("students.txt not found!");
    }
  }
}
