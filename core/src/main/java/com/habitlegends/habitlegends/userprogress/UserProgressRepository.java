package com.habitlegends.habitlegends.userprogress;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    @Query("SELECT up FROM UserProgress up WHERE up.user.id = :userId")
    Optional<UserProgress> findByUserId(@Param("userId") Long userId);
}
