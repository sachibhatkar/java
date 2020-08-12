import java.util.Scanner;

public class ISBN_83783{

    String first9DigCode;
    char checkSum;

    public ISBN_83783(String first9DigCodeInput) {
        first9DigCode = first9DigCodeInput;
        checkSum = this.calculateCheckSum();
    }
    public char getCheckSum() {

        return checkSum;
    }
    public String getFirst9Digits() {

        return first9DigCode;
    }

    public String  getTenChar() {

        return first9DigCode + Character.toString(checkSum);
    }

    public static boolean validateInput(String userInput) {
        if (userInput.length() == 9) {
            for (int i = 0; i < userInput.length(); i++) {
                Boolean flag = Character.isDigit(userInput.charAt(i));
                if (!flag)
                    return false;
            }
            return true;
        }

        return false;

    }

    private char calculateCheckSum() {
        long sum = 0;
        char val = '0';
        for (int i = 0; i < first9DigCode.length(); i++) {
            int num = Integer.parseInt(String.valueOf(first9DigCode.charAt(i)));
            sum = sum + num * (i + 1);
        }
        int reminder = (int) (sum % 11);

        System.out.print(reminder);
        if (reminder == 10)
            val = 'X';
        else
            val =  Character.forDigit(reminder, 10);

        return val;

    }

    public String formatWithHyphen() {
        first9DigCode = first9DigCode + checkSum;
        char separator = '-';
        String formattedIsbn = first9DigCode.substring(0,1 ) + separator + first9DigCode.substring(1, 4) + separator
                + first9DigCode.substring(4, 9) + separator + first9DigCode.substring(9);
        return formattedIsbn;

    }

    public static void main(String[] args) {
        String isbn;
        Scanner in = new Scanner(System.in);
        System.out.println("This program calculates the checksum of a 9-digit ISBN code " +
                "and displays the 10-digit ISBN.");

        System.out.print("Enter the 9 digits for the ISBN code or X to exit: ");
        isbn = in.next();
        while (!isbn.equals("X"))

        {
            boolean isValid = validateInput(isbn);

            if (!isValid)

            {
                System.out.println("The input ISBN code is not valid.");
            }

            else

            {

                ISBN_83783 isbnObj = new ISBN_83783(isbn);

                System.out.printf("%-40s%10s", "The 10-digit ISBN code is", isbnObj.first9DigCode);

                System.out.println();

                System.out.printf("%-40s%10s", "The hyphen-formatted ISBN code is", isbnObj.formatWithHyphen());

                System.out.println();

            }

            System.out.print("Enter the 9 digits for the ISBN code or X to exit: ");

            isbn = in.next();

        }

    }
}

