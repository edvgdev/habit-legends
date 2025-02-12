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
@RequestMapping("/api/quest-completion")
public class QuestCompletionController {

    private final QuestCompletionService questCompletionService;

    public QuestCompletionController(QuestCompletionService questCompletionService) {
        this.questCompletionService = questCompletionService;
    }

    /**
     * Creates a new quest completion
     * 
     * @param questCompletionDTO DTO containing the quest completion details
     * @return DTO containing the saved quest completion details
     */
    @PostMapping
    public ResponseEntity<CompletionDetails> createQuestCompletion(
            @RequestBody QuestCompletionDTO questCompletionDTO) {
        return ResponseEntity.ok(questCompletionService.createQuestCompletion(questCompletionDTO));
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
