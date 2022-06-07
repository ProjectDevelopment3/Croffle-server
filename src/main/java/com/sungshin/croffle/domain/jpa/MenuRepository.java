package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository  extends JpaRepository<Menu,Long> {
    List<Menu> findByCafeId(Long cafeId);
}
