package com.example.code_generator.controller;

import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/codes")
@CrossOrigin("*")
public class CodeController {

    private final Map<String, String> codes = new HashMap<>();
    private final SecureRandom random = new SecureRandom();

    @PostMapping("/generate")
    public String generateCode(@RequestParam String username) {
        String code = String.format("%04d", random.nextInt(10000));
        codes.put(username, code);
        return code;
    }

    @GetMapping("/validate")
    public boolean validateCode(@RequestParam String username, @RequestParam String code) {
        return codes.containsKey(username) && codes.get(username).equals(code);
    }
}
