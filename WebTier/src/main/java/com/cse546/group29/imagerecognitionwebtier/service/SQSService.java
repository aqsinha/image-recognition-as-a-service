package com.cse546.group29.imagerecognitionwebtier.service;

import com.cse546.group29.imagerecognitionwebtier.repo.SQSRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSService {

     @Autowired
     private SQSRepo sqsRepo;

     private static Logger LOGGER = LoggerFactory.getLogger(SQSService.class);

     public void createQueue(String queueName) {
          LOGGER.info("SQS SERVICE CLASS: createQueue start");
          sqsRepo.createQueue(queueName);
          LOGGER.info("SQS SERVICE CLASS: createQueue end");
     }

     public void sendMessage(String queueName, String messageBody) {
          LOGGER.info("SQS SERVICE CLASS: sendMessage start");
          sqsRepo.sendMessage(queueName,"Sample Message 1");
          sqsRepo.sendMessage(queueName, "Sample Message 2");
          LOGGER.info("SQS SERVICE CLASS: sendMessage end");
     }

     public void receiveMessage(String queueName) {
          LOGGER.info("SQS SERVICE CLASS: receiveMessage Start");
          sqsRepo.receiveMessage(queueName);
          LOGGER.info("SQS SERVICE CLASS: receiveMessage End");
     }

     public int countNumberOfMessages(String queueName) {
          LOGGER.info("SQS SERVICE CLASS: countNumberOfMessage START:");
          int cnt = sqsRepo.getNumberOfMessageInQueue(queueName);
          LOGGER.info("Number of message read from the queue"+cnt);
          LOGGER.info("SQS SERVICE CLASS: countNumberOfMessage END:");
          return cnt;
     }

}
