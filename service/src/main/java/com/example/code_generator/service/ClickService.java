package com.example.code_generator.service;

import com.example.code_generator.model.Click;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClickService {

    // Сохраняем клики для каждого пользователя
    private Map<String, List<Click>> userClicks = new HashMap<>();

    // Добавляем клик для пользователя
    public void addClick(String username, int x, int y) {
        userClicks.putIfAbsent(username, new ArrayList<>());
        userClicks.get(username).add(new Click(LocalDateTime.now(), x, y));
    }

    // Получаем историю кликов пользователя
    public List<Click> getClicks(String username) {
        return userClicks.getOrDefault(username, new ArrayList<>());
    }
}
