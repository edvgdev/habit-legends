package com.habitlegends.habitlegends.completion;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.habitlegends.habitlegends.quest.QuestService;
import com.habitlegends.habitlegends.statprogress.StatProgressService;
import com.habitlegends.habitlegends.user.UserService;
import com.habitlegends.habitlegends.userprogress.ProgressUpdateInfoDetails;
import com.habitlegends.habitlegends.userprogress.UserProgressService;

/**
 * Service class for handling quest completions
 */
@Service
public class QuestCompletionServiceImpl implements QuestCompletionService {

    private final QuestCompletionRepository questCompletionRepository;

    private final UserService userService;

    private final QuestService questService;

    private final UserProgressService userProgressService;

    private final StatProgressService statProgressService;

    private final ModelMapper modelMapper;

    public QuestCompletionServiceImpl(QuestCompletionRepository questCompletionRepository, UserService userService,
            QuestService questService, UserProgressService userProgressService,
            StatProgressService statProgressService, ModelMapper modelMapper) {
        this.questCompletionRepository = questCompletionRepository;
        this.userService = userService;
        this.questService = questService;
        this.userProgressService = userProgressService;
        this.statProgressService = statProgressService;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new quest completion
     * 
     * @param questCompletionDTO DTO containing the quest completion details
     * @return DTO containing the saved quest completion details
     */
    @Transactional
    @Override
    public CompletionDetails createQuestCompletion(QuestCompletionDTO questCompletionDTO) {

        QuestCompletion questCompletion = convertToEntity(questCompletionDTO);
        QuestCompletion savedQuestCompletion = questCompletionRepository.save(questCompletion);

        // Trigger progress update for the user progress
        List<ProgressUpdateInfoDetails> progressUpdateInfoDetails = userProgressService
                .triggerProgressUpdateOnCompletion(savedQuestCompletion.getUser().getId(),
                        savedQuestCompletion.getExpEarned());

        QuestCompletionDTO savedQuestCompletionDTO = modelMapper.map(savedQuestCompletion, QuestCompletionDTO.class);

        CompletionDetails completionDetails = new CompletionDetails(
                savedQuestCompletionDTO,
                progressUpdateInfoDetails);

        // Add points to the user quest stat
        statProgressService.addPoints(questCompletionDTO.getUserId(), questCompletionDTO.getQuestId());
        return completionDetails;
    }

    /**
     * Retrieves a list of quest completions based on the provided filter details
     * 
     * @param filterDetails Filter details to be used for filtering the quest
     *                      completions
     * @return List of quest completions that match the provided filter details
     */
    @Override
    public List<QuestCompletionDTO> getCompletionsByFilter(QuestCompletionFilterDetails filterDetails) {

        Specification<QuestCompletion> spec = QuestCompletionSpecification.withFilters(filterDetails.getUserId(),
                filterDetails.getQuestId(), filterDetails.getStartDate(),
                filterDetails.getEndDate(), filterDetails.getDescription());

        return questCompletionRepository.findAll(spec).stream()
                .map(questCompletion -> modelMapper.map(questCompletion, QuestCompletionDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Converts a quest completion DTO to a quest completion entity
     * 
     * @param questCompletionDTO DTO containing the quest completion details
     * @return quest completion entity
     */
    private QuestCompletion convertToEntity(QuestCompletionDTO questCompletionDTO) {
        QuestCompletion questCompletion = modelMapper.map(questCompletionDTO, QuestCompletion.class);
        questCompletion.setUser(userService.getUserById(questCompletionDTO.getUserId()));
        questCompletion.setQuest(questService.getQuestById(questCompletionDTO.getQuestId()));

        return questCompletion;
    }

}
