package com.habitlegends.habitlegends.service;

import java.util.List;
import java.util.Optional;

import com.habitlegends.habitlegends.dto.RankDTO;

public interface RankService {
    RankDTO createRank(RankDTO rankDTO);

    RankDTO getRankById(Integer id);

    List<RankDTO> getAllRanks();

    RankDTO updateRank(Integer id, RankDTO habitDTO);

    void deleteRank(Integer id);

    Optional<RankDTO> determineRank(Integer exp);
}
