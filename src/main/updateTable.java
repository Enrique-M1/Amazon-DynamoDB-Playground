package main;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class updateTable {
    public static void updateTable(DynamoDbClient client, String tableName){
        /* TODO:
            1.) Delete a row in the table
            2.) Delete a column in the table
            3.) Add a column to the table
            4.) Add a row to the table
        */
        Scanner input = new Scanner(System.in);
        String choice;

        // Ask User for what they want to do
        System.out.println("What would you like to do?");
        System.out.println("1.) Add an attribute (column) \n2.) Delete an attribute (column)\n"
                + "3.) Add a record (row)\n4.) Delete a record (row)\n5.) Exit\nChoice: ");

        choice = input.nextLine();

        while(true) {
            switch (choice) {
                // Add an attribute to the table
                case "1":
                    System.out.println(choice);
                    return;

                // Delete an attribute from the table
                case "2":
                    System.out.println(choice);
                    return;

                // Add a record to the table
                case "3":
                    System.out.println("What table would you like to add a record to?");
                    String updateTable = input.nextLine();
                    String flag = "y";

                    while (true) { // While we are adding records, continue

                        if (flag.equalsIgnoreCase("y")|| flag.equalsIgnoreCase("yes")) {
                            // Update the table
                            while (flag.equals("y") || flag.equals("yes")) { // Create each row
                                System.out.println("Enter the amount of attributes for this row: ");
                                int numberOfAttributes = Integer.parseInt(input.nextLine());
                                Map<String, AttributeValue> row = new HashMap<>();
                                String attributeName;


                                for (int count = 1; count < numberOfAttributes + 1; count++) { // Iterate through each column
                                    System.out.print("Enter attribute " + count + " value: ");
                                    System.out.println("Enter attribute name: ");
                                    attributeName = input.nextLine();

                                    row.put(attributeName, AttributeValue.builder().s(input.nextLine()).build());
                                }

                                // Put item in the table
                                PutItemRequest putRequest = PutItemRequest.builder()
                                        .tableName(tableName).item(row).build();

                                try { // Put the item in the table
                                    client.putItem(putRequest);
                                } catch (DynamoDbException e) {
                                    System.err.println(e.getMessage());
                                    return;
                                }
                                System.out.print("Add another? (y or yes): ");
                                flag = input.nextLine().toLowerCase();
                            }


                            System.out.println("Add another record? (y or yes)");
                            flag = input.nextLine();

                        } else { // Done adding records, break
                            break;
                        }
                    }

                    return;

                // Delete a record from the table
                case "4":
                    System.out.println(choice);
                    return;

                // Exit the program
                case "5":
                    System.out.println(choice);
                    return;

                // Invalid choice entered
                default:
                    System.out.println("Invalid choice\nPlease enter 1, 2, 3, 4 or 5");
                    break;
            }
        }
    }
}
