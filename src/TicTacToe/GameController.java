package TicTacToe;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

// Accepts input buttons

public class GameController {

    private GameView gameBoard;
    private GameModel gameRules;

    public GameController() {
        gameBoard = GameView.getInstance();
        gameRules = GameModel.getInstance();
    }

    public void run() {
        // Add all event handlers
        gameBoard.getResetBtn().setOnAction(e -> {
            reset();
        });
        gameBoard.getStartBtn().setOnAction(e -> {
            start();
        });
        for (Button b : gameBoard.getGameButtons()) {
            b.setOnAction(gameButtonPress());
        }
    }

    public Scene getGameBoard() {
        return gameBoard.getScene();
    }

    // Events when start button is pressed
    public void start() {
        ArrayList<Button> gameButtons = gameBoard.getGameButtons();

        for (Button b : gameButtons) {
            b.setDisable(false);
        }
        gameBoard.getStartBtn().setDisable(true);
        gameRules.setCurrentPlayer(0);
        gameBoard.getGameText().setText("O's turn");
    }

    // Events when reset button is pressed
    public void reset() {
        ArrayList<Button> gameButtons = gameBoard.getGameButtons();
        gameRules.setGameResultText(null);
        gameRules.setTurnCounter(0);
        gameRules.setGameWinner(false);
        for (Button b : gameButtons) {
            b.setText("");
            b.setDisable(true);
            b.setStyle(null);
        }
        gameBoard.getStartBtn().setDisable(false);
        gameBoard.getGameText().setText("Click start to begin");
    }

    // Event when a game button is pressed.
    public EventHandler<ActionEvent> gameButtonPress() {
        EventHandler<ActionEvent> handle = e -> {
            Button btnPressed = (Button) e.getSource();
            if (gameRules.getCurrentPlayer() == 1) {
                btnPressed.setText("X");
                btnPressed.setDisable(true);
                gameBoard.getGameText().setText("O's turn");
                gameRules.incrementTurnCounter();
                checkWinner(gameBoard.getGameButtons());
                gameRules.setCurrentPlayer(0);
            } else if (gameRules.getCurrentPlayer() == 0) {
                btnPressed.setText("O");
                btnPressed.setDisable(true);
                gameBoard.getGameText().setText("X's turn");
                gameRules.incrementTurnCounter();
                checkWinner(gameBoard.getGameButtons());
                gameRules.setCurrentPlayer(1);
            }
        };
        return handle;
    }

    // Takes all buttons and checks their text to check if there is a winner.
    public void checkWinner(ArrayList<Button> buttons) {
        Button[] checkedBtns = new Button[3];
        for (int x = 0; x < 8; x++) {
            String connect3 = switch (x) {
                // Horizontal Top
                case 0 -> {
                    checkedBtns[0] = buttons.get(0);
                    checkedBtns[1] = buttons.get(1);
                    checkedBtns[2] = buttons.get(2);
                    yield buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText();
                }
                // Horizontal Middle
                case 1 -> {
                    checkedBtns[0] = buttons.get(3);
                    checkedBtns[1] = buttons.get(4);
                    checkedBtns[2] = buttons.get(5);
                    yield buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText();
                }
                // Horizontal Bottom
                case 2 -> {
                    checkedBtns[0] = buttons.get(6);
                    checkedBtns[1] = buttons.get(7);
                    checkedBtns[2] = buttons.get(8);
                    yield buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText();
                }
                // Vertical Left
                case 3 -> {
                    checkedBtns[0] = buttons.get(0);
                    checkedBtns[1] = buttons.get(3);
                    checkedBtns[2] = buttons.get(6);
                    yield buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText();
                }
                // Vertical Middle
                case 4 -> {
                    checkedBtns[0] = buttons.get(1);
                    checkedBtns[1] = buttons.get(4);
                    checkedBtns[2] = buttons.get(7);
                    yield buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText();
                }
                // Vertical Right
                case 5 -> {
                    checkedBtns[0] = buttons.get(2);
                    checkedBtns[1] = buttons.get(5);
                    checkedBtns[2] = buttons.get(8);
                    yield buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText();
                }
                // Diagonal top left -> bottom right
                case 6 -> {
                    checkedBtns[0] = buttons.get(0);
                    checkedBtns[1] = buttons.get(4);
                    checkedBtns[2] = buttons.get(8);
                    yield buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText();
                }
                // Diagonal top right -> bottom left
                case 7 -> {
                    checkedBtns[0] = buttons.get(2);
                    checkedBtns[1] = buttons.get(4);
                    checkedBtns[2] = buttons.get(6);
                    yield buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText();
                }
                default -> null;
            };

            // X wins if the text of 3 buttons in a row is "XXX"
            if (connect3.equals("XXX")) {
                for (int i = 0; i < checkedBtns.length; i++) {
                    checkedBtns[i].setStyle("-fx-color: orange");
                }
                gameRules.setGameWinner(true);
                gameRules.setGameResultText("X wins!");
                gameBoard.getGameText().setText(gameRules.getGameResultText());
                for (Button b : gameBoard.getGameButtons()) {
                    b.setDisable(true);
                }
                gameRules.incrementTotalXWins();
                gameBoard.getTotalXText().setText(String.valueOf(gameRules.getTotalXWins()));
                System.out.println("X wins!");
            }
            // O wins if the text of 3 buttons in a row is "OOO"
            else if (connect3.equals("OOO")) {
                for (int i = 0; i < checkedBtns.length; i++) {
                    checkedBtns[i].setStyle("-fx-color: orange");
                }
                gameRules.setGameWinner(true);
                gameRules.setGameResultText("O wins!");
                gameBoard.getGameText().setText(gameRules.getGameResultText());
                for (Button b : gameBoard.getGameButtons()) {
                    b.setDisable(true);
                }
                gameRules.incrementTotalOWins();
                gameBoard.getTotalOText().setText(String.valueOf(gameRules.getTotalOWins()));
                System.out.println("O wins!");
            }
        }
        // Tie when turn count reaches 9
        if (gameRules.getTurnCounter() == 9 && gameRules.hasGameWinner() == false) {
            gameRules.setGameResultText("It's a tie!");
            gameBoard.getGameText().setText(gameRules.getGameResultText());
            for (Button b : gameBoard.getGameButtons()) {
                b.setDisable(true);
            }
            System.out.println("It's a tie!");
        }
    }
}
