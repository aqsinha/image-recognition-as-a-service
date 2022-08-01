package com.cse546.group29.imagerecognitionapptier.repo;

import com.amazonaws.services.ec2.model.*;
import com.cse546.group29.imagerecognitionapptier.config.AWSConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/*
 * Reference: Amazon DOCS Github Repo
 *    https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/java/example_code/ec2/src/main/java/aws/example/ec2
 *    https://www.baeldung.com/ec2-java
 */

@Repository
public class EC2RepoImpl implements EC2Repo {

    private static Logger LOGGER = LoggerFactory.getLogger(EC2RepoImpl.class);

    @Autowired
    private AWSConfig awsConfig;

    @Override
    public void createEC2Instance(String name, String amiId, int minCount, int maxCount) {
        LOGGER.info("Creating E2 instance with name:"+ name + "and AMI ID"+ amiId);
        RunInstancesRequest runRequest = new RunInstancesRequest();
        runRequest.withImageId(amiId).withInstanceType(InstanceType.T1Micro).withMinCount(minCount).withMaxCount(maxCount);
        RunInstancesResult result = awsConfig.getEC2Client().runInstances(runRequest);
        LOGGER.info("EC2 instance successfully started with RunInstancesResult:"+ result.toString());
    }

    @Override
    public void startEC2Instance(String instanceId) {
        LOGGER.info("Starting EC2 instance");
        StartInstancesRequest request = new StartInstancesRequest();
        request.withInstanceIds(instanceId);
        awsConfig.getEC2Client().startInstances(request);
        LOGGER.info("EC2 instance successfully started");
    }

    @Override
    public void stopEC2Instance(String instanceId) {
        LOGGER.info("Stopping Ec2 instance");
        StopInstancesRequest request = new StopInstancesRequest();
        request.withInstanceIds(instanceId);
        awsConfig.getEC2Client().stopInstances(request);
        LOGGER.info("Ec2 instance successfully stopped");
    }

    /*
      Reference :1) https://docs.aws.amazon.com/code-samples/latest/catalog/java-ec2-src-main-java-aws-example-ec2-DescribeInstances.java.html
                 2) https://docs.aws.amazon.com/code-samples/latest/catalog/java-ec2-src-main-java-aws-example-ec2-FindRunningInstances.java.html
     */

    @Override
    public int countNumberOfInstances() {
        LOGGER.info("Counting number of running instances");
        int cnt =0;

        try{
            Filter filter = new Filter("instance-state-name");
            filter.withValues("running");
            //Create a DescribeInstancesRequest
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            request.withFilters(filter);

            // Find the running instances
            DescribeInstancesResult response = awsConfig.getEC2Client().describeInstances(request);

            for (Reservation reservation : response.getReservations()){
                for (Instance instance : reservation.getInstances()) {
                    cnt++;
                }

            }} catch (Exception e) {
            LOGGER.info("Exception in countNumber of running instances");
            e.getStackTrace();
        }
        LOGGER.info("Exiting countNumberofInstances function");
        return cnt;
    }

    @Override
    public void terminateEC2Instance(String instanceId) {
        TerminateInstancesRequest request = new TerminateInstancesRequest();
        request.withInstanceIds(instanceId);
        awsConfig.getEC2Client().terminateInstances(request);
    }
}
