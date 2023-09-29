package com.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.project.game.HumanPlayer;
import com.project.game.SmartPlayer;
import com.project.search.TicTacToeState;
import com.project.search.TicTacToeStateProblem;

/**
 * Main for TicTacToe App.
 */
public class TicTacToeApp {
    public static void main( String[] args ) throws IOException {
               
        TicTacToeStateProblem sp = new TicTacToeStateProblem();
        TicTacToeState playerState = new TicTacToeState();
        TicTacToeState botState = new TicTacToeState();

        char playerTeam = choiceTeam();
        HumanPlayer player = new HumanPlayer(playerTeam);
        char botTeam = player.getLetter() == 'X' ? 'O' : 'X';
        SmartPlayer bot = new SmartPlayer(botTeam, sp);
        clearScreen();

        boolean playerTurn = false;

        //Inital state
        sp.getInitialState();

        //First game movement
        if (player.getLetter() == 'X') {
            playerState = player.getGameMove(sp.initialState());
            playerState.printState();
        } else {
            botState = bot.getFirstGameMove(sp);
            botState.printState();
            playerTurn = true;
        }

        //Game
        boolean gameOver = false;

        while (!gameOver) {
            if (!playerTurn) {
                clearScreen();
                System.out.println(bot.getLetter()+"'s turn.");
                botState = bot.getGameMove(playerState);
                if (botState.isSuccess()) {
                    botState.printState();
                    System.out.println(bot.getLetter()+ "'s win!");
                    gameOver = true;
                } else {
                    botState.printState();
                    playerTurn = true;   
                }

                if (botState.isTie()) {
                    clearScreen();
                    botState.printState();
                    System.out.println("Tie!");
                    gameOver = true;
                }
            } else {
                playerState = player.getGameMove(botState);
                if (playerState.isSuccess()) {
                    playerState.printState();
                    System.out.println(player.getLetter()+"'s win!");
                    gameOver = true;
                } else {
                    playerState.printState();
                    playerTurn = false;
                }

                if (playerState.isTie()) {
                    clearScreen();
                    playerState.printState();
                    System.out.println("Tie!");
                    gameOver = true;
                }
            }
        }
    }

    /*
     * Method to choose team
     */
    private static char choiceTeam() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char t = ' ';
        boolean invalid = true;

        while (invalid) {
            System.out.println("Elige equipo (X - O):");
            t = reader.readLine().toUpperCase().charAt(0);

            if (validT(t)) {
                break;
            }
            
            System.out.println("Equipo no valido. Ingrese nuevamente.");
        }
        return t;
    }

    /*
     * It returns true iff c is equals than X or O. 
     * Otherwise false.
     */
    private static boolean validT(char c) {
        return c == 'X' || c == 'O';
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
