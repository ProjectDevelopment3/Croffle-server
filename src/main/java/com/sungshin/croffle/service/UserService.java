package com.sungshin.croffle.service;

import com.sungshin.croffle.domain.jpa.UserRepository;
import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 user id 입니다."));
        return UserDto.builder()
                .id(id)
                .nickname(user.getNickname())
                .build();
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

    public UserDto nicknameEdit(Long userid, String nickname) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 user id 입니다." + userid));
        user.nicknameEdit(nickname);
        return new UserDto().toDto(user);
    }
}
