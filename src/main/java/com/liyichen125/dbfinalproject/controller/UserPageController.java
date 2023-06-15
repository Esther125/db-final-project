package com.liyichen125.dbfinalproject.controller;

import com.liyichen125.dbfinalproject.constant.ItemStatus;
import com.liyichen125.dbfinalproject.constant.ItemType;
import com.liyichen125.dbfinalproject.dto.ItemRequest;
import com.liyichen125.dbfinalproject.dto.UserLoginRequest;
import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.Item;
import com.liyichen125.dbfinalproject.model.Record;
import com.liyichen125.dbfinalproject.model.User;
import com.liyichen125.dbfinalproject.service.ItemService;
import com.liyichen125.dbfinalproject.service.RecordService;
import com.liyichen125.dbfinalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ItemService itemService;

    //使用者登入頁面
    @GetMapping("/users/login")
    public String showLoginForm(Model model) {
        model.addAttribute("UserLoginRequest", new UserLoginRequest());
        return "login";
    }


    //登入成功的歡迎頁面

    @GetMapping("/users/login-success")
    public String showHomepage(HttpSession session, Model model) {

        Integer userId = (Integer) session.getAttribute("user_id");
        if (userId == null) {
            return "redirect:/users/login"; // 如果用户未登录，重定向到登录页面
        }

        User user = userService.getUserById(userId);

        model.addAttribute("user", user);

        if ( user.getRole().toString().equals("STUDENT")) { // 假设角色值1表示学生
            return "student-homepage";
        } else if ( user.getRole().toString().equals("ADMIN")) { // 假设角色值2表示管理员
            return "admin-homepage";
        } else {
            return "redirect:/users/login"; // 如果角色无效，重定向回登录页面
        }
    }



//    @PostMapping("/users/login-success")
//    public String loginSuccess(HttpSession session,
//                               @ModelAttribute("UserLoginRequest") UserLoginRequest userLoginRequest,
//                               Model model) {
//
//        User user = userService.login(userLoginRequest);
//
//        // Store the user id in session after a successful login
//        session.setAttribute("user_id", user.getUser_id());
//
//        model.addAttribute("user", user);
//        session.setAttribute("user", user);
//
//
//        if (user.getRole().toString().equals("STUDENT")) { // 假设角色值1表示学生
//            return "student-homepage";
//        } else if (user.getRole().toString().equals("ADMIN")) { // 假设角色值2表示管理员
//            return "admin-homepage";
//        } else {
//            return "login"; // 如果角色无效，重定向回登录页面
//        }
//    }
@PostMapping("/users/login-success")
public String loginSuccess(HttpSession session,
                           @ModelAttribute("UserLoginRequest") UserLoginRequest userLoginRequest,
                           Model model) {

    User user;
    try {
        user = userService.login(userLoginRequest);
    } catch (ResponseStatusException e) {
        model.addAttribute("error", e.getReason()); // add the error message to the model
        return "login"; // return back to the login page
    }

    // Store the user id in session after a successful login
    session.setAttribute("user_id", user.getUser_id());

    model.addAttribute("user", user);
    session.setAttribute("user", user);

    if (user.getRole().toString().equals("STUDENT")) { // 假设角色值1表示学生
        return "student-homepage";
    } else if (user.getRole().toString().equals("ADMIN")) { // 假设角色值2表示管理员
        return "admin-homepage";
    } else {
        return "login"; // 如果角色无效，重定向回登录页面
    }
}



    //註冊介面
    @GetMapping("/users/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("UserRegisterRequest", new UserRegisterRequest());
        return "register";
    }

    //註冊成功
    @PostMapping("/users/register-success")
    public String registerSuccess(@ModelAttribute("UserRegisterRequest") UserRegisterRequest userRegisterRequest, Model model) {
        model.addAttribute("UserRegisterRequest", new UserRegisterRequest());
        Integer user_id = userService.register(userRegisterRequest);
        model.addAttribute("user_id", user_id);
        return "redirect:/users/login";
    }

    //會員管理頁面 - 管理員
    @GetMapping("/users/management")
    public String getAllItems2(Model model){
//        List<Item> itemList = itemService.getItems(type,status,search);
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "show-all-users";
    }

    //登出
    @GetMapping("/users/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/login";
    }

    //查看學生個人頁面 - 管理員
    @GetMapping("/users/management/{user_id}")
    public String showProfile(@PathVariable("user_id") Integer user_id, Model model) {
        User user = userService.getUserById(user_id);
        // 需要创建一个从Item对象到ItemRequest对象的转换方法
        List<Record> records = recordService.getRecordsByUserId(user.getUser_id());
        for (Record record : records) {
            Item item = itemService.getItemById(record.getItem_id());
            record.setItem(item);
        }

        model.addAttribute("records",records);
        model.addAttribute("user", user);
        return "admin-user-profile";
    }
    @GetMapping("/users/profile/{user_id}")
    public String showSelfProfile(HttpSession session, Model model) {
        //User user = userService.getUserById(user_id);
        User user = (User) session.getAttribute("user");
        // 需要创建一个从Item对象到ItemRequest对象的转换方法
        List<Record> records = recordService.getRecordsByUserId(user.getUser_id());
        for (Record record : records) {
            Item item = itemService.getItemById(record.getItem_id());
            record.setItem(item);
        }


        model.addAttribute("records",records);
        model.addAttribute("user", user);
        return "user-profile";
    }

}
