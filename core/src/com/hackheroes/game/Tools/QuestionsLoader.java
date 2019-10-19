package com.hackheroes.game.Tools;

import com.badlogic.gdx.utils.Array;
import com.hackheroes.game.Components.Question;
import java.util.Random;

public class QuestionsLoader {

    private final Array<Question> questions = new Array<>();
    private static final Random generator = new Random();
    private static final Question[] questions_raw = {
            new Question("Teskt pytania", 25, -15, 5, -35, -1250, -15, -10, 40, 15, 2150),
            new Question("Teskt innego pytania", 10, -5, 0, -25, 1350, 15, 0, -10, 0, -250)};

    public Question getRandomQuestion() {
        if (questions.size == 0)
            questions.addAll(questions_raw);

        return questions.removeIndex(generator.nextInt(questions.size));
    }
}