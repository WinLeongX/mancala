package com.qsd.assignment.mancala.util;

import java.util.List;

/**
 * Class to store GameState key values
 */
public class GameStateConstants {

    public static final String PLAYER_BLUE_PIT_1 = "pit1-blue";
    public static final String PLAYER_BLUE_PIT_2 = "pit2-blue";
    public static final String PLAYER_BLUE_PIT_3 = "pit3-blue";
    public static final String PLAYER_BLUE_PIT_4 = "pit4-blue";
    public static final String PLAYER_BLUE_PIT_5 = "pit5-blue";
    public static final String PLAYER_BLUE_PIT_6 = "pit6-blue";

    public static final String PLAYER_RED_PIT_1 = "pit1-red";
    public static final String PLAYER_RED_PIT_2 = "pit2-red";
    public static final String PLAYER_RED_PIT_3 = "pit3-red";
    public static final String PLAYER_RED_PIT_4 = "pit4-red";
    public static final String PLAYER_RED_PIT_5 = "pit5-red";
    public static final String PLAYER_RED_PIT_6 = "pit6-red";

    public static final List<String> PLAYER_BLUE_PITS = List.of(PLAYER_BLUE_PIT_6, PLAYER_BLUE_PIT_5, PLAYER_BLUE_PIT_4, PLAYER_BLUE_PIT_3, PLAYER_BLUE_PIT_2, PLAYER_BLUE_PIT_1);
    public static final List<String> PLAYER_RED_PITS = List.of(PLAYER_RED_PIT_6, PLAYER_RED_PIT_5, PLAYER_RED_PIT_4, PLAYER_RED_PIT_3, PLAYER_RED_PIT_2, PLAYER_RED_PIT_1);

    public static final String MANCALA_BLUE = "mancala-blue";
    public static final String MANCALA_RED = "mancala-red";

    public static final String PLAYERS_TURN_FLAG = "players-turn";

    public static final String GAME_OVER_FLAG = "game-over";
}
