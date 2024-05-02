package org.vaadin.example;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        String chatInvitation = ". Click to enter the Chat: ";
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user. " + chatInvitation;
        } else {
            return "Hello " + name + chatInvitation;
        }
    }

}
