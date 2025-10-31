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
    
    