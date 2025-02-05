package com.habitlegends.habitlegends.completion;

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

/**
 * Service class for handling quest completions
 */
@Service
public class QuestCompletionServiceImpl implements QuestCompletionService {

    private final QuestCompletionRepository questCompletionRepository;

    private final UserService userService;

    private final HabitService habitService;

    private final UserProgressService userProgressService;

    private final UserHabitStatService userHabitStatService;

    private final ModelMapper modelMapper;

    public QuestCompletionServiceImpl(QuestCompletionRepository questCompletionRepository, UserService userService,
            HabitService habitService, UserProgressService userProgressService,
            UserHabitStatService userHabitStatService, ModelMapper modelMapper) {
        this.questCompletionRepository = questCompletionRepository;
        this.userService = userService;
        this.habitService = habitService;
        this.userProgressService = userProgressService;
        this.userHabitStatService = userHabitStatService;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new habit completion
     * 
     * @param habitCompletionDTO DTO containing the habit completion details
     * @return DTO containing the saved habit completion details
     */
    @Transactional
    @Override
    public QuestCompletionDTO createHabitCompletion(QuestCompletionDTO habitCompletionDTO) {

        QuestCompletion habitCompletion = convertToEntity(habitCompletionDTO);
        QuestCompletion savedHabitCompletion = questCompletionRepository.save(habitCompletion);

        // Trigger progress update for the user progress
        userProgressService.triggerProgressUpdateOnCompletion(savedHabitCompletion.getUser().getId(),
                savedHabitCompletion.getExpEarned());

        // Add points to the user habit stat
        userHabitStatService.addPoints(habitCompletionDTO.getUserId(), habitCompletionDTO.getHabitId());
        return modelMapper.map(savedHabitCompletion, QuestCompletionDTO.class);
    }

    /**
     * Retrieves a list of habit completions based on the provided filter details
     * 
     * @param filterDetails Filter details to be used for filtering the habit
     *                      completions
     * @return List of habit completions that match the provided filter details
     */
    @Override
    public List<QuestCompletionDTO> getCompletionsByFilter(QuestCompletionFilterDetails filterDetails) {

        Specification<QuestCompletion> spec = QuestCompletionSpecification.withFilters(filterDetails.getUserId(),
                filterDetails.getHabitId(), filterDetails.getStartDate(),
                filterDetails.getEndDate(), filterDetails.getDescription());

        return questCompletionRepository.findAll(spec).stream()
                .map(habitCompletion -> modelMapper.map(habitCompletion, QuestCompletionDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Converts a habit completion DTO to a habit completion entity
     * 
     * @param habitCompletionDTO DTO containing the habit completion details
     * @return Habit completion entity
     */
    private QuestCompletion convertToEntity(QuestCompletionDTO habitCompletionDTO) {
        QuestCompletion habitCompletion = modelMapper.map(habitCompletionDTO, QuestCompletion.class);
        habitCompletion.setUser(userService.getUserById(habitCompletionDTO.getUserId()));
        habitCompletion.setHabit(habitService.getHabitById(habitCompletionDTO.getHabitId()));

        return habitCompletion;
    }

}
