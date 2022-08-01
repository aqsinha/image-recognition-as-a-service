package com.cse546.group29.imagerecognitionapptier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    private static Logger LOGGER = LoggerFactory.getLogger(DummyController.class);
    @GetMapping(path="/start", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> startAppTier(){
        LOGGER.info("Started App Tier");
        String message = "App Tier is up and running";
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
