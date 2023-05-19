package booklib;

import booklib.Entity.BorrowRequest;
import booklib.Entity.Library;
import booklib.Entity.BorrowResponse;

import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;
import org.apache.log4j.Logger;


public class Main {
    static Library library = Library.getInstance();
    static PriorityQueue<BorrowRequest> borrowRequests =
            new PriorityQueue<>();
    static Logger logger = Logger.getLogger(Library.class.getName());

    public static void main(String[] args) {
        System.out.println("Welcome to Book Library");
        System.out.println("What would you like to do today?");

        //      Actions
        // Take BorrowRequest
        // Fulfill BurrowRequest
        // Initiate Book Return
        // Add Books to Library Catalogue

        while (true) {
            System.out.println("1. Take Borrow Request");
            System.out.println("2. Fulfill Burrow Request");
            System.out.println("3. Initiate Book Return");
            System.out.println("4. Add Books to Library Catalogue");
            System.out.println("0. Exit Program");

            int choice = getUserChoice(4);

            switch (choice) {
                case 1:
                    takeBorrowRequest();
                    break;
                case 2:
                    fulfilBorrowRequest();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public static int getUserChoice(int max) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(": ");

            int choice;

            try {
                choice = sc.nextInt();
                if (choice >= 0 && choice <= max)
                    return  choice;
                System.out.format("Pls choose number between (0 - %d)", max);
                logger.error("Invalid choice number input");
            } catch (InputMismatchException e) {
                logger.error(e.getMessage());
                System.out.println("Pls Enter a number");
                sc.nextLine();
            }
        }
    }

    public static void takeBorrowRequest() {
        Scanner sc = new Scanner(System.in);
        System.out.print("What is the title of the book you want to borrow?: ");
        String title = sc.nextLine();

        System.out.print("What is your name?: ");
        String name = sc.nextLine();

        System.out.print("Pls Provide your contact Number: ");
        String contact = sc.nextLine();

        System.out.println("Are you a Teacher, Senior Student or Junior Student?");
        System.out.println("1. Teacher");
        System.out.println("2. Senior Student");
        System.out.println("3. Junior Student");

        String type;

        while (true) {
            System.out.print(": ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> type = "TEACHER";
                case "2" -> type = "SENIOR-STUDENT";
                case "3" -> type = "JUNIOR-STUDENT";
                default -> {
                    System.out.println("Invalid input, pls choice (1 - 3)");
                    continue;
                }
            }
            break;
        }

        int noOfDays;
        while (true) {
            System.out.print("How many days do you want to borrow the books for? ");
            try {
                noOfDays = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input, pls enter a number");
                sc.nextInt();
            }
        }

        BorrowRequest borrowRequest = new BorrowRequest()
                .bookTitle(title)
                .borrower(name, contact)
                .personType(type)
                .borrowDuration(noOfDays);

        borrowRequests.add(borrowRequest);

        System.out.println("Your Request have been successfully Taken\n");
    }

    public static void fulfilBorrowRequest() {
        BorrowRequest request;

        while (!borrowRequests.isEmpty()) {
            request = borrowRequests.poll();
            handleRequest(request);
        }
    }

    public static void handleRequest(BorrowRequest request) {
        BorrowResponse response = library.borrowBook(request);

        System.out.println("\n");

        if (!response.bookAvailable()) {
            System.out.println(request.getPersonName());
            System.out.println("Sorry, the book you requested for is not available");
            System.out.println("Please check back another time!!!\n\n");
        } else {
            System.out.println("Here is the book you requested for " + request.getPersonName());
            System.out.println("Id: " + response.borrowID());
            System.out.println("Expected Return Date: " + response.expectedReturnDate() + "\n\n");
        }
    }
}