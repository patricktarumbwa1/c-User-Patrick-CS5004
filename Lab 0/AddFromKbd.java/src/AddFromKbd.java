import java.util.Scanner;

public class AddFromKbd {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the first integer: ");
        int first = input.nextInt();

        System.out.print("Enter the second integer: ");
        int second = input.nextInt();

        int sum = first + second;
        System.out.println("The sum is: " + sum);

        input.close();
    }
}