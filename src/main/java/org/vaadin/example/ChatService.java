package org.vaadin.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChatService {
    // Inner class for chat messages
    public static class ChatLine {
        private final String userName;
        private final String message;

        public ChatLine(String userName, String message) {
            this.userName = userName;
            this.message = message;
        }

        public String getUserName() {
            return userName;
        }

        public String getMessage() {
            return message;
        }
    }

    private final List<Consumer<ChatLine>> observers = new ArrayList<>();

    // Method to register observers
    public void registerObserver(Consumer<ChatLine> observer) {
        observers.add(observer);
    }

    // Method for receiving a user message and broadcasting it to other observers
    public void sendMessage(String userName, String message) {
        ChatLine chatLine = new ChatLine(userName, message);
        observers.forEach(observer -> observer.accept(chatLine));
    }
}
