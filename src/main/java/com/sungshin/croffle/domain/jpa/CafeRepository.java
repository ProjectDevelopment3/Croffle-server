package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    //    List<CafeListDto> findAll()
    Optional<Cafe> findByName(String name);
}
