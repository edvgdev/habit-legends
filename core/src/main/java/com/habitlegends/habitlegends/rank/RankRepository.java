package com.habitlegends.habitlegends.rank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank, Integer> {
    List<Rank> findAllByOrderByMinExpAsc();
}
