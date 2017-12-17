package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private static final int PASS_MARK = 3;

    protected List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        JsonObject[] jsonExams = new JsonObject[exams.size()];
        for (int i = 0; i < exams.size(); i++) {
            jsonExams[i] = new JsonObject(
                    new JsonPair("course", new JsonString(exams.get(i).key)),
                    new JsonPair("mark", new JsonNumber(exams.get(i).value)),
                    new JsonPair("passed", new JsonBoolean(exams.get(i).value >= PASS_MARK))
            );
        }
        jsonObject.add(new JsonPair("exams", new JsonArray(jsonExams)));
        return jsonObject;
    }
}
