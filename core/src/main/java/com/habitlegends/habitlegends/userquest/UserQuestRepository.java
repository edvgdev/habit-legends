package com.habitlegends.habitlegends.userquest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {

    List<UserQuest> findByUserId(Long userId);
}
