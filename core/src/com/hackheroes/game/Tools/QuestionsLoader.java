package com.hackheroes.game.Tools;

import com.badlogic.gdx.utils.Array;
import com.hackheroes.game.Components.Question;
import java.util.Random;
import java.util.TreeMap;

public class QuestionsLoader {

    private final Array<Question> questions = new Array<>();
    private static final Random generator = new Random();
    private static final Question[] questions_raw = {
            /*new Question("Teskt pytania", 25, -15, 5, -35, -1250, -15, -10, 40, 15, 2150),
            new Question("Teskt innego pytania", 10, -5, 0, -25, 1350, 15, 0, -10, 0, -250),
            new Question("bank breaker", 10, -5, 0, -25, -32000, 0, 0, 0, 0, -31999),*/

            new Question("W mieście brakuje żywności. Czy chciałbyś zwiekszyć import? Jeśli się zgodzisz będzie nas to kosztować 20000zł.",
                    new TreeMap<String, Short>(){{put("food", (short) 25); put("population", (short) 10); put("money", (short) -20000);}},
                    new TreeMap<String, Short>(){{put("food", (short) -20); put("population", (short) -15);}}),
            new Question("W mieście będzie budowany supermarket. Czy zgadzasz na jego budowę?",
                    new TreeMap<String, Short>(){{put("environment", (short) -10); put("food", (short) 20);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 5); put("food", (short) -10);}}),
            new Question("W lesie szaleje pożar. Czy chcesz podjąć natychmiastową akcje gaśniczą? Jeśli się zgodzisz będzie nas to kosztować 25000zł. Jeśli się nie zgodzisz zyskamy 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("resources", (short) -5); put("money", (short) -25000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("resources", (short) -10); put("money", (short) 5000);}}),
            new Question("Czy chciałbyś dofinansować rolników i przemysł spożywczy? Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 5); put("food", (short) 20); put("resources", (short) -5); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -10); put("food", (short) -15); put("resources", (short) 5); put("money", (short) 10000);}}),
            new Question("Czy chcesz przeznaczyć ziemię pod blokowiska na pola uprawne? Jeśli się zgodzisz możemy zyskać 5000zł. Jeśli się nie zgodzisz będzie nas to kosztować 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 5); put("food", (short) 30); put("population", (short) -15); put("money", (short) 5000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -5); put("food", (short) -15); put("population", (short) 20); put("money", (short) -10000);}}),
            new Question("Przemytnicy proponują ci dużą łapówkę w zamian za przymknięcie oka na ich działalność? Jeśli się zgodzisz możemy zyskać 75000zł. Jeśli się nie zgodzisz będzie nas to kosztować 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("resources", (short) -10); put("money", (short) 75000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 40); put("resources", (short) 10); put("money", (short) -5000);}}),
            new Question("Rada uważa, że miasto lepiej by funkcjonowało, gdybyś wprowadził politykę recyklingową. Czy zgadzasz sie z nimi? Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("food", (short) 10); put("population", (short) 10); put("resources", (short) -15); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -5); put("food", (short) -5); put("population", (short) -5); put("resources", (short) 10); put("money", (short) 5000);}}),
            new Question("Uchodźcy chca skorzystać z gościnności naszego kraju. Czy przyjmiesz ich? Jeśli się zgodzisz możemy zyskać 15000zł.",
                    new TreeMap<String, Short>(){{put("food", (short) -15); put("population", (short) 25); put("resources", (short) -10); put("money", (short) 15000);}},
                    new TreeMap<String, Short>(){{put("food", (short) 10); put("population", (short) -5); put("resources", (short) 15);}}),
            new Question("Czy chciałbyś wspomóc inicjatywę poprawy jakości życia w krajach 3 świata? Dofinansowanie dotyczy m. in. szczepionek, żywności, ubrań. Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 10); put("food", (short) -20); put("population", (short) 20); put("resources", (short) -25); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -10); put("food", (short) 20); put("population", (short) -20); put("resources", (short) 10); put("money", (short) 10000);}}),
            new Question("Wielu uchodźców, którzy przybyli do kraju nie stać na odpowiednią edukację. Czy chciałbyś utworzyć program wspierający ich naukę? Jeśli się zgodzisz będzie nas to kosztować 20000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("population", (short) 20); put("money", (short) -20000);}},
                    new TreeMap<String, Short>(){{put("population", (short) -15); put("money", (short) 10000);}}),
            new Question("Pojawia się prośba o dofinansowanie budowy rezerwatów. Czy chcesz dołożyć się do tego szczytnego celu? Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 25); put("resources", (short) 10); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -20); put("population", (short) 20); put("resources", (short) -5); put("money", (short) 10000);}}),
            new Question("Nadchodzi czas wysłania inspekcji do miejsc pracy i oceny szkodliwości ich działalności. Czy chcesz wysłać inspeckcje? Jeśli się zgodzisz będzie nas to kosztować 5000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 25); put("population", (short) 10); put("resources", (short) -10); put("money", (short) -5000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("population", (short) -5); put("money", (short) 10000);}}),
            new Question("Mieszkańcy chcą, by miasto wzięło udział w programie ochrony gatunków. Czy chcesz wziąć w nim udział? Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("population", (short) 15); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("population", (short) -15); put("money", (short) 10000);}}),
            new Question("Rada uważa, że powinieneś wydać zgodę na budowę elektrowni atomowej, która jest lepsza dla środowiska niż dotychczasowe - węglowe. Czy przystaniesz na prośbę rady? Jeśli się zgodzisz będzie nas to kosztować 30000zł. Jeśli się nie zgodzisz zyskamy 25000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 30); put("money", (short) -30000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("population", (short) -10); put("money", (short) 25000);}}),
            new Question("Pojawia sie prozpozycja wykorzystania zamiast plastiku składników biodegradowalnych w produkcji m. in. butelek. Czy zgadzasz się na wdrożenie tego pomysłu? Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 25); put("food", (short) 10); put("resources", (short) 10);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("food", (short) -10); put("money", (short) 10000);}}),
            new Question("W mieście ma powstać hodowla brojlerów. Czy zgadasz sie na jej budowę? Jeśli się zgodzisz możemy zyskać 15000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("food", (short) 25); put("money", (short) 15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("food", (short) -15);}}),
            new Question("W wielu domach używane są stare, bardzo zanieczyszczające środowisko piece. Czy chciałbyś dofinansować zakup nowych dla mieszkańców? Jeśli się zgodzisz będzie nas to kosztować 20000zł. Jeśli się nie zgodzisz zyskamy 15000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("population", (short) 10); put("resources", (short) 5); put("money", (short) -20000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -20); put("population", (short) -5); put("resources", (short) -5); put("money", (short) 15000);}}),
            new Question("Miastu przydałaby się lepsza oczyszczalnia ścieków. Czy zgadzasz się zbudować jedną? Jeśli się zgodzisz będzie nas to kosztować 15000zł. Jeśli się nie zgodzisz zyskamy 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 25); put("food", (short) 10); put("population", (short) 5); put("resources", (short) 15); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("food", (short) -15); put("population", (short) -10); put("resources", (short) -15); put("money", (short) 10000);}}),
            new Question("Pojawia się propozycja inwestycji w bardziej ekologiczne i wydajne maszyny, które zostaną odsprzedane do m.in. fabryk. Czy zainwestujesz? Jeśli się zgodzisz będzie nas to kosztować 30000zł. Jeśli się nie zgodzisz zyskamy 25000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 30); put("food", (short) 25); put("population", (short) 15); put("resources", (short) 20); put("money", (short) -30000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("food", (short) -5); put("population", (short) -5); put("resources", (short) -15); put("money", (short) 25000);}}),
            new Question("Rada domaga się interwencji w sprawie utylizacji odpadów radioaktywnych. Jeśli się nie zgodzisz zostaną on po prostu zakopane pod ziemią. Jaka jest twoja decyzja? Jeśli się nie zgodzisz zyskamy 50000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 30); put("food", (short) 20); put("population", (short) 10); put("resources", (short) 5);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("food", (short) -20); put("population", (short) -15); put("resources", (short) -5); put("money", (short) 50000);}}),
            new Question("Ludzie skarżą się na palenie odpadów przez różne firmy. Czy warto interweniować? Jeśli się zgodzisz będzie nas to kosztować 10000zł. Jeśli się nie zgodzisz zyskamy 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 30); put("population", (short) 5); put("resources", (short) 10); put("money", (short) -10000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -25); put("population", (short) -15); put("resources", (short) -5); put("money", (short) 5000);}}),
            new Question("Razem z radą rozważacie dofinansowania i modernizacje komunikacji miejskiej, aby mniej ludzi jeździło samochodami. Czy to dobra zmiana? Jeśli się zgodzisz będzie nas to kosztować 25000zł. Jeśli się nie zgodzisz zyskamy 20000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 30); put("population", (short) 10); put("resources", (short) 25); put("money", (short) -25000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -15); put("resources", (short) -15); put("money", (short) 20000);}}),
            new Question("Wpadasz na kolejny pomysł, by pomóc swojemu miastu. Uznajesz, że bardzo dobrym pomysłem byłoby dofinansowanie i rozpopularyzowanie elektrycznych środków lokomocji. Jednak czy ostatecznie ten pomysł jest dobry? Jeśli się zgodzisz będzie nas to kosztować 30000zł. Jeśli się nie zgodzisz zyskamy 40000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 25); put("population", (short) 10); put("resources", (short) 30); put("money", (short) -30000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("resources", (short) -25); put("money", (short) 40000);}}),
            new Question("W mieście potrzebna jest reforma dot. utylizacji odpadów i żywności. Wiele fabryk i innych przedsiębiorstw nie przestrzega norm, przez co środowisko jest zanieczyszczone. Czy zgadzasz się na wprowadzenie jej? Jeśli się zgodzisz będzie nas to kosztować 10000zł. Jeśli się nie zgodzisz zyskamy 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("food", (short) 15); put("population", (short) 5); put("resources", (short) 10); put("money", (short) -10000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -30); put("food", (short) -10); put("population", (short) -10); put("resources", (short) -5); put("money", (short) 5000);}}),
            new Question("Rada rozważa budowę kolejnego osiedla mieszkaniowego. Czy zgadzasz się z nią? Jeśli się zgodzisz będzie nas to kosztować 10000zł. Jeśli się nie zgodzisz zyskamy 5000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) -5); put("population", (short) 25); put("resources", (short) -15); put("money", (short) -10000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 15); put("population", (short) -15); put("resources", (short) 10); put("money", (short) 5000);}}),
            new Question("Wiele osób prosi o polepszenie warunków w mieście. W tym celu rada uznała, że warto zachęcić ludzi do prowadzenia bardziej ekologicznego trybu życia dobrą kampanią. Czy zgadzasz się na wprowadzenie jej. Jeśli się zgodzisz będzie nas to kosztować 10000zł. Jeśli się nie zgodzisz zyskamy 15000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) 20); put("food", (short) 10); put("population", (short) 5); put("resources", (short) 30); put("money", (short) -10000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) -15); put("food", (short) -15); put("resources", (short) -20); put("money", (short) 15000);}}),
            new Question("Do miasta zawitał inwestor, który chce zbudować fabrykę produkującą żywność. Czy wyrażasz zgodę na konstrukcje tego obiektu? Jeśli się zgodzisz będzie nas to kosztować 15000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) -20); put("food", (short) 30); put("money", (short) -15000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 10); put("food", (short) -30);}}),
            new Question("Miasto potrzebuje nowej przestrzeni do rozbudowy. Czy zgadzasz się na wycinkę lasu i zbudowaniu tam osiedla mieszkaniowego? Jeśli się zgodzisz będzie nas to kosztować 10000zł.",
                    new TreeMap<String, Short>(){{put("environment", (short) -5); put("population", (short) 20); put("resources", (short) -10); put("money", (short) -10000);}},
                    new TreeMap<String, Short>(){{put("environment", (short) 10);}})

    };

    public void resetQuestions() {
        questions.addAll(questions_raw);
    }

    public Question getRandomQuestion() {
        if (questions.size == 0) {
            resetQuestions();
        }

        return questions.removeIndex(generator.nextInt(questions.size));
    }
}