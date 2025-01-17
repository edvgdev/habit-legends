package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.RankDTO;

public interface RankService {
    RankDTO createRank(RankDTO rankDTO);

    RankDTO getRankById(Integer id);

    List<RankDTO> getAllRanks();

    RankDTO updateRank(Integer id, RankDTO habitDTO);

    void deleteRank(Integer id);
}
