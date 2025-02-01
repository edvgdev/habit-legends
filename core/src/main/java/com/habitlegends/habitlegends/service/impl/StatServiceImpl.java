package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.StatDTO;
import com.habitlegends.habitlegends.entity.Stat;
import com.habitlegends.habitlegends.repository.StatRepository;
import com.habitlegends.habitlegends.service.StatService;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StatDTO createStat(StatDTO statDTO) {
        Stat stat = modelMapper.map(statDTO, Stat.class);
        Stat savedStat = statRepository.save(stat);
        return modelMapper.map(savedStat, StatDTO.class);
    }

    @Override
    public StatDTO updateStat(Integer id, StatDTO statDTO) {
        Stat stat = statRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stat not found"));
        modelMapper.map(statDTO, stat);
        Stat updatedStat = statRepository.save(stat);
        return modelMapper.map(updatedStat, StatDTO.class);
    }

    @Override
    public StatDTO getStatById(Integer id) {
        Stat stat = statRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stat not found"));
        return modelMapper.map(stat, StatDTO.class);
    }

    @Override
    public List<StatDTO> getAllStatsDto() {
        return statRepository.findAll()
                .stream()
                .map(stat -> modelMapper.map(stat, StatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Stat> getAllStats() {
        return statRepository.findAll();
    }

    @Override
    public void deleteStat(Integer id) {
        if (!statRepository.existsById(id)) {
            throw new RuntimeException("Stat not found");
        }
        statRepository.deleteById(id);
    }
}
