/*
Source: https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
 */

package com.cse546.group29.imagerecognitionwebtier.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.cse546.group29.imagerecognitionwebtier.util.CONSTANTS;
import org.springframework.context.annotation.Configuration;

/*
   References: https://www.baeldung.com/aws-queues-java
               https://www.baeldung.com/ec2-java
 */

@Configuration
public class AWSConfig {

    public BasicAWSCredentials getAWSCredential() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(CONSTANTS.DEFAULT_ACCESSKEY, CONSTANTS.DEFAULT_SECRETACCESSKEY);
        return awsCreds;
    }

    public AmazonSQS getSQSClient() {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(getAWSCredential())).withRegion(Regions.US_EAST_1).build();
        return sqsClient;
    }

    public AmazonEC2 getEC2Client() {
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(getAWSCredential())).withRegion(Regions.US_EAST_1).build();
        return ec2Client;
    }
}
