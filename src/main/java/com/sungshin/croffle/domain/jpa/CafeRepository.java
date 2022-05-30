package com.sungshin.croffle.domain.jpa;

import com.sungshin.croffle.domain.Cafe;
import com.sungshin.croffle.dto.cafe.CafeListDto;
import com.sungshin.croffle.dto.cafe.CafeRecommendDto;
import com.sungshin.croffle.dto.cafe.CafeRecommendWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Query(value = "select c.id, c.name, c.addr roadaddr, c.liked_count, r.rate from " +
            "(select c1.id, c1.addr, c1.name, count(*) liked_count from cafe c1 " +
            "inner join liked_cafe lc where c1.id = lc.cafe_id group by lc.cafe_id) c inner join " +
            "(SELECT cafe_id, sum(rate) / count(*) rate from review group by cafe_id) r" +
            " where c.id = r.cafe_id order by r.rate desc", nativeQuery = true)
    List<CafeRecommendWrapper> cafeRecommendOrderByReview();

    @Query(value = "select c.id, c.name, c.addr roadaddr, c.liked_count, r.rate from " +
            "(select c1.id, c1.addr, c1.name, count(*) liked_count from cafe c1 " +
            "inner join liked_cafe lc where c1.id = lc.cafe_id group by lc.cafe_id) c inner join " +
            "(SELECT cafe_id, sum(rate) / count(*) rate from review group by cafe_id) r" +
            " where c.id = r.cafe_id order by c.liked_count desc", nativeQuery = true)
    List<CafeRecommendWrapper> cafeRecommendOrderByLiked();

    Optional<Cafe> findByName(String name);

    List<Cafe> findAllByChecked(boolean check);
}
