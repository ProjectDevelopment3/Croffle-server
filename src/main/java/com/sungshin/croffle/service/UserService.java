package com.sungshin.croffle.service;

import com.sungshin.croffle.config.auth.dto.SessionUser;
import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public Long findUserId() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        return sessionUser.getId();
    }

    public boolean nicknameVerify(String nickname) {
        if (Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z0-9]*$", nickname)) {
            int length = nickname.length();
            if (length <= 20 && length >= 2) {
                return true;
            }
        }
        return false;
    }

    public UserDto nicknameEdit(String nickname) {
        User user = userRepository.findById(findUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id 입니다." + findUserId()));
        return new UserDto().toDto(user);
    }
}
