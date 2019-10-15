package com.hackheroes.game.Tools;

import com.badlogic.gdx.utils.Array;
import com.hackheroes.game.Components.Question;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by BlackBird on 10/15/2019.
 */

public final class QuestionLoader {
    private final Array<Question> questionContainer = new Array<>();
    private final Random generator = new Random();

    private final Question[] questions = {
            new Question("Pytanie 1",
                    new HashMap<String, Integer>() {{
                        put("population", 10);
                        put("food", 10);
                    }},
                    new HashMap<String, Integer>() {{
                        put("money", -10);
                    }}
            ),
            new Question("Pytanie 2",
                    new HashMap<String, Integer>() {{
                        put("materials", 30);
                        put("food", 10);
                    }},
                    new HashMap<String, Integer>() {{
                        put("money", -10);
                        put("population", -5);
                    }})
    };

    public Question getQuestion() {
        if (questionContainer.size == 0) questionContainer.addAll(questions);

        return questionContainer.removeIndex(generator.nextInt(questionContainer.size));
    }
}
