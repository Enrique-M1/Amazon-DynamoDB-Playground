package main;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class create_table {
    public static void createTable(DynamoDbClient client){
        Scanner input = new Scanner(System.in);
        String flag; // Flag to determine if table should be created

        System.out.println("Create a table? (y or yes): ");
        flag = input.nextLine().toLowerCase();

        if (flag.equals("y") || flag.equals("yes")) { // Create a table
            String tableName;
            while (true) {
                System.out.print("Enter table name: ");
                tableName = input.nextLine();

                if (tableName.matches("[a-zA-Z0-9_.-]+")) { // If the name contains only letters, numbers,
                    break;                                      // and a couple specified symbols, continue
                }
                else { // Otherwise, error and ask again
                    System.out.println("Verify table name (Allowed characters: letters, numbers, underscore, " +
                            "period, and hyphen)");
                }
            }

            // Declare necessary variable
            int numberOfAttributes = 0;
            String name;
            ArrayList<String> attributeName = new ArrayList<>();

            while (true) { // Create column title and amount
                System.out.print("Enter Attribute name (or press Enter to stop): ");
                name = input.nextLine();

                if (name.isEmpty()) { // If the user does not enter another name, stop
                    break;
                }

                attributeName.add(name); // Add to list of attribute names
                numberOfAttributes++; // Increment number of attributes
            }

            if (numberOfAttributes == 0) { // If there are no attributes, error and return
                System.out.println("At least one attribute is required");
                input.close();
                return;
            }

            // Get the primary key (The UNIQUE part of the table. (This will define each row))
            System.out.print("Enter attribute to act as the primary key: (Defines each row. Must be UNIQUE) ");
            String primaryKey = input.nextLine();

            if (!attributeName.contains(primaryKey)) { // If the primary key is not in the list of attributes, error and return
                System.out.println("Primary key must be in the list of attributes you provided");
                input.close();
                return;
            }

            // Create the table
            try {
                CreateTableRequest creationRequest = CreateTableRequest.builder()
                        .tableName(tableName)
                        .keySchema(KeySchemaElement.builder()
                                .attributeName(primaryKey)
                                .keyType(KeyType.HASH)
                                .build())
                        .attributeDefinitions(AttributeDefinition.builder()
                                .attributeName(primaryKey)
                                .attributeType(ScalarAttributeType.S)
                                .build())
                        .build();
                client.createTable(creationRequest);
                System.out.println("Table created!");
            } catch (DynamoDbException e) { // If there is an error, show error and return
                System.err.println(e.getMessage());
                input.close();
                return;
            }

            // Update the table
            while (flag.equals("y") || flag.equals("yes")) { // Create each row
                Map<String, AttributeValue> row = new HashMap<>();
                for (int count = 0; count < numberOfAttributes; count++) { // Iterate through each column
                    System.out.print("Enter attribute " + attributeName.get(count) + " value: ");
                    row.put(attributeName.get(count), AttributeValue.builder().s(input.nextLine()).build());
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

            // Close the scanner
            input.close();
        }
    }
}
