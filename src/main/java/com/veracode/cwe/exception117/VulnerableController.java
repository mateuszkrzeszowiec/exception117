package com.veracode.cwe.exception117;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class VulnerableController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    @ResponseBody
    String home(String dataFromRequest) {


        /**
         * To see CWE ID 117 run the app and use following URL: http://localhost:8080/?dataFromRequest=data%0a%0ddata
         * While it sounds crazy one would have to encode Exception#getMessage but also Exception#getCause#getMessage recursively!
         */

        log.error(dataFromRequest);

        try {
            doSomethingWithDataFromRequest(dataFromRequest);
        } catch (Exception e) {
            log.error("Oh oh, something went wrong: ", e);
        }

        try {
            doSomethingEvenMoreWickedWithDataFromRequest(dataFromRequest);
        } catch (Exception e) {
            log.error("Oh oh, something went wrong again: ", e);
        }


        return "Hello World!";
    }

    private void doSomethingWithDataFromRequest(String dataFromRequest) throws Exception {

        throw new Cwe117PayloadException(dataFromRequest);
    }

    private void doSomethingEvenMoreWickedWithDataFromRequest(String dataFromRequest) throws Exception {

        throw new Cwe117PayloadException("something went really really wrong", new Cwe117PayloadException("###### nested exception ######" + dataFromRequest));

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(VulnerableController.class, args);
    }
}

class Cwe117PayloadException extends Exception {
    Cwe117PayloadException(String message) {
        super(message);
    }

    Cwe117PayloadException(String message, Throwable cause) {
        super(message, cause);
    }
}