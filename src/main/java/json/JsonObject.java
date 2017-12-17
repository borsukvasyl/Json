package json;

import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final HashMap<String, Json> jsonMap = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair : jsonPairs) {
            jsonMap.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        String result = "{";
        int count = 0;
        for (String key : jsonMap.keySet()) {
            count++;
            result += String.format("%s: %s", key, jsonMap.get(key).toJson());
            if (count != jsonMap.size()) {
                result += ", ";
            }
        }
        result += "}";
        return result;
    }

    public void add(JsonPair jsonPair) {
        jsonMap.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        if (jsonMap.containsKey(name)) {
            return jsonMap.get(name);
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();
        for (String name : names) {
            if (jsonMap.containsKey(name))
                result.add(new JsonPair(name, jsonMap.get(name)));
        }
        return result;
    }
}
