package com.example.code_generator.controller;

import com.example.code_generator.service.CodeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private CodeService codeService;

    // Показать страницу логина
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    // Обработка регистрации нового пользователя
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, Model model) {
        String code = codeService.registerUser(username);
        model.addAttribute("username", username);
        model.addAttribute("code", code); // Отображаем код для демонстрации (в реальном приложении код отправляется пользователю)
        return "showCode"; // Перенаправление на страницу с отображением кода
    }

    // Обработка входа в систему
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String code, HttpSession session, Model model) {
        if (codeService.validateCode(username, code)) {
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(240); // Сессия активна 4 минуты
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid code. Please try again.");
            return "login";
        }
    }

    // Показать домашнюю страницу
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
            return "home";
        } else {
            return "redirect:/";
        }
    }
}
