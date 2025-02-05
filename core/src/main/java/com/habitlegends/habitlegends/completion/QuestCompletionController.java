package com.habitlegends.habitlegends.completion;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling quest completions
 */
@RestController
@RequestMapping("/api/habit-completion")
public class QuestCompletionController {

    private final QuestCompletionService questCompletionService;

    public QuestCompletionController(QuestCompletionService questCompletionService) {
        this.questCompletionService = questCompletionService;
    }

    /**
     * Creates a new habit completion
     * 
     * @param habitCompletionDTO DTO containing the habit completion details
     * @return DTO containing the saved habit completion details
     */
    @PostMapping
    public ResponseEntity<QuestCompletionDTO> createHabitCompletion(
            @RequestBody QuestCompletionDTO habitCompletionDTO) {
        return ResponseEntity.ok(questCompletionService.createHabitCompletion(habitCompletionDTO));
    }

    /**
     * Retrieves a list of quest completions based on the provided filter details
     * 
     * @param filterDetails DTO containing the filter details
     * @return List of DTOs containing the quest completion details
     */
    @GetMapping
    public List<QuestCompletionDTO> getQuestCompletions(QuestCompletionFilterDetails filterDetails) {
        return questCompletionService.getCompletionsByFilter(filterDetails);
    }

}
