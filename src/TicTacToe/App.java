package TicTacToe;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        GameController game = new GameController();
        game.run();

        stage.setScene(game.getGameBoard());
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        stage.show();
    }

}
