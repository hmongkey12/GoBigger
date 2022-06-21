package com.games.gobigorgohome;

import com.games.gobigorgohome.parsers.ParseJSON;

public class Exercise {
    ParseJSON jsonParser = new ParseJSON();

    private final Object targetMuscles;
    private final String exerciseStatus;
    private final Long energyCost;

    public Exercise(Object exercises, String exerciseName) {
        Object exercise = jsonParser.getObjectFromJSON(exercises, exerciseName);
        this.targetMuscles = jsonParser.getObjectFromJSONObject(exercise, "target muscles");
        this.exerciseStatus = jsonParser.getObjectStringFromJSONObject(exercise, "status");
        this.energyCost = jsonParser.getLongFromJSONObject(exercise, "energy cost");
    }

    public Object getTargetMuscles() {
        return targetMuscles;
    }

    public String getExerciseStatus() {
        return exerciseStatus;
    }

    public Long getEnergyCost() {
        return energyCost;
    }
}