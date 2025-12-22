package main;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Map;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String tableName; // Temporary tablename for debugging
        Scanner input = new Scanner(System.in);
        connectDynamoDB link = new connectDynamoDB(); // Instantiate class to connect to DynamoDB


        // Connect DynamoDB Local
        DynamoDbClient client = link.connect();

        // Determine what the user wants to do
        System.out.println("What would you like to do?:\n1.)Create a table\n2.)View a table\n3.)Update/Remove a table\n" +
                "4.)Exit\nChoice(1, 2, 3, or 4 accepted):");
        int choice = input.nextInt();

        while (true) {
            switch (choice) {
                case 1: // Create a table
                    create_table.createTable(client);
                    break;

                case 2: // View a table
                    System.out.print("Enter table name: ");
                    tableName = input.nextLine();
                    print_table.printTable(tableName, client);
                    break;

                case 3: // Update/Remove a table
                    updateTable.updateTable(client);
                    break;

                case 4: // Exit
                    // Terminate DynamoDB connection
                    client.close();

                    // Close the scanner
                    input.close();
                    return;

                default: // Invalid choice
                    System.out.println("Invalid choice\nPlease enter 1, 2, 3, or 4");
                    break;
            }
            System.out.println("What would you like to do?:\n1.)Create a table\n2.)View a table\n3.)Update/Remove a table\n" +
                    "4.)Exit\nChoice(1, 2, 3, or 4 accepted):");
            choice = input.nextInt();
        }
    }
}


