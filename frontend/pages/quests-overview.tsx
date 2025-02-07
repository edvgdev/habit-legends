import { getAllQuestCompletions, getAllUserQuests } from '@/api/api';
import CompletionDetailsModal from '@/components/quest-overview/completion-details-modal';
import { QuestCompletion, QuestCompletionFilterDetails, UserQuestDetails } from '@/types/quest';
import useUserStore from '@/types/user';
import { DatePicker } from '@mui/x-date-pickers';
import dayjs, { Dayjs } from 'dayjs';
import React, { useEffect, useState } from 'react';

const QuestsOverview = () => {

    const [selectedDate, setSelectedDate] = useState<Dayjs>(dayjs());
    const [userQuests, setUserQuests] = useState<UserQuestDetails[]>([]);
    const [userQuestCompletions, setUserQuestCompletions] = useState<QuestCompletion[]>([]);
    const [completionDetailsModalOpen, setCompletionDetailsModalOpen] = useState(false);
    const [selectedQuestCompletion, setSelectedQuestCompletion] = useState<QuestCompletion | null>();
    const [selectedQuestName, setSelectedQuestName] = useState<string | null>();

    const { userProfile } = useUserStore();

    const daysInMonth = selectedDate.daysInMonth();
    const daysArray = Array.from({ length: daysInMonth }, (_, i) => i + 1);

    const getMonthRange = (year: number, month: number) => {
        const startDate = new Date(year, month - 1, 1, 0, 0, 0); // First day of the month at 00:00:00
        const endDate = new Date(year, month, 0, 23, 59, 59); // Last day of the month at 23:59:59

        const formattedStartDate = startDate.toISOString().split("T")[0] + "T00:00:00";
        const formattedEndDate = endDate.toISOString().split("T")[0] + "T23:59:59";

        return { formattedStartDate, formattedEndDate };
    };

    useEffect(() => {
        fetchUserQuests();
    }, [userProfile]);

    useEffect(() => {
        fetchQuestCompletions();
    }, [selectedDate]);

    const fetchUserQuests = async () => {
        if (!userProfile) return;
        try {
            const quests = await getAllUserQuests(userProfile.id);
            setUserQuests(quests);
        } catch (error) {
            console.error(error);
        }
    };

    const fetchQuestCompletions = async () => {
        if (!userProfile) return;
        try {
            const { formattedStartDate, formattedEndDate } = getMonthRange(selectedDate.year(), selectedDate.month() + 1);
            const filterDetails: QuestCompletionFilterDetails = {
                userId: userProfile.id,
                startDate: formattedStartDate,
                endDate: formattedEndDate,
                description: null,
                questId: null
            };
            const completions = await getAllQuestCompletions(filterDetails);
            setUserQuestCompletions(completions);
        } catch (error) {
            console.error(error);
        }
    };

    const showDescriptionModal = (completion: QuestCompletion, questName: string) => {
        if (!completion) return;
        setSelectedQuestCompletion(completion);
        setSelectedQuestName(questName);
        setCompletionDetailsModalOpen(true);
    };

    const closeDescriptionModal = () => {
        setCompletionDetailsModalOpen(false);
        setSelectedQuestCompletion(null);
        setSelectedQuestName(null);
    };

    return (
        <div className='quests-overview'>
            <div className='quests-overview-header'>
                <h2>Quests Overview</h2>
            </div>
            <div className='overview-container'>
                <DatePicker
                    label="Select month and year"
                    maxDate={dayjs()}
                    views={['year', 'month']}
                    yearsOrder="desc"
                    value={selectedDate}
                    onChange={(date) => setSelectedDate(date as Dayjs)}
                    slotProps={{
                        textField: {
                            sx: {
                                input: { color: "white" }, // Change text color
                                label: { color: "white" }, // Change label color
                                width: "300px",
                                "& .MuiOutlinedInput-root": {
                                    "& fieldset": { borderColor: "white" }, // Border color
                                    "&:hover fieldset": { borderColor: "white" },
                                    "&.Mui-focused fieldset": { borderColor: "#0BBD8B" },
                                },
                                "& .MuiSvgIcon-root": { color: "white" },
                                "&.Mui-focused .MuiSvgIcon-root": { color: "#0BBD8B" },
                                "& .MuiInputLabel-root": { color: "white" },
                                "& .MuiInputLabel-root.Mui-focused": { color: "#0BBD8B" },
                            },
                        },
                        popper: {
                            sx: {
                                "& .MuiPaper-root": {
                                    backgroundColor: "#181F30", // Background color of the popper
                                    color: "white", // Text color
                                    borderRadius: "10px",
                                    boxShadow: "0px 4px 10px rgba(0,0,0,0.3)",
                                },
                                "& .MuiPickersCalendarHeader-root": {
                                    backgroundColor: "#333", // Header background
                                    color: "#0BBD8B", // Header text color
                                },
                                "& .MuiPickersDay-root": {
                                    color: "white", // Day text color
                                },
                                "& .Mui-selected": {
                                    backgroundColor: "#0BBD8B !important", // Selected day color
                                    color: "black !important",
                                },
                                "& .MuiPickersDay-root:hover": {
                                    backgroundColor: "#00e673", // Hover effect on days
                                },
                            },
                        },
                    }}

                />
                <div className='overview-calendar-view'>
                    <div className='overview-grid-container'>
                        <div className='overview-grid-header'>
                            <div className='overview-empty-cell'></div>
                            {daysArray.map((day) => (
                                <div key={day} className='overview-grid-day'> {day}</div>
                            ))}

                        </div>

                        {userQuests.map((userQuest) => (
                            <div key={userQuest.questDetails.quest.id} className='overview-grid-row'>
                                <div className='overview-grid-row-title'>
                                    {userQuest.questDetails.quest.name}
                                </div>
                                {daysArray.map((day) => {

                                    const currentDate = dayjs();
                                    console.log(currentDate);
                                    const dayDate = selectedDate.date(day);

                                    const questCreationDate = dayjs(userQuest.questDetails.quest.createdAt);
                                    const isBeforeCreation = dayDate.isBefore(questCreationDate, 'day');

                                    const isPastDay = dayDate.isBefore(currentDate, 'day') || dayDate.isSame(currentDate, "day");

                                    const completion = userQuestCompletions.find((completion) => {
                                        const completionDate = dayjs(completion.completedAt);
                                        return completionDate.date() === day && completion.questId === userQuest.questDetails.quest.id;
                                    });



                                    return (
                                        <div
                                            key={day}
                                            className={`overview-grid-cell ${completion ? 'completed' : ''}`}
                                            onClick={() => showDescriptionModal(completion!, userQuest.questDetails.quest.name)}
                                        >
                                            {isBeforeCreation ? '-' : (isPastDay ? (completion ? '✅' : '❌') : '-')}
                                        </div>
                                    )
                                })}
                            </div>
                        ))}

                    </div>
                </div>
            </div>
            <CompletionDetailsModal
                isOpen={completionDetailsModalOpen}
                onClose={closeDescriptionModal}
                questCompletion={selectedQuestCompletion!}
                questName={selectedQuestName!}
            />

        </div>
    )
}

export default QuestsOverview
