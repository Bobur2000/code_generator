package com.example.code_generator.controller;


import com.example.code_generator.model.Click;
import com.example.code_generator.service.ClickService;
import com.example.code_generator.service.CodeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private ClickService clickService;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestParam String username, Model model) {
//        String code = codeService.registerUser(username);
//        model.addAttribute("username", username);
//        model.addAttribute("code", code);
//        return "showCode";
//    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String code, HttpSession session, Model model) {
        if (codeService.validateCode(username, code)) {
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(240); // 4 минуты
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid code. Please try again.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            List<Click> clicks = clickService.getClicks(username);
            model.addAttribute("username", username);
            model.addAttribute("clicks", clicks);
            return "home";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/click")
    @ResponseBody
    public String recordClick(@RequestParam int x, @RequestParam int y, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            clickService.addClick(username, x, y);
            return "ok";
        }
        return "error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, Model model) {
        String code = codeService.registerUser(username);
        model.addAttribute("username", username);
        model.addAttribute("code", code);
        model.addAttribute("codeHistory", codeService.getCodeHistory());
        return "showCode";
    }

}
