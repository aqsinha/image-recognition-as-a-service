package com.cse546.group29.imagerecognitionwebtier.repo;

import com.amazonaws.services.sqs.AmazonSQSClient;

/* References: https://www.baeldung.com/aws-queues-java
               https://docs.aws.amazon.com/AWSSimpleQueueService/latest/APIReference/API_GetQueueAttributes.html
               https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/sqs/src/main/java/com/example/sqs
 */

public interface SQSRepo {

    public void createQueue(String queueName);

    public void sendMessage(String queueName, String message);

    public void receiveMessage(String message);

    public void deleteQueue(String queueName);

    public int getNumberOfMessageInQueue(String queueName);

}
