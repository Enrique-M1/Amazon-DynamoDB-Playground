package main;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class updateTable {
    public static void updateTable(DynamoDbClient client, String tableName, int numberOfAttributes){
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
                    /* TODO:
                        Figure out a way to get the attribute list from the previous record
                        in a specific table.
                    */
                    String keyName = "";
                    HashMap<String, AttributeValue> keyToGet = new HashMap<>();
                    Map<String, AttributeValue> row = new HashMap<>();
                    keyToGet.put(keyName, AttributeValue.builder().s().build());
                    Set<String> attributeNamesSet = item.keySet();
                    List<String> attributeNamesList = new ArrayList<>(attributeNamesSet);

                    GetItemRequest request = GetItemRequest.builder()
                            .tableName(tableName)
                            .key(keyToGet)
                            .build();


                    for (int count = 0; count < numberOfAttributes; count++) { // Iterate through each attribute
                        System.out.print("Enter attribute " + attributeName.get(count) + " value: ");
                        row.put(attributeName.get(count), AttributeValue.builder().s(input.nextLine()).build());
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
