package com.cse546.group29.imagerecognitionwebtier.resource;

import com.cse546.group29.imagerecognitionwebtier.service.SQSService;
import com.cse546.group29.imagerecognitionwebtier.util.CONSTANTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ImageRecognitionController {

    //Sample logger to be used throughout the project
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRecognitionController.class);

    @Autowired
    private SQSService sqsService;

    @GetMapping(path="/start", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startWebTier(){
        LOGGER.info("Started Web Tier");
        String message = "Web Tier is up and running";
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(path="/image/{id}", method=RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getImageId(@PathVariable("id") String id) {

        String message = "DUMMY_IMAGE_ID "+id;

        //Start calling methods to test

        //Test EC2 instances

        //Test SQS queue service
        //sqsService.createQueue(CONSTANTS.INPUTQUEUENAME);
        //sqsService.sendMessage(CONSTANTS.INPUTQUEUENAME,"Sample Message Body");
        sqsService.receiveMessage(CONSTANTS.INPUTQUEUENAME);
        sqsService.countNumberOfMessages(CONSTANTS.INPUTQUEUENAME);

        //Test
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }
}
