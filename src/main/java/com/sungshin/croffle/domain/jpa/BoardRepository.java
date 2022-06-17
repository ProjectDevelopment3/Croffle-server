package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.board.Board;
import com.sungshin.croffle.dto.board.BoardListWrapper;
import com.sungshin.croffle.dto.board.BoardSearchWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @Query(value = "select b.id, b.title, u.nickname, b.modified_date modifiedDate, b.board_category category"+
            " from board b, user u where u.id=b.user_id order by b.id desc", nativeQuery = true)
    List<BoardListWrapper> findAllByUserIdByOrderByIdDesc();

    @Query(value = "select b.title, b.content, u.nickname, b.modified_date modifiedDate, b.board_category category" +
            " from board b, user u where b.id=:id and u.id=b.user_id", nativeQuery = true)
    Optional<BoardSearchWrapper> findByIdJoinUser(@Param("id")Long id);
}
