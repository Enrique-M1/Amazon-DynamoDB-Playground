# Amazon-DynamoDB-Playground
I am trying to learn DynamoDB using AWS and Java in tandem. This is a work in progress to help me create tables, select, update, and other tasks that would help in my pursuit of a career.

# Setup and Installation
1.) I am going to assume that you already have Java installed.

2.) You will need to install the AWS SDK for Java using this link. (The most up-to-date version is currently V2)

    https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/setup-install.html

3.) After installing the SDK make sure that the following exists in this exact location: If it does not exist create it.
    
        IMPORTANT: I am on WINDOWS so I will be showing windows paths. For mac and linux you will 
        need to adjust accordingly.

    - C:\Users\"yourusername"\.aws\credentials          

    NOTE: The credentials file is NOT to have an extension. (credentials.md/credentials.txt/etc.)

4.) Once located, the credentials file should follow this format:
    
    [default]
    aws_access_key_id="your access key"
    aws_secret_access_key="your secret key"
    toolkit_artifact_guid="your guid"
    region="your region"

    NOTE: There can not be any extra lines after region. If there are, the program will not work.

5.) As to not incur costs, I recommend that you download Amazon DynamoDB Local. This is a free version of DynamoDB that you can run locally.

    https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html
    
    
# Running the Program

1.) You must start the DynamoDB Local server before running the program.
    
    - Open a command prompt
    - Navigate to the directory where you have the DynamoDB Local server
    - Type "java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar"
    - Press enter
    - You should see a message that says "Started DynamoDB Local, connect string is: http://localhost:8000"
    
    NOTE: If you get an error, make sure that you have the correct path to the DynamoDBLocal.jar file.
    NOTE: You may have 8000 already in use. If so, change the port number in the command AND in the 
    connectDynamoDB.java file. Then try again.
2.) Once you have the DynamoDB Local server running, you can run the program from main.java.