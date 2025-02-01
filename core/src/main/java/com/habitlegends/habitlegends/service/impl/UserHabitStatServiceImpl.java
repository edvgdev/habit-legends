package com.habitlegends.habitlegends.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.habitlegends.habitlegends.dto.HabitStatRewardDTO;
import com.habitlegends.habitlegends.dto.UserHabitStatDTO;
import com.habitlegends.habitlegends.entity.HabitStatReward;
import com.habitlegends.habitlegends.entity.Stat;
import com.habitlegends.habitlegends.entity.UserHabitStat;
import com.habitlegends.habitlegends.repository.UserHabitStatRepository;
import com.habitlegends.habitlegends.service.HabitStatRewardService;
import com.habitlegends.habitlegends.service.StatService;
import com.habitlegends.habitlegends.service.UserHabitStatService;
import com.habitlegends.habitlegends.service.UserService;

@Service
public class UserHabitStatServiceImpl implements UserHabitStatService {

    private final UserHabitStatRepository userHabitStatRepository;

    private final StatService statService;

    private final HabitStatRewardService habitStatRewardService;

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserHabitStatServiceImpl(UserHabitStatRepository userHabitStatRepository, StatService statService,
            HabitStatRewardService habitStatRewardService, UserService userService, ModelMapper modelMapper) {
        this.userHabitStatRepository = userHabitStatRepository;
        this.statService = statService;
        this.habitStatRewardService = habitStatRewardService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserHabitStatDTO> initializeUserHabitStatForNewUser(Long userId) {
        List<UserHabitStatDTO> userHabitStatDTOs = statService.getAllStats().stream()
                .map(stat -> {
                    UserHabitStatDTO userHabitStatDTO = new UserHabitStatDTO(
                            null,
                            userId,
                            stat.getId(),
                            0,
                            null,
                            null);
                    return createUserHabitStat(userHabitStatDTO);
                }).collect(Collectors.toList());

        return userHabitStatDTOs;
    }

    @Override
    public UserHabitStatDTO createUserHabitStat(UserHabitStatDTO userHabitStatDTO) {
        UserHabitStat userHabitStat = convertToEntity(userHabitStatDTO);
        UserHabitStat savedUserHabitStat = userHabitStatRepository.save(userHabitStat);
        return modelMapper.map(savedUserHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public void addPoints(Long userId, Long habitId) {
        List<HabitStatRewardDTO> habitStatRewardDTOs = habitStatRewardService.getHabitStatRewardsByHabitId(habitId);

        for (HabitStatRewardDTO habitStatRewardDTO : habitStatRewardDTOs) {
            UserHabitStat userHabitStat = userHabitStatRepository.findByUserIdAndStatId(userId,
                    habitStatRewardDTO.getStatId()).orElseGet(
                            () -> {
                                UserHabitStatDTO userHabitStatDTO = new UserHabitStatDTO(null, userId,
                                        habitStatRewardDTO.getStatId(), 0, null,
                                        null);

                                return modelMapper.map(userHabitStatDTO, UserHabitStat.class);
                            });

            userHabitStat.setCurrentPoints(userHabitStat.getCurrentPoints() + habitStatRewardDTO.getBaseStatReward());
            userHabitStatRepository.save(userHabitStat);
        }

    }

    @Override
    public UserHabitStatDTO updateUserHabitStat(Long id, UserHabitStatDTO userHabitStatDTO) {
        UserHabitStat userHabitStat = userHabitStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabitStat not found"));
        modelMapper.map(userHabitStatDTO, userHabitStat);
        UserHabitStat updatedUserHabitStat = userHabitStatRepository.save(userHabitStat);
        return modelMapper.map(updatedUserHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public UserHabitStatDTO getUserHabitStatById(Long id) {
        UserHabitStat userHabitStat = userHabitStatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserHabitStat not found"));
        return modelMapper.map(userHabitStat, UserHabitStatDTO.class);
    }

    @Override
    public List<UserHabitStatDTO> getAllUserHabitStats() {
        return userHabitStatRepository.findAll()
                .stream()
                .map(userHabitStat -> modelMapper.map(userHabitStat, UserHabitStatDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserHabitStat(Long id) {
        if (!userHabitStatRepository.existsById(id)) {
            throw new RuntimeException("UserHabitStat not found");
        }
        userHabitStatRepository.deleteById(id);
    }

    private UserHabitStat convertToEntity(UserHabitStatDTO userHabitStatDTO) {
        UserHabitStat userHabitStat = modelMapper.map(userHabitStatDTO, UserHabitStat.class);
        userHabitStat.setUser(userService.getUserById(userHabitStatDTO.getUserId()));
        userHabitStat.setStat(modelMapper.map(statService.getStatById(userHabitStatDTO.getStatId()), Stat.class));
        return userHabitStat;
    }
}
