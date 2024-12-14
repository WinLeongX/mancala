package com.qsd.assignment.mancala.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.qsd.assignment.mancala.util.GameStateConstants.*;

@Service
@RequiredArgsConstructor
public class GameStateUpdateService {

    private final List<String> gameSequence = List.of(
            MANCALA_BLUE, PLAYER_RED_PIT_6, PLAYER_RED_PIT_5, PLAYER_RED_PIT_4, PLAYER_RED_PIT_3, PLAYER_RED_PIT_2, PLAYER_RED_PIT_1,
            MANCALA_RED, PLAYER_BLUE_PIT_6, PLAYER_BLUE_PIT_5, PLAYER_BLUE_PIT_4, PLAYER_BLUE_PIT_3, PLAYER_BLUE_PIT_2, PLAYER_BLUE_PIT_1);

    public List<String> getPitsToUpdate(String selectedPit, Integer pebblesInHand, Integer playersTurn) {
        int startingIndex = getStartingIndex(selectedPit);

        List<String> pitsToUpdate = Stream.iterate(startingIndex, i -> (i + 1) % gameSequence.size())
                .map(gameSequence::get)
                // check filter with full circle game state
                .filter(p -> !p.equals(selectedPit))
                .limit(pebblesInHand)
                .toList();

        List<String> finalResult = new ArrayList<>(pitsToUpdate);

        // Skip opposite mancala and add one more pit
        if (pitsToUpdate.contains(MANCALA_BLUE) && playersTurn == 1 ||
                pitsToUpdate.contains(MANCALA_RED) && playersTurn == 0) {

            String lastPit = pitsToUpdate.get(pitsToUpdate.size() - 1);
            finalResult.add(gameSequence.get(getIndexToAdd(lastPit)));
        }
        return finalResult;
    }

    /**
     * Calculate starting index of selectedPit without going out-of-bounds
     * in Game Sequence list
     *
     * @param selectedPit Selected pit of the player's turn
     * @return Starting Index
     */
    private Integer getStartingIndex(String selectedPit) {
        return gameSequence.indexOf(selectedPit) + 1 == gameSequence.size() ? 0 : gameSequence.indexOf(selectedPit);
    }

    /**
     * Calculate index of extra pit to add from last pit without going
     * out-of-bounds in Game Sequence list
     *
     * @param lastPit Last pit in pits to update
     * @return Index of extra to add
     */
    private Integer getIndexToAdd(String lastPit) {
        return gameSequence.indexOf(lastPit) + 1 == gameSequence.size() ? 0 : gameSequence.indexOf(lastPit) + 1;
    }
}
