package main;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.Map;

public class print_table {
    public static void printTable(String tableName, DynamoDbClient client){
        // Query the database
        ScanRequest request = ScanRequest.builder()
                .tableName(tableName)
                .build();

        // Get Response from the database
        ScanResponse response = client.scan(request);

        // Print to screen
        for (Map<String, AttributeValue> item : response.items()) { // Loop through response and format output
            for(Map.Entry<String, AttributeValue> entry : item.entrySet()){ // Loop through each "row" of the table
                String key = entry.getKey();
                AttributeValue value = entry.getValue();

                // Print out the key (Attribute Name) and value
                if (value.s() != null) { // If value is a string, Continue
                    System.out.println(key + ": " + value.s());
                } else if (value.n() != null) { // If value is a number, Continue
                    System.out.println(key + ": " + value.n());
                } else if (value.bool() != null) { // If value is a boolean, Continue
                    System.out.println(key + ": " + value.bool());
                } else {
                    System.out.println("Verify value type (Expected: String, Integer/Float, or Boolean)");
                }
            }
        }
    }
}
