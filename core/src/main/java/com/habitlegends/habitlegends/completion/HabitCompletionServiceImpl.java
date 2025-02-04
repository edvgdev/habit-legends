package com.habitlegends.habitlegends.completion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habitlegends.habitlegends.quest.HabitService;
import com.habitlegends.habitlegends.statprogress.UserHabitStatService;
import com.habitlegends.habitlegends.user.UserService;
import com.habitlegends.habitlegends.userprogress.UserProgressService;

@Service
public class HabitCompletionServiceImpl implements HabitCompletionService {

    private final HabitCompletionRepository habitCompletionRepository;

    private final UserService userService;

    private final HabitService habitService;

    private final UserProgressService userProgressService;

    private final UserHabitStatService userHabitStatService;

    private final ModelMapper modelMapper;

    public HabitCompletionServiceImpl(HabitCompletionRepository habitCompletionRepository, UserService userService,
            HabitService habitService, UserProgressService userProgressService,
            UserHabitStatService userHabitStatService, ModelMapper modelMapper) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.userService = userService;
        this.habitService = habitService;
        this.userProgressService = userProgressService;
        this.userHabitStatService = userHabitStatService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public HabitCompletionDTO createHabitCompletion(HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = convertToEntity(habitCompletionDTO);
        HabitCompletion savedHabitCompletion = habitCompletionRepository.save(habitCompletion);
        userProgressService.triggerProgressUpdateOnCompletion(savedHabitCompletion.getUser().getId(),
                savedHabitCompletion.getExpEarned());
        userHabitStatService.addPoints(habitCompletionDTO.getUserId(), habitCompletionDTO.getHabitId());
        return modelMapper.map(savedHabitCompletion, HabitCompletionDTO.class);
    }

    @Transactional
    @Override
    public HabitCompletionDTO updateHabitCompletion(Long id, HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitCompletion not found"));
        modelMapper.map(habitCompletionDTO, habitCompletion);
        HabitCompletion updatedHabitCompletion = habitCompletionRepository.save(habitCompletion);
        return modelMapper.map(updatedHabitCompletion, HabitCompletionDTO.class);
    }

    @Override
    public HabitCompletionDTO getHabitCompletionById(Long id) {
        HabitCompletion habitCompletion = habitCompletionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HabitCompletion not found"));
        return modelMapper.map(habitCompletion, HabitCompletionDTO.class);
    }

    @Override
    public List<HabitCompletionDTO> getAllHabitCompletions() {
        return habitCompletionRepository.findAll()
                .stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, HabitCompletionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitCompletionDTO> getAllHabitCompletionsOfUser(Long userId) {
        return habitCompletionRepository.findByUserId(userId)
                .stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, HabitCompletionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HabitCompletionDTO> getAllHabitCompletionsOfUserToday(Long userId) {
        LocalDate today = LocalDate.now();

        return habitCompletionRepository.findByUserIdAndCompletedAtDate(userId, today)
                .stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, HabitCompletionDTO.class))
                .collect(Collectors.toList());
    }

    public List<HabitCompletionDTO> getCompletionsByFilter(Long userId, Long habitId, LocalDateTime startDate,
            LocalDateTime endDate, String description) {
        Specification<HabitCompletion> spec = QuestCompletionSpecification.withFilters(userId, habitId, startDate,
                endDate, description, endDate);

        return habitCompletionRepository.findAll(spec).stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, HabitCompletionDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteHabitCompletion(Long id) {
        if (!habitCompletionRepository.existsById(id)) {
            throw new RuntimeException("HabitCompletion not found");
        }
        habitCompletionRepository.deleteById(id);
    }

    private HabitCompletion convertToEntity(HabitCompletionDTO habitCompletionDTO) {
        HabitCompletion habitCompletion = modelMapper.map(habitCompletionDTO, HabitCompletion.class);
        habitCompletion.setUser(userService.getUserById(habitCompletionDTO.getUserId()));
        habitCompletion.setHabit(habitService.getHabitById(habitCompletionDTO.getHabitId()));

        return habitCompletion;
    }

}
