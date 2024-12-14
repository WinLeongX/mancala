package com.qsd.assignment.mancala.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameStateUpdateServiceTest {

    private final GameStateUpdateService gameStateUpdateService = new GameStateUpdateService();

    @Test
    void getPitsToUpdate_WithoutOpponentMancala_NoExtraPitToUpdate() {
        List<String> result = gameStateUpdateService.getPitsToUpdate("pit6-red", 4, 0);

        List<String> expected = List.of("pit5-red", "pit4-red", "pit3-red", "pit2-red");

        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(4, result.size());
    }

    @Test
    void getPitsToUpdate_WithOpponentMancala_ExtraPitToUpdate() {
        List<String> result = gameStateUpdateService.getPitsToUpdate("pit6-red", 6, 0);

        List<String> expected = List.of("pit5-red", "pit4-red", "pit3-red", "pit2-red", "pit1-red", "mancala-red", "pit6-blue");

        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(7, result.size());
    }
}
