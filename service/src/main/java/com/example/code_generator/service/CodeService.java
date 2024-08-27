package com.example.code_generator.service;

import com.example.code_generator.model.CodeHistory;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeService {

    private static final String DIGITS = "0123456789";
    private static final int CODE_LENGTH = 4;
    private final SecureRandom random = new SecureRandom();

    private final Map<String, String> userCodes = new HashMap<>();
    private final List<CodeHistory> codeHistory = new ArrayList<>();

    public String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return code.toString();
    }

    public String registerUser(String username) {
        String code = generateCode();
        userCodes.put(username, code);
        codeHistory.add(new CodeHistory(username, code, LocalDateTime.now()));
        return code;
    }

    public boolean validateCode(String username, String code) {
        return userCodes.containsKey(username) && userCodes.get(username).equals(code);
    }

    public List<CodeHistory> getCodeHistory() {
        return codeHistory;
    }
}
