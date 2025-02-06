package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for filtering quest completions
 */
public class QuestCompletionSpecification {

    /**
     * Filters quest completions by user ID
     * 
     * @param userId ID of the user
     * @return Specification object for filtering quest completions by user ID
     */
    public static Specification<QuestCompletion> withUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };

    }

    /**
     * Filters quest completions by quest ID
     * 
     * @param questId ID of the quest
     * @return Specification object for filtering quest completions by quest ID
     */
    public static Specification<QuestCompletion> withQuestId(Long questId) {
        return (root, query, criteriaBuilder) -> {
            if (questId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("quest").get("id"), questId);
        };
    }

    /**
     * Filters quest completions by completion date
     * 
     * @param startDate Start date of the completion
     * @param endDate   End date of the completion
     * @return Specification object for filtering quest completions by completion
     *         date
     */
    public static Specification<QuestCompletion> withCompletedAtBetween(LocalDateTime startDate,
            LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("completedAt"), startDate, endDate);
        };
    }

    /**
     * Filters quest completions by description
     * 
     * @param description Description of the completion
     * @return Specification object for filtering quest completions by description
     */
    public static Specification<QuestCompletion> withDescriptionLike(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                    "%" + description.toLowerCase() + "%");
        };
    }

    /**
     * Combines all the filters
     * 
     * @param userId      ID of the user
     * @param questId     ID of the quest
     * @param startDate   Start date of the completion
     * @param endDate     End date of the completion
     * @param description Description of the completion
     * @return Specification object for filtering quest completions
     */
    public static Specification<QuestCompletion> withFilters(Long userId, Long questId, LocalDateTime startDate,
            LocalDateTime endDate, String description) {
        return Specification.where(withUserId(userId)).and(withQuestId(questId))
                .and(withCompletedAtBetween(startDate, endDate))
                .and(withDescriptionLike(description));
    }

}
