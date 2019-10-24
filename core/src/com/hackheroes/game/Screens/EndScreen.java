package com.hackheroes.game.Screens;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackheroes.game.MainClass;

import java.util.Map;
import java.util.TreeMap;

public class EndScreen extends AbstractScreen {

    private MainClass game;

    private Map<String, String> texts = new TreeMap<String, String>() {{
        put("environment", "Stan środowiska pogorszył się do tego stopnia, że na terenie kraju już nie da się żyć. Cała ludność musiała emigrować na drugi koniec świata, szukając terenów możliwych do zamieszkania.");
        put("food", "Ilość pożywienia spadła do krytycznego poziomu. Ludzie głodują i podejmują wszelkie możliwe środki, aby zdobyć pożywienie. Uprawy w kraju nie dają już żadnych plonów, więc rolnicy uciekli za granicę poszukując żyznej ziemii.");
        put("population", "Liczba ludności spadła do poziomu grożącego kryzem demograficznym. Społeczeństwo starzeje się, umiera lub emigruje za granicę. Jeśli nic się nie zmieni kraj straci wszystkich obywateli.");
        put("population_high", "Liczba ludności przekroczyła wszelkie limity doprowadzając do przeludnienia. Wielu ludzi nie ma domów, bo wszystkie są pełne mieszkańców. 10 osób żyjących w małym mieszkaniu stało się normom. Ci którzy mogą uciekają z kraju.");
        put("resources", "Liczba zasobów spadła praktycznie do zera. Ludzie nie mają z czego budować domostw, a fabryki nie mają z czego wyrabiać swoich produktów. Krajowy przemysł wisi na włosku, a kraj stoi na skraju bankructwa.");
        put("money", "Wydaliśmy ostatnie oszczędności. Nie mamy już nic na czarną godzinę. Posiadana przez nas ilość nie pozwala już na podjęcie żadnej decyzji. Bez jakichkolwiek środków pieniężnych nie jesteśmy już w stanie kierować krajem.");
    }};

    public EndScreen(MainClass game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        game.gameShapeRenderer.setProjectionMatrix(game.gameCamera.combined);
        game.gameShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.gameShapeRenderer.setColor(MainClass.GUI_BORDER_COLOR);
        game.gameShapeRenderer.roundRect(50, 200, MainClass.V_WIDTH - 100, MainClass.V_HEIGHT - 400, 20);
        game.gameShapeRenderer.setColor(MainClass.GUI_BACKGROUND_COLOR);
        game.gameShapeRenderer.roundRect(60, 210, MainClass.V_WIDTH - 120, MainClass.V_HEIGHT - 420, 10);
        game.gameShapeRenderer.end();

        game.gameBatch.setProjectionMatrix(game.gameCamera.combined);
        game.gameBatch.begin();
        game.gameFont.getData().setScale(1.25f);
        game.gameFont.draw(game.gameBatch, "Koniec gry", 0, MainClass.V_HEIGHT - 50, MainClass.V_WIDTH, 1, false);
        game.gameFont.getData().setScale(0.5f);
        game.gameFont.draw(game.gameBatch, texts.get(game.gameScreen.endReason), 80, MainClass.V_HEIGHT - 230, MainClass.V_WIDTH - 160, 1, true);
        game.gameFont.draw(game.gameBatch, "Kliknij, aby kontynuować...", 0, 150, MainClass.V_WIDTH, 1, false);
        game.gameBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.gameViewport.update(width, height, false);
    }

    @Override
    public void isClicked(int touchX, int touchY) {
        game.setScreen(game.resultsScreen);
    }
}