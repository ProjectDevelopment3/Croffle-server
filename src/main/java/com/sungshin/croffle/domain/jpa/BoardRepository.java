package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByIdDesc();
}
