package com.habitlegends.habitlegends.statprogress;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatProgressRepository extends JpaRepository<StatProgress, Long> {

    @Query("SELECT uhs FROM StatProgress uhs WHERE uhs.user.id = :userId AND uhs.stat.id = :statId")
    Optional<StatProgress> findByUserIdAndStatId(@Param("userId") Long userId, @Param("statId") Integer statId);

    @Query("SELECT uhs FROM StatProgress uhs WHERE uhs.user.id = :userId")
    List<StatProgress> findByUserId(@Param("userId") Long userId);

}
