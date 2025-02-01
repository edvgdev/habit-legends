package com.habitlegends.habitlegends.service;

import java.util.List;

import com.habitlegends.habitlegends.dto.StatDTO;
import com.habitlegends.habitlegends.entity.Stat;

public interface StatService {
    StatDTO createStat(StatDTO statDTO);

    StatDTO getStatById(Integer id);

    List<StatDTO> getAllStatsDto();

    List<Stat> getAllStats();

    StatDTO updateStat(Integer id, StatDTO statDTO);

    void deleteStat(Integer id);
}
