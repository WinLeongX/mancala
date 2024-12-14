package com.qsd.assignment.mancala.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qsd.assignment.mancala.util.GameStateConstants.*;

/**
 * Service to process game rules when player makes a move
 */
@Service
public class MancalaService {

    private final GameStateUpdateService gameStateUpdateService;

    private final OppositesService oppositesService;

    private final GameOverService gameOverService;

    @Getter
    private Map<String, Integer> gameState = new HashMap<>();

    public MancalaService(GameStateUpdateService gameStateUpdateService,
                          OppositesService oppositesService,
                          GameOverService gameOverService) {
        this.gameStateUpdateService = gameStateUpdateService;
        this.oppositesService = oppositesService;
        this.gameOverService = gameOverService;
        loadGameBoard();
    }

    /**
     * Update the gamestate based on input from player
     *
     * @param selectedPit Selected pit by the player
     */
    public void makeMove(String selectedPit) {
        Integer pebblesInHand = gameState.get(selectedPit);
        Integer playersTurn = gameState.get(PLAYERS_TURN_FLAG);

        gameState.replace(selectedPit, 0);

        List<String> pitsToUpdate = gameStateUpdateService.getPitsToUpdate(selectedPit, pebblesInHand, playersTurn);
        updatePits(pitsToUpdate);

        String lastPit = updateMancalas(pitsToUpdate);
        gameState = oppositesService.calculateGameState(lastPit, gameState);

        switchPlayersTurn(lastPit);

        gameState = gameOverService.check(gameState);
    }

    /**
     * Method to reset the Mancala board
     */
    public void resetGame() {
        loadGameBoard();
    }

    private void updatePits(List<String> pitsToUpdate) {
        pitsToUpdate.stream()
                .filter(k -> !(k.equals(MANCALA_BLUE) || k.equals(MANCALA_RED)))
                .forEach(p -> {
                    Integer score = gameState.get(p) + 1;
                    gameState.replace(p, score);
                });
    }

    private String updateMancalas(List<String> pitsToUpdate) {
        int mancalaScore;

        if(pitsToUpdate.contains(MANCALA_BLUE) && gameState.get(PLAYERS_TURN_FLAG) == 0) {
            mancalaScore = gameState.get(MANCALA_BLUE) + 1;
            gameState.replace(MANCALA_BLUE, mancalaScore);
        }

        if(pitsToUpdate.contains(MANCALA_RED) && gameState.get(PLAYERS_TURN_FLAG) == 1) {
            mancalaScore = gameState.get(MANCALA_RED) + 1;
            gameState.replace(MANCALA_RED, mancalaScore);
        }
        return pitsToUpdate.get(pitsToUpdate.size() - 1);
    }

    private void switchPlayersTurn(String lastMove) {
        // Check if last pebble is in mancala
        if (!(lastMove.equals(MANCALA_BLUE) || lastMove.equals(MANCALA_RED))) {
            switch(gameState.get(PLAYERS_TURN_FLAG)) {
                case 0 -> gameState.replace(PLAYERS_TURN_FLAG, 1);
                case 1 -> gameState.replace(PLAYERS_TURN_FLAG, 0);
            }
        }
    }

    private void loadGameBoard() {
        // Reset Mancala pits
        gameState.put(MANCALA_BLUE, 0);
        gameState.put(MANCALA_RED, 0);

        //Reset player pits
        gameState.put(PLAYER_BLUE_PIT_1, 4);
        gameState.put(PLAYER_BLUE_PIT_2, 4);
        gameState.put(PLAYER_BLUE_PIT_3, 4);
        gameState.put(PLAYER_BLUE_PIT_4, 4);
        gameState.put(PLAYER_BLUE_PIT_5, 4);
        gameState.put(PLAYER_BLUE_PIT_6, 4);

        gameState.put(PLAYER_RED_PIT_1, 4);
        gameState.put(PLAYER_RED_PIT_2, 4);
        gameState.put(PLAYER_RED_PIT_3, 4);
        gameState.put(PLAYER_RED_PIT_4, 4);
        gameState.put(PLAYER_RED_PIT_5, 4);
        gameState.put(PLAYER_RED_PIT_6, 4);

        // 0 for blue, 1 for red
        gameState.put(PLAYERS_TURN_FLAG, 0);

        // 0 for game not over, 1 for game over
        gameState.put(GAME_OVER_FLAG, 0);
    }

}
