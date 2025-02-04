package com.habitlegends.habitlegends.stat;

import java.util.List;

public interface StatService {
    StatDTO createStat(StatDTO statDTO);

    StatDTO getStatById(Integer id);

    List<StatDTO> getAllStatsDto();

    List<Stat> getAllStats();

    StatDTO updateStat(Integer id, StatDTO statDTO);

    void deleteStat(Integer id);
}
