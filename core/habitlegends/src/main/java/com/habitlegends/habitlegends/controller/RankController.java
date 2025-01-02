package com.habitlegends.habitlegends.controller;

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

import com.habitlegends.habitlegends.dto.RankDTO;
import com.habitlegends.habitlegends.service.RankService;

@RestController
@RequestMapping("/api/rank")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @PostMapping
    public ResponseEntity<RankDTO> createRank(@RequestBody RankDTO rankDTO) {
        return ResponseEntity.ok(rankService.createRank(rankDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankDTO> getRankById(@PathVariable Integer id) {
        return ResponseEntity.ok(rankService.getRankById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RankDTO>> getAllRanks() {
        return ResponseEntity.ok(rankService.getAllRanks());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RankDTO> updateRank(@PathVariable Integer id, @RequestBody RankDTO rankDTO) {
        return ResponseEntity.ok(rankService.updateRank(id, rankDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRank(@PathVariable Integer id) {
        rankService.deleteRank(id);
        return ResponseEntity.noContent().build();
    }
}
