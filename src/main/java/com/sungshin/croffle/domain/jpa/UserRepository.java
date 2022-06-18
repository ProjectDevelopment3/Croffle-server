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

    @Query(value = "select u.id, u.name, s.stampCnt from user u" + " LEFT JOIN " + " stamp s" +
            " on u.id = s.userId where u.id=:id and s.cafeId=:cafeId", nativeQuery = true)
    StampUserInfoDto findUserById(@Param("id") Long id, @Param("cafeId")Long cafeId);
}
