package com.habitlegends.habitlegends.completion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Repository class for handling quest completions
 */
public interface QuestCompletionRepository
                extends JpaRepository<QuestCompletion, Long>, JpaSpecificationExecutor<QuestCompletion> {

}
