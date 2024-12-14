package com.qsd.assignment.mancala.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.qsd.assignment.mancala.util.GameStateConstants.*;

@Service
public class GameOverService {

    public Map<String, Integer> check(Map<String, Integer> gameState) {

        if (calculateSum(PLAYER_BLUE_PITS, gameState) == 0 || calculateSum(PLAYER_RED_PITS, gameState) == 0) {
            // Set game over flag
            gameState.replace(GAME_OVER_FLAG, 1);

            // Calculate final scores
            Integer endBlueScore = calculateSum(PLAYER_BLUE_PITS, gameState) + gameState.get(MANCALA_BLUE);
            gameState.replace(MANCALA_BLUE, endBlueScore);

            Integer endRedScore = calculateSum(PLAYER_RED_PITS, gameState) + gameState.get(MANCALA_RED);
            gameState.replace(MANCALA_RED, endRedScore);
        }
        return gameState;
    }

    private Integer calculateSum(List<String> playersPits, Map<String, Integer> gameState) {
        return playersPits.stream()
                .filter(gameState::containsKey)
                .map(gameState::get)
                .reduce(0, Integer::sum);
    }
}
