package com.sungshin.croffle.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Service
public class UserService {

    public boolean nicknameVerify(String nickname) {
        if (Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$", nickname)) {
            int length = nickname.length();
            if (length <= 20 && length >= 2) {
                return true;
            }
        }
        return false;
    }
}
