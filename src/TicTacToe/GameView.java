package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Game Board

public class GameView {

    private static GameView board_instance = null;

    private GameView() {
    }

    public static GameView getInstance() {
        {
            if (board_instance == null)
                board_instance = new GameView();

            return board_instance;
        }
    }

    // Buttons for the game
    private Button b1 = new Button();
    private Button b2 = new Button();
    private Button b3 = new Button();
    private Button b4 = new Button();
    private Button b5 = new Button();
    private Button b6 = new Button();
    private Button b7 = new Button();
    private Button b8 = new Button();
    private Button b9 = new Button();

    // ArrayList for easier access to all buttons
    private ArrayList<Button> gameButtons = new ArrayList<>(Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9));

    public ArrayList<Button> getGameButtons() {
        return this.gameButtons;
    }

    // Menu Buttons
    private Button resetBtn = new Button("Reset");
    private Button startBtn = new Button("Start");

    public Button getResetBtn() {
        return resetBtn;
    }

    public Button getStartBtn() {
        return startBtn;
    }

    // Label to declare winner
    private Label gameText = new Label("Click start to begin");

    public Label getGameText() {
        return gameText;
    }

    // Left Text
    private Label OWins = new Label("O Wins:");
    private Text totalO = new Text("0");

    public Text getTotalOText() {
        return totalO;
    }

    // Right Text
    private Label XWins = new Label("X Wins:");
    private Text totalX = new Text("0");

    public Text getTotalXText() {
        return totalX;
    }

    // Font Styles
    private Font mainFont = Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 15);
    private Font btnFont = Font.font("Helvetica", 15);

    // Main pane to add all panes.
    public BorderPane getRoot() {
        BorderPane root = new BorderPane();
        root.setTop(getTop());
        root.setBottom(getBottom());
        root.setLeft(getLeft());
        root.setRight(getRight());
        root.setCenter(getCentre());
        return root;
    }

    // Top of BorderPane
    public HBox getTop() {
        // Top Text
        HBox topText = new HBox();
        topText.setPadding(new Insets(20, 20, 0, 20));
        topText.setAlignment(Pos.CENTER);
        topText.getChildren().add(gameText);
        // Styling
        gameText.setFont(mainFont);
        return topText;
    }

    // Bottom of BorderPane
    public HBox getBottom() {
        // Bottom Menu
        HBox bottomMenu = new HBox(50);

        bottomMenu.setPadding(new Insets(0, 20, 20, 20));
        bottomMenu.setAlignment(Pos.CENTER);
        bottomMenu.getChildren().addAll(resetBtn, startBtn);
        // Styling
        resetBtn.setPrefSize(100, 20);
        resetBtn.setFont(btnFont);
        startBtn.setPrefSize(100, 20);
        startBtn.setFont(btnFont);
        return bottomMenu;
    }

    // Left of BorderPane
    public VBox getLeft() {
        // Left O wins
        VBox leftText = new VBox();
        leftText.setPadding(new Insets(20, 0, 20, 20));
        leftText.setAlignment(Pos.CENTER);
        leftText.getChildren().addAll(OWins, totalO);
        // Styling
        OWins.setFont(mainFont);
        totalO.setFont(mainFont);
        return leftText;
    }

    // Right of BorderPane
    public VBox getRight() {
        // Right X wins
        VBox rightText = new VBox();
        rightText.setPadding(new Insets(20, 20, 20, 0));
        rightText.setAlignment(Pos.CENTER);
        rightText.getChildren().addAll(XWins, totalX);
        // Styling
        XWins.setFont(mainFont);
        totalX.setFont(mainFont);
        return rightText;
    }

    // Centre of BorderPane
    public StackPane getCentre() {
        // Game board lines
        // vertical lines
        Line l1 = new Line(125, 34, 125, 374);
        l1.setFill(Color.BLACK);
        Line l2 = new Line(235, 34, 235, 374);
        l2.setFill(Color.BLACK);

        // horizontal lines
        Line l3 = new Line(10, 149, 350, 149);
        l3.setFill(Color.BLACK);
        Line l4 = new Line(10, 259, 350, 259);
        l4.setFill(Color.BLACK);

        // Pane for the horizontal + vertical lines
        Pane borders = new Pane();
        borders.getChildren().addAll(l1, l2, l3, l4);

        // Create pane for clickable buttons
        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setPadding(new Insets(20));
        board.setHgap(10);
        board.setVgap(10);

        // Add buttons to board.
        board.add(b1, 0, 0);
        board.add(b2, 1, 0);
        board.add(b3, 2, 0);
        board.add(b4, 0, 1);
        board.add(b5, 1, 1);
        board.add(b6, 2, 1);
        board.add(b7, 0, 2);
        board.add(b8, 1, 2);
        board.add(b9, 2, 2);

        // Make each button 100x100 and set to disable until start.
        for (Button b : gameButtons) {
            b.setMinSize(100, 100);
            b.setDisable(true);
        }

        // Stack Game board lines and Buttons.
        StackPane stack = new StackPane();
        stack.getChildren().addAll(borders, board);

        return stack;
    }

    // Build the Scene
    public Scene getScene() {
        Scene scene = new Scene(getRoot(), 500, 500);
        return scene;
    }

}
