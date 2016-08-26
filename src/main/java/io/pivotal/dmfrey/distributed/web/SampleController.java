package io.pivotal.dmfrey.distributed.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dmfrey on 8/26/16.
 */
@Controller
@RequestMapping( "/sample" )
@Slf4j
public class SampleController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping( method = RequestMethod.GET )
    public String getForm() {
        log.debug( "getForm : enter" );

        log.debug( "getForm : exit" );
        return "/testSubmit.html";
    }

    @RequestMapping( method = RequestMethod.POST )
    public String postXml( @RequestParam final String message ) {
        log.debug( "postXml : enter" );

        log.info( "postXml : message = " + message );

        // Send a message
        MessageCreator messageCreator = session -> session.createTextMessage( message );

        log.info( "postXml : sending a new message" );
        jmsTemplate.send( "my-queue", messageCreator );

        log.debug( "postXml : exit" );
        return "redirect:/testSubmit.html";
    }

}
