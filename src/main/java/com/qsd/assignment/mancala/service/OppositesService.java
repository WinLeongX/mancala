package com.qsd.assignment.mancala.service;

import org.springframework.stereotype.Service;

import java.util.Map;

import static com.qsd.assignment.mancala.util.GameStateConstants.*;

@Service
public class OppositesService {

    public Map<String, Integer> calculateGameState(String lastMove, Map<String, Integer> gameState) {
        String opposite = calculateOpposite(lastMove);
        int score;

        if (gameState.get(PLAYERS_TURN_FLAG) == 0 && PLAYER_BLUE_PITS.contains(lastMove) && gameState.get(lastMove).equals(1)) {

            score = gameState.get(MANCALA_BLUE) + gameState.get(lastMove) + gameState.get(opposite);
            gameState.replace(MANCALA_BLUE, score);
            gameState.replace(lastMove, 0);
            gameState.replace(opposite, 0);
        } else if (gameState.get(PLAYERS_TURN_FLAG) == 1 && PLAYER_RED_PITS.contains(lastMove) && gameState.get(lastMove).equals(1)) {

            score = gameState.get(MANCALA_RED) + gameState.get(lastMove) + gameState.get(opposite);
            gameState.replace(MANCALA_RED, score);
            gameState.replace(lastMove, 0);
            gameState.replace(opposite, 0);
        }

        return gameState;
    }

    private String calculateOpposite(String lastMove) {

        return switch (lastMove) {
            case PLAYER_BLUE_PIT_6 -> PLAYER_RED_PIT_1;
            case PLAYER_BLUE_PIT_5 -> PLAYER_RED_PIT_2;
            case PLAYER_BLUE_PIT_4 -> PLAYER_RED_PIT_3;
            case PLAYER_BLUE_PIT_3 -> PLAYER_RED_PIT_4;
            case PLAYER_BLUE_PIT_2 -> PLAYER_RED_PIT_5;
            case PLAYER_BLUE_PIT_1 -> PLAYER_RED_PIT_6;
            case PLAYER_RED_PIT_1 -> PLAYER_BLUE_PIT_6;
            case PLAYER_RED_PIT_2 -> PLAYER_BLUE_PIT_5;
            case PLAYER_RED_PIT_3 -> PLAYER_BLUE_PIT_4;
            case PLAYER_RED_PIT_4 -> PLAYER_BLUE_PIT_3;
            case PLAYER_RED_PIT_5 -> PLAYER_BLUE_PIT_2;
            case PLAYER_RED_PIT_6 -> PLAYER_BLUE_PIT_1;
            default -> "0";
        };
    }
}
