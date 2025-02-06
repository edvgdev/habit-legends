package com.habitlegends.habitlegends.quest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habitlegends.habitlegends.statreward.QuestAndStatRewardsDetails;

@RestController
@RequestMapping("/api/quest")
public class QuestController {

    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping
    public ResponseEntity<QuestDetails> createQuest(
            @RequestBody QuestAndStatRewardsDetails questAndStatRewardsDetails) {
        return ResponseEntity.ok(questService.createQuest(questAndStatRewardsDetails));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestDetails>> getAllQuestDetails() {
        return ResponseEntity.ok(questService.getAllQuestDetails());
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestDetails> updateQuest(@PathVariable("id") Long id,
            @RequestBody QuestAndStatRewardsDetails questAndStatRewardsDetails) {
        return ResponseEntity.ok(questService.updateQuest(id, questAndStatRewardsDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuest(@PathVariable("id") Long id) {
        questService.deleteQuest(id);
        return ResponseEntity.noContent().build();
    }
}
