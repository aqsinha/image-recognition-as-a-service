package com.cse546.group29.imagerecognitionwebtier.service;

import com.cse546.group29.imagerecognitionwebtier.repo.EC2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EC2Service {

    @Autowired
    private static EC2Repo ec2Repo;

    public void createEC2Instance() {
        ec2Repo.createEC2Instance("","", 5,7);
    }

    public int countNumberOfRunningInstances() {
        return ec2Repo.countNumberOfInstances();
    }

}
