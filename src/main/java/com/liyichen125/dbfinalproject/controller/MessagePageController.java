package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.MessageRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Message;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;



@Controller
public class MessagePageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/add-message")
    public String showAddItemForm(HttpSession session,Model model) {
        model.addAttribute("MessageRequest", new MessageRequest());
        User user = (User) session.getAttribute("user");
        List<Message> messages = messageService.getMessages();
        model.addAttribute("messages", messages);
        if (user.getRole().toString().equals("STUDENT")) { // 假设角色值1表示学生
            return "student-add-message";
        } else if (user.getRole().toString().equals("ADMIN")) { // 假设角色值2表示管理员
            return "admin-add-message";
        }else{
            return "login";
        }
//
    }


    @PostMapping("/add-message-success")
    public String addMessageSuccess(@ModelAttribute("MessageRequest") MessageRequest messageRequest, Model model) {
//        model.addAttribute("MessageRequest", new MessageRequest());
        Integer message_id = messageService.createMessage(messageRequest);
        model.addAttribute("message_id", message_id);
        if (message_id != null) {
            // 添加一个标记到 session
            model.addAttribute("messageAdded", true);
        }
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
