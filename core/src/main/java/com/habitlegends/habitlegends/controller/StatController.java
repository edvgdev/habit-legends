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

import com.habitlegends.habitlegends.dto.StatDTO;
import com.habitlegends.habitlegends.service.StatService;

@RestController
@RequestMapping("/api/stat")
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @PostMapping
    public ResponseEntity<StatDTO> createStat(@RequestBody StatDTO statDTO) {
        return ResponseEntity.ok(statService.createStat(statDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatDTO> getStatById(@PathVariable Integer id) {
        return ResponseEntity.ok(statService.getStatById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatDTO>> getAllStats() {
        return ResponseEntity.ok(statService.getAllStats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatDTO> updateStat(@PathVariable Integer id, @RequestBody StatDTO statDTO) {
        return ResponseEntity.ok(statService.updateStat(id, statDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStat(@PathVariable Integer id) {
        statService.deleteStat(id);
        return ResponseEntity.noContent().build();
    }

}
