
package com.cse546.group29.imagerecognitionwebtier.repo;

import com.amazonaws.services.ec2.AmazonEC2Client;

public interface EC2Repo {

    public void createEC2Instance(String name, String amiId, int minCount, int maxCount);

    public void startEC2Instance(String instanceId);

    public void stopEC2Instance(String instanceId);

    /*Reference: https://docs.aws.amazon.com/code-samples/latest/catalog/java-ec2-src-main-java-aws-example-ec2-DescribeInstances.java.html
     */
    public int countNumberOfInstances();

    /*Reference:
    https://docs.aws.amazon.com/code-samples/latest/catalog/javav2-ec2-src-main-java-com-example-ec2-TerminateInstance.java.html
     */

    public void terminateEC2Instance(String instanceId);


}
