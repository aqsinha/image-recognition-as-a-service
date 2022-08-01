package com.cse546.group29.imagerecognitionwebtier.repo;

import com.amazonaws.services.sqs.model.*;
import com.cse546.group29.imagerecognitionwebtier.config.AWSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/* References: https://www.baeldung.com/aws-queues-java
               https://docs.aws.amazon.com/AWSSimpleQueueService/latest/APIReference/API_GetQueueAttributes.html
               https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/sqs/src/main/java/com/example/sqs
 */

@Repository
public class SQSRepoImpl implements SQSRepo{

    private static Logger LOGGER = LoggerFactory.getLogger(SQSRepoImpl.class);

    @Autowired
    private AWSConfig awsConfig;

    @Override
    public void createQueue(String queueName) {
        LOGGER.info("SQSREPO IMPL CLASS: Queue creation started");
        CreateQueueRequest createStandardQueueRequest = new CreateQueueRequest(queueName);
        //standardQueueURL
        awsConfig.getSQSClient().createQueue(createStandardQueueRequest).getQueueUrl();
        LOGGER.info("SQSREPOIMPL CLASS: Queue creation end");
    }

    @Override
    public void sendMessage(String queueName, String message) {
        LOGGER.info("SQSREPOIMPLE CLASS: STARTED SEND MESSAGE IN SQS QUEUE"+ queueName);
        try
         {
             CreateQueueRequest request = new CreateQueueRequest();
             request.withQueueName(queueName);

             //Get Queue URL
             GetQueueUrlRequest getQueueRequest = new GetQueueUrlRequest();
             getQueueRequest.withQueueName(queueName);
             String queueUrl = awsConfig.getSQSClient().getQueueUrl(getQueueRequest).getQueueUrl();

             SendMessageRequest sendMessageRequest = new SendMessageRequest();
             sendMessageRequest.withQueueUrl(queueUrl).withMessageBody(message).withDelaySeconds(5);

             //Send one message
             awsConfig.getSQSClient().sendMessage(sendMessageRequest);

         }
         catch(Exception e){
             LOGGER.info("ERROR IN SEND MESSAGE SQS QUEUE");
             e.printStackTrace();
         }

         LOGGER.info("SQSREPOIMPL CLASS: SEND MESSAGE SQS ENDED");
    }

    @Override
    public void receiveMessage(String queueName) {
        LOGGER.info("SQSREPOIMPL CLASS: STARTED RECEIVE MESSAGE IN SQS QUEUE");
        try {
            CreateQueueRequest request = new CreateQueueRequest();
            request.withQueueName(queueName);

            //Get Queue URL
            GetQueueUrlRequest getQueueRequest = new GetQueueUrlRequest();
            getQueueRequest.withQueueName(queueName);
            String queueUrl = awsConfig.getSQSClient().getQueueUrl(getQueueRequest).getQueueUrl();

            ReceiveMessageRequest receiveRequest = new ReceiveMessageRequest();
            receiveRequest.withQueueUrl(queueUrl).withWaitTimeSeconds(10).withMaxNumberOfMessages(10);

            List<Message> messages = awsConfig.getSQSClient().receiveMessage(receiveRequest).getMessages();

                //Print out the messages
                for (Message m : messages) {
                    LOGGER.info("Messages are " + m.getBody());
                }
            }
            catch(Exception e) {
                LOGGER.info("EXCEPTION IN RECEIVE MESSAGE IN SQS QUEUE");
                e.printStackTrace();
            }
        LOGGER.info("SQSREPOIMPL CLASS: COMPLETED RECEIVE MESSAGE IN SQS QUEUE");
    }

    @Override
    public void deleteQueue(String queueName) {

    }

    @Override
    public int getNumberOfMessageInQueue(String queueName) {
        LOGGER.info("SQSREPOIMPL CLASS getNumberOfMessageInQueue started");
        GetQueueUrlRequest getQueueRequest = new GetQueueUrlRequest();
        getQueueRequest.withQueueName(queueName);
        String queueUrl = awsConfig.getSQSClient().getQueueUrl(getQueueRequest).getQueueUrl();

        GetQueueAttributesRequest getQueueAttributesRequest = new GetQueueAttributesRequest(queueUrl).withAttributeNames("All");
        GetQueueAttributesResult getQueueAttributesResult = awsConfig.getSQSClient().getQueueAttributes(getQueueAttributesRequest);
        int cnt = Integer.parseInt(getQueueAttributesResult.getAttributes().get("ApproximateNumberOfMessages"));
        LOGGER.info("Number of message in the queue is"+cnt);
        LOGGER.info("SQSREPOIMPL CLASS getNumberOfMessageInQueue ended");
        return cnt;
    }
}
