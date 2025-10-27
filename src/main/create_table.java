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

            if (!attributeName.contains(primaryKey)) {
                System.out.println("Primary key must be in the list of attributes you provided");
                input.close();
                return;
            }

            // Create the table
            try {
                CreateTableRequest creationRequest = CreateTableRequest.builder()
                        .tableName(tableName)
                        .keySchema(KeySchemaElement.builder()
                                .attibuteName()
                        )
            }

            while (flag.equals("y") || flag.equals("yes")) { // Create each row
                for (int count = 0; count < numberOfAttributes; count++) { // Iterate through each column
                    System.out.print("Enter attribute " + count + " value: ");
                    table.put(attributeName.get(count), AttributeValue.builder().s(input.nextLine())
                            .build()
                    );
                }
                System.out.print("Create another row? (y or yes): ");
                flag = input.nextLine().toLowerCase();
            }

            // Get the name of the table, add everything, and send the request to create the table
            System.out.print("Enter table name: ");
            PutItemRequest request = PutItemRequest.builder()
                    .tableName(input.nextLine())
                    .item(table)
                    .build();

            client.putItem(request);

            System.out.println("Table created!");

            // Close the scanner
            input.close();
        }
        else { // Otherwise, terminate
            return;
        }
    }
}
