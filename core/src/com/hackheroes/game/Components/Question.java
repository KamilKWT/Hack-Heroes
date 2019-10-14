package com.hackheroes.game.Components;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Blackbird on 10/13/2019.
 */

public class Question {
    private String question;
    private Map<String, Short> accept = new HashMap<>();
    private Map<String, Short> refuse = new HashMap<>();

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, Map<String, Short> accept, Map<String, Short> refuse) {
        this(question);
        this.accept = accept;
        this.refuse = refuse;
    }

    public Question(final String question, final short populationA, final short environmentA, final short foodA, final short resourcesA, final short moneyA, final short populationR, final short environmentR, final short foodR, final short resourcesR, final short moneyR) {
        this(question, new HashMap<String, Short>() {{
            put("population", populationA);
            put("environment", environmentA);
            put("food", foodA);
            put("resources", resourcesA);
            put("money", moneyA);
        }}, new HashMap<String, Short>() {{
            put("population", populationR);
            put("environment", environmentR);
            put("food", foodR);
            put("resources", resourcesR);
            put("money", moneyR);
        }});
    }

    public void addOnAccept(String name, short amount) {
        if (!this.accept.containsKey(name)) this.accept.put(name, amount);
    }

    public void addOnRefuse(String name, short amount) {
        if (!this.refuse.containsKey(name)) this.refuse.put(name, amount);
    }

    public void addOnAccept(String[] names, short[] amounts) {
        if (names.length != amounts.length) return;

        for (int i = 0; i < names.length; i++) {
            if (this.accept.containsKey(names[i])) continue;
            this.accept.put(names[i], amounts[i]);
        }
    }

    public void addOnRefuse(String[] names, short[] amounts) {
        if (names.length != amounts.length) return;

        for (int i = 0; i < names.length; i++) {
            if (this.refuse.containsKey(names[i])) continue;
            this.refuse.put(names[i], amounts[i]);
        }
    }

    public Map<String, Short> getOnAccept() {
        return this.accept;
    }

    public Map<String, Short> getOnRefuse() {
        return this.refuse;
    }

    public short getOnAccept(String key) {
        if (accept.containsKey(key)) return accept.get(key);
        return 0;
    }

    public short getOnRefuse(String key) {
        if (this.refuse.containsKey(key)) return this.refuse.get(key);
        return 0;
    }

    public String getQuestion() {
        return this.question;
    }
}

