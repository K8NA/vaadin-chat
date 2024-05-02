package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("chat")
public class ChatView extends VerticalLayout {
    private final ChatService chatService = new ChatService();
    private final List<ChatService.ChatLine> chatLines = new ArrayList<>();

    public ChatView() {
        TextField userNameField = new TextField("Username");
        TextField messageField = new TextField("Message");
        Button sendButton = new Button("Send", event -> {
            String userName = userNameField.getValue() + ":";
            String message = messageField.getValue();
            if (!message.isEmpty()) {
                chatService.sendMessage(userName, message);
                messageField.clear();
            }
        });

        sendButton.addClickShortcut(Key.ENTER);

        Grid<ChatService.ChatLine> messageGrid = new Grid<>();
        messageGrid.addColumn(ChatService.ChatLine::getUserName).setHeader("Username").setFlexGrow(0);
        messageGrid.addColumn(ChatService.ChatLine::getMessage).setHeader("Messages");
        messageGrid.setItems(chatLines);

        chatService.registerObserver(chatLine -> getUI().ifPresent(ui -> ui.access(() -> {
            chatLines.add(chatLine);
            messageGrid.getDataProvider().refreshAll();
        })));

        add(userNameField, messageField, sendButton, messageGrid);
    }
}

