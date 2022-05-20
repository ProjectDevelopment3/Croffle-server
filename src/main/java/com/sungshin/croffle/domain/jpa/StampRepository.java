package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
