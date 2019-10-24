package com.hackheroes.game.Components;

import java.util.Map;
import java.util.TreeMap;

public class Question {

    private String question;
    private Map<String, Integer> accept = new TreeMap<>();
    private Map<String, Integer> refuse = new TreeMap<>();

    public Question(String question, Map<String, Integer> accept, Map<String, Integer> refuse) {
        this.question = question;
        this.accept = accept;
        this.refuse = refuse;
    }

    public Question(final String question, final int environmentA, final int foodA, final int populationA, final int resourcesA, final int moneyA, final int environmentR, final int foodR, final int populationR, final int resourcesR, final int moneyR) {
        this(question, new TreeMap<String, Integer>() {{
            if (environmentA != 0) put("environment", environmentA);
            if (foodA != 0) put("food", foodA);
            if (populationA != 0) put("population", populationA);
            if (resourcesA != 0) put("resources", resourcesA);
            if (moneyA != 0) put("money", moneyA);
        }}, new TreeMap<String, Integer>() {{
            if (environmentR != 0) put("environment", environmentR);
            if (foodR != 0) put("food", foodR);
            if (populationR != 0) put("population", populationR);
            if (resourcesR != 0) put("resources", resourcesR);
            if (moneyR != 0) put("money", moneyR);
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