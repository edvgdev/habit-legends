package com.habitlegends.habitlegends.completion;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;

public class QuestCompletionSpecification {

    public static Specification<HabitCompletion> withUserId(Long userId) {
        return (root, query, criteriaBuilder) -> {
            if (userId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        };

    }

    public static Specification<HabitCompletion> withHabitId(Long habitId) {
        return (root, query, criteriaBuilder) -> {
            if (habitId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("habit").get("id"), habitId);
        };
    }

    public static Specification<HabitCompletion> withCompletedAtBetween(LocalDateTime startDate,
            LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("completedAt"), startDate, endDate);
        };
    }

    public static Specification<HabitCompletion> withDescriptionLike(String description) {
        return (root, query, criteriaBuilder) -> {
            if (description == null || description.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                    "%" + description.toLowerCase() + "%");
        };
    }

    public static Specification<HabitCompletion> withFilters(Long userId, Long habitId, LocalDateTime startDate,
            LocalDateTime endDate, String description, LocalDateTime date) {
        return Specification.where(withUserId(userId)).and(withHabitId(habitId))
                .and(withCompletedAtBetween(startDate, endDate))
                .and(withDescriptionLike(description));
    }

}
