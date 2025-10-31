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

        // Create Table
        create_table.createTable(client);

        // Show table
        System.out.println("Show table? (y or yes): ");
        String flag = input.nextLine().toLowerCase();

        while (flag.equals("y") || flag.equals("yes")) {
            System.out.print("Enter table name: ");
            tableName = input.nextLine();
            print_table.printTable(tableName, client);

            System.out.println("After printing the table.");

            System.out.println("See another? (y or yes): ");
            flag = input.nextLine().toLowerCase();
        }

        // Terminate DynamoDB connection
        client.close();

        // Close the scanner
        input.close();
    }
}
