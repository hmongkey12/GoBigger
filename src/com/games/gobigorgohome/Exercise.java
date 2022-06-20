package com.games.gobigorgohome;

import com.games.gobigorgohome.parsers.ParseJSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;

public class Exercise {
    ParseJSON jsonParser = new ParseJSON();

    private JSONArray targetMuscles;
    private String exerciseStatus;
    private Long energyCost;

    public Exercise(JSONObject exercises, String exerciseName) throws FileNotFoundException {
        JSONObject exercise = jsonParser.getJSONObjectFromJSONObject(exercises, exerciseName);
        this.targetMuscles = jsonParser.getJSONArrayFromJSONObject(exercise, "target muscles");
        this.exerciseStatus = (String) exercise.get("status");
        this.energyCost = (Long) exercise.get("energy cost");
    }

    public JSONArray getTargetMuscles() {
        return targetMuscles;
    }

    public String getExerciseStatus() {
        return exerciseStatus;
    }

    public Long getEnergyCost() {
        return energyCost;
    }
}