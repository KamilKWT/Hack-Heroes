package com.hackheroes.game.Tools;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class QuestionsLoader {

    private ArrayList<Question> questions = new ArrayList<>();

    public static QuestionsLoader loadQuestions() {
        QuestionsLoader questionsLoader = new QuestionsLoader();

        questionsLoader.createQuestions(questionsLoader);

        return questionsLoader;
    }

    private void createQuestions(QuestionsLoader questionsLoader) {
        questionsLoader.addQuestion(new Question("Teskt pytania", 25, -15, 5, -35, -1250, -15, -10, 40, 15, 2150));
        questionsLoader.addQuestion(new Question("Teskt innego pytania", 10, -5, 0, -25, 1350, 15, 0, -10, 0, -250));
        questionsLoader.addQuestion(new Question("Teskt jeszcze innego pytania", 45, 0, -5, 15, 650, 0, -10, 0, 25, -1750));
        questionsLoader.addQuestion(new Question("Teskt jeszcze bardziej innego pytania", -15, -15, 0, -15, -750, 0, 0, -30, 5, 215));
    }

    private void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public class Question {

        private String question;
        private Map<String, Short> accept = new TreeMap<>();
        private Map<String, Short> refuse = new TreeMap<>();

        public Question(String question) {
            this.question = question;
        }

        public Question(String question, Map<String, Short> accept, Map<String, Short> refuse) {
            this(question);
            this.accept = accept;
            this.refuse = refuse;
        }

        public Question(final String question, final short environmentA, final short foodA, final short populationA, final short resourcesA, final short moneyA, final short environmentR, final short foodR, final short populationR, final short resourcesR, final short moneyR) {
            this(question, new TreeMap<String, Short>() {{
                if (environmentA != 0) put("environment", environmentA);
                if (foodA != 0)put("food", foodA);
                if (populationA != 0)put("population", populationA);
                if (resourcesA != 0)put("resources", resourcesA);
                if (moneyA != 0)put("money", moneyA);
            }}, new TreeMap<String, Short>() {{
                if (environmentR != 0)put("environment", environmentR);
                if (foodR != 0)put("food", foodR);
                if (populationR != 0)put("population", populationR);
                if (resourcesR != 0)put("resources", resourcesR);
                if (moneyR != 0)put("money", moneyR);
            }});
        }

        public Question(final String question, final int environmentA, final int foodA, final int populationA, final int resourcesA, final int moneyA, final int environmentR, final int foodR, final int populationR, final int resourcesR, final int moneyR) {
            this(question, (short) environmentA, (short) foodA, (short) populationA, (short) resourcesA, (short) moneyA, (short) environmentR, (short) foodR, (short) populationR, (short) resourcesR, (short) moneyR);
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
            if (this.accept.containsKey(key)) return this.accept.get(key);
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
}