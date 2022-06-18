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
    @Query(value = "select c.id, c.name, c.addr roadaddr, c.liked_count, ifnull(r.rate, 0) rate " +
            "from " +
            "((select c1.id, c1.addr, c1.name, ifnull(lc.count, 0) liked_count " +
                "from cafe c1 left join " +
                    "(select cafe_id, count(cafe_id) count from liked_cafe group by cafe_id) lc " +
                "on c1.id = lc.cafe_id group by c1.id)) c" +
            " left join " +
            "(SELECT cafe_id, ifnull(sum(rate) / count(*), 0) rate from review group by cafe_id) r" +
            " on c.id = r.cafe_id order by r.rate desc", nativeQuery = true)
    List<CafeRecommendWrapper> cafeRecommendOrderByReview();

    @Query(value = "select c.id, c.name, c.addr roadaddr, c.liked_count, ifnull(r.rate, 0) rate " +
            "from " +
            "((select c1.id, c1.addr, c1.name, ifnull(lc.count, 0) liked_count " +
                "from cafe c1 left join " +
                "(select cafe_id, count(cafe_id) count from liked_cafe group by cafe_id) lc " +
                "on c1.id = lc.cafe_id group by c1.id)) c" +
            " left join " +
            "(SELECT cafe_id, ifnull(sum(rate) / count(*), 0) rate from review group by cafe_id) r" +
            " on c.id = r.cafe_id order by c.liked_count desc", nativeQuery = true)
    List<CafeRecommendWrapper> cafeRecommendOrderByLiked();

    Optional<Cafe> findByIdAndChecked(Long id, boolean check);

    Optional<Cafe> findByName(String name);

    Optional<Cafe> findByAddr(String addr);

    List<Cafe> findAllByChecked(boolean check);

    List<Cafe> findAllByNameLikeAndChecked(String name, boolean check);
}
