package TicTacToe;

//Rules of the game

public class GameModel {

    private static GameModel model_instance = null;

    private GameModel() {
    }

    public static GameModel getInstance() {
        {
            if (model_instance == null)
                model_instance = new GameModel();

            return model_instance;
        }
    }

    // if currentPlayer == 0, it is O turn. if currentPlayer == 1, it is X turn.
    private int currentPlayer = 0;

    // Turn counter
    private int turnCounter = 0;

    // Declares winner
    private boolean gameWinner = false;

    // Result of the game to display
    private String gameResultText;

    // Total O wins;
    private int totalOWins = 0;

    // Total X wins;
    private int totalXWins = 0;

    public int getTotalOWins() {
        return totalOWins;
    }

    public void incrementTotalOWins() {
        this.totalOWins = totalOWins + 1;
    }

    public int getTotalXWins() {
        return totalXWins;
    }

    public void incrementTotalXWins() {
        this.totalXWins = totalXWins + 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public void incrementTurnCounter() {
        this.turnCounter += 1;
    }

    public boolean hasGameWinner() {
        return gameWinner;
    }

    public void setGameWinner(boolean gameWinner) {
        this.gameWinner = gameWinner;
    }

    public String getGameResultText() {
        return gameResultText;
    }

    public void setGameResultText(String resultText) {
        this.gameResultText = resultText;
    }

}
