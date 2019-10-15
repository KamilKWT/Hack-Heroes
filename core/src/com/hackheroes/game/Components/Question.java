package com.hackheroes.game.Components;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Blackbird on 10/13/2019.
 */

public class Question {
    private String question;
    private Map<String, Integer> accept = new TreeMap<>();
    private Map<String, Integer> refuse = new TreeMap<>();

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, Map<String, Integer> accept, Map<String, Integer> refuse) {
        this(question);
        this.accept = accept;
        this.refuse = refuse;
    }

    public Question(final String question, final int populationA, final int environmentA, final int foodA, final int resourcesA, final int moneyA, final int populationR, final int environmentR, final int foodR, final int resourcesR, final int moneyR) {
        this(question, new TreeMap<String, Integer>() {{
            put("population", populationA);
            put("environment", environmentA);
            put("food", foodA);
            put("resources", resourcesA);
            put("money", moneyA);
        }}, new TreeMap<String, Integer>() {{
            put("population", populationR);
            put("environment", environmentR);
            put("food", foodR);
            put("resources", resourcesR);
            put("money", moneyR);
        }});
    }

    public void addOnAccept(String name, int amount) {
        if (!this.accept.containsKey(name)) this.accept.put(name, amount);
    }

    public void addOnRefuse(String name, int amount) {
        if (!this.refuse.containsKey(name)) this.refuse.put(name, amount);
    }

    public void addOnAccept(String[] names, int[] amounts) {
        if (names.length != amounts.length) return;

        for (int i = 0; i < names.length; i++) {
            if (this.accept.containsKey(names[i])) continue;
            this.accept.put(names[i], amounts[i]);
        }
    }

    public void addOnRefuse(String[] names, int[] amounts) {
        if (names.length != amounts.length) return;

        for (int i = 0; i < names.length; i++) {
            if (this.refuse.containsKey(names[i])) continue;
            this.refuse.put(names[i], amounts[i]);
        }
    }

    public Map<String, Integer> getOnAccept() {
        return this.accept;
    }

    public Map<String, Integer> getOnRefuse() {
        return this.refuse;
    }

    public int getOnAccept(String key) {
        if (this.accept.containsKey(key)) return this.accept.get(key);
        return 0;
    }

    public int getOnRefuse(String key) {
        if (this.refuse.containsKey(key)) return this.refuse.get(key);
        return 0;
    }

    public String getQuestion() {
        return this.question;
    }
}

