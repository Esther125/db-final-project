package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Message;
import com.liyichen125.dbfinalproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

@Controller
public class MessagePageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/add-message")
    public String showAddItemForm(Model model) {
        model.addAttribute("MessageRequest", new MessageRequest());

        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages",messages);
        return "add-message";
    }


    @PostMapping("/add-message-success")
    public String addMessageSuccess(@ModelAttribute("MessageRequest") MessageRequest messageRequest, Model model) {
        model.addAttribute("MessageRequest", new MessageRequest());
        Integer message_id = messageService.createMessage(messageRequest);
        model.addAttribute("message_id", message_id);
        return "redirect:/add-message";
    }

    @DeleteMapping("/delete-message/{messageId}")
    @ResponseBody
    public ResponseEntity<String> deleteMessage(@PathVariable Integer messageId) {
        try {
            messageService.deleteMessage(messageId);
            return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete message", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
