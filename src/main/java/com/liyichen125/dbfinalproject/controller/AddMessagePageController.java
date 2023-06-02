package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddMessagePageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/add-message")
    public String showAddItemForm(Model model) {
        model.addAttribute("MessageRequest", new MessageRequest());
        return "add-message";
    }

    @PostMapping("/add-message-success")
    public String addMessageSuccess(@ModelAttribute("MessageRequest") MessageRequest messageRequest, Model model) {
        model.addAttribute("MessageRequest", new MessageRequest());
        Integer message_id = messageService.createMessage(messageRequest);
        model.addAttribute("message_id", message_id);
        return "add-message-success";
    }

}
