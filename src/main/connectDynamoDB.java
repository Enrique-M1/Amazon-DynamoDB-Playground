package main;


import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.regions.Region;

import java.net.URI;


public class connectDynamoDB {
    public DynamoDbClient connect() {
         return(DynamoDbClient.builder()
                 // Checks your .aws/credentials file to use Amazon DynamoDB
                 .credentialsProvider(ProfileCredentialsProvider.create())
                 .endpointOverride(URI.create("http://localhost:8000")) // Change here if 8000 is already taken
                 .region(Region.US_EAST_1)
                 .build()
         );
    }
}
