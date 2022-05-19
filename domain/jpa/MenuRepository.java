package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository  extends JpaRepository<Menu,Long> {
}
