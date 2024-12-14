package com.qsd.assignment.mancala.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MancalaServiceTest {

    @Mock
    private GameStateUpdateService gameStateUpdateService;

    @Mock
    private OppositesService oppositesService;

    @Mock
    private GameOverService gameOverService;

    @InjectMocks
    private MancalaService mancalaService;

    @Test
    void makeMove_GameStartAndReset() {

        when(gameStateUpdateService.getPitsToUpdate("pit6-blue", 4, 0)).thenReturn(List.of("pit5-blue", "pit4-blue", "pit3-blue", "pit2-blue"));
        when(oppositesService.calculateGameState(any(String.class), anyMap())).thenReturn(mancalaService.getGameState());
        when(gameOverService.check(anyMap())).thenReturn(mancalaService.getGameState());

        mancalaService.makeMove("pit6-blue");

        assertEquals(5, mancalaService.getGameState().get("pit5-blue"));
        assertEquals(5, mancalaService.getGameState().get("pit4-blue"));
        assertEquals(5, mancalaService.getGameState().get("pit3-blue"));
        assertEquals(5, mancalaService.getGameState().get("pit2-blue"));
        assertEquals(1, mancalaService.getGameState().get("players-turn"));

        mancalaService.resetGame();

        assertEquals(4, mancalaService.getGameState().get("pit5-blue"));
        assertEquals(4, mancalaService.getGameState().get("pit4-blue"));
        assertEquals(4, mancalaService.getGameState().get("pit3-blue"));
        assertEquals(4, mancalaService.getGameState().get("pit2-blue"));
        assertEquals(0, mancalaService.getGameState().get("players-turn"));
    }
}
