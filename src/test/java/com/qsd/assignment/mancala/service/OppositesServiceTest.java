package com.qsd.assignment.mancala.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class OppositesServiceTest {

    private final OppositesService oppositesService = new OppositesService();

    @Test
    void calculateGameState_WithOppositeRule() {
        Map<String, Integer> result = oppositesService.calculateGameState("pit2-blue", gameState());

        Assertions.assertEquals(5, result.get("mancala-blue"));
        Assertions.assertEquals(0, result.get("pit2-blue"));
        Assertions.assertEquals(0, result.get("pit5-red"));
    }

    @Test
    void calculateGameState_WithoutOppositeRule() {
        Map<String, Integer> gameStateWithoutOpposite = gameState();
        gameStateWithoutOpposite.replace("pit2-blue", 3);

        Map<String, Integer> result = oppositesService.calculateGameState("pit2-blue", gameStateWithoutOpposite);

        Assertions.assertEquals(0, result.get("mancala-blue"));
        Assertions.assertEquals(3, result.get("pit2-blue"));
        Assertions.assertEquals(4, result.get("pit5-red"));
    }

    private Map<String, Integer> gameState() {
        Map<String, Integer> gameState = new HashMap<>();

        gameState.put("mancala-blue", 0);
        gameState.put("mancala-red", 0);

        gameState.put("pit1-blue", 0);
        gameState.put("pit2-blue", 1);
        gameState.put("pit3-blue", 4);
        gameState.put("pit4-blue", 4);
        gameState.put("pit5-blue", 4);
        gameState.put("pit6-blue", 4);

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
