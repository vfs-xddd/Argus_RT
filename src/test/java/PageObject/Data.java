package PageObject;

import java.util.*;

public final class Data {
    public static ArrayList<String> streets = new ArrayList<>(Arrays.asList(
            "Советская",
            "Чуйкова"
    ));

    public static HashMap<String, Integer> get_map_streets() {
        HashMap<String, Integer> newMap = new HashMap<>();
        streets.forEach(el -> newMap.put(el, 0));
        return newMap;
    }
}
