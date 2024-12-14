package com.qsd.assignment.mancala.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameOverServiceTest {

    private final GameOverService gameOverService = new GameOverService();

    @Test
    void check_GameOver() {
        Map<String, Integer> result = gameOverService.check(gameState());

        assertEquals(1, result.get("game-over"));
        assertEquals(24, result.get("mancala-red"));

    }

    @Test
    void check_GameNotOver() {
        Map<String, Integer> gameNotOverState = gameState();
        gameNotOverState.replace("pit2-blue", 4);

        Map<String, Integer> result = gameOverService.check(gameNotOverState);

        assertEquals(0, result.get("game-over"));
        assertEquals(0, result.get("mancala-red"));

    }

    private Map<String, Integer> gameState() {
        Map<String, Integer> gameState = new HashMap<>();

        gameState.put("mancala-blue", 1);
        gameState.put("mancala-red", 0);

        gameState.put("pit1-blue", 0);
        gameState.put("pit2-blue", 0);
        gameState.put("pit3-blue", 0);
        gameState.put("pit4-blue", 0);
        gameState.put("pit5-blue", 0);
        gameState.put("pit6-blue", 0);

        gameState.put("pit1-red", 4);
        gameState.put("pit2-red", 4);
        gameState.put("pit3-red", 4);
        gameState.put("pit4-red", 4);
        gameState.put("pit5-red", 4);
        gameState.put("pit6-red", 4);

        gameState.put("players-turn", 0);

        gameState.put("game-over", 0);

        return gameState;
    }
}
