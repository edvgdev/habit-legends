package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.RankDTO;
import com.habitlegends.habitlegends.entity.Rank;
import com.habitlegends.habitlegends.repository.RankRepository;
import com.habitlegends.habitlegends.service.RankService;

@Service
public class RankServiceImpl implements RankService {

    private final RankRepository rankRepository;

    private final ModelMapper modelMapper;

    public RankServiceImpl(RankRepository rankRepository, ModelMapper modelMapper) {
        this.rankRepository = rankRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RankDTO createRank(RankDTO rankDTO) {
        Rank rank = modelMapper.map(rankDTO, Rank.class);
        Rank savedRank = rankRepository.save(rank);
        return modelMapper.map(savedRank, RankDTO.class);
    }

    @Override
    public RankDTO updateRank(Integer id, RankDTO rankDTO) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rank not found"));
        modelMapper.map(rankDTO, rank);
        Rank updatedRank = rankRepository.save(rank);
        return modelMapper.map(updatedRank, RankDTO.class);
    }

    @Override
    public RankDTO getRankById(Integer id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rank not found"));
        return modelMapper.map(rank, RankDTO.class);
    }

    @Override
    public List<RankDTO> getAllRanks() {
        return rankRepository.findAll()
                .stream()
                .map(rank -> modelMapper.map(rank, RankDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRank(Integer id) {
        if (!rankRepository.existsById(id)) {
            throw new RuntimeException("Rank not found");
        }
        rankRepository.deleteById(id);
    }

}
