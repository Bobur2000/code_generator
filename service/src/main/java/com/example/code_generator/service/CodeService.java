package com.example.code_generator.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class CodeService {

    private static final String DIGITS = "0123456789";
    private static final int CODE_LENGTH = 4;
    private final SecureRandom random = new SecureRandom();

    private final Map<String, String> userCodes = new HashMap<>();

    // Генерация случайного четырехзначного кода
    public String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return code.toString();
    }

    // Регистрация пользователя с генерированным кодом
    public String registerUser(String username) {
        String code = generateCode();
        userCodes.put(username, code);
        return code;
    }

    // Проверка кода пользователя
    public boolean validateCode(String username, String code) {
        return userCodes.containsKey(username) && userCodes.get(username).equals(code);
    }
}
