package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsByTime = timetable.get(trainingSession.getDayOfWeek());
        if (sessionsByTime == null) {
            sessionsByTime = new TreeMap<>();
            timetable.put(trainingSession.getDayOfWeek(), sessionsByTime);
        }

        List<TrainingSession> sessionsAtTime = sessionsByTime.get(trainingSession.getTimeOfDay());
        if (sessionsAtTime == null) {
            sessionsAtTime = new ArrayList<>();
            sessionsByTime.put(trainingSession.getTimeOfDay(), sessionsAtTime);
        }

        sessionsAtTime.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsByTime = timetable.get(dayOfWeek);
        if (sessionsByTime == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessionsAtTime : sessionsByTime.values()) {
            result.addAll(sessionsAtTime);
        }
        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> sessionsByTime = timetable.get(dayOfWeek);
        if (sessionsByTime == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> sessionsAtTime = sessionsByTime.get(timeOfDay);
        if (sessionsAtTime == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(sessionsAtTime);
    }
}
