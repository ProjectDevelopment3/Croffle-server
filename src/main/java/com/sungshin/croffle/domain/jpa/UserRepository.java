package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.user.User;
import com.sungshin.croffle.dto.user.StampUserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNaverId(String naverId);

    Optional<User> findByName(String name);

    Optional<User> findByPhone(String phone);

    @Query(value = "select u.id userId, u.name, s.stamp_cnt stampCnt from user u" + " LEFT JOIN " + " stamp s" +
            " on u.id = s.user_id where u.id=:id and s.cafe_id=:cafeId", nativeQuery = true)
    Optional<StampUserInfoDto> findUserById(@Param("id") Long id, @Param("cafeId")Long cafeId);
}
