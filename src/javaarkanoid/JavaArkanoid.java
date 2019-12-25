/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarkanoid;

import controllers.PlatformController;
import controllers.ProcessController;
import java.io.InputStream;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.Ball;
import models.Brick;

/**
 *
 * @author Dmitry
 */
public class JavaArkanoid extends Application {

    Integer speed = 15;
    Integer direction = -1;
    BorderPane root;
    Pane canvas;
    Scene scene;
    Ball ball;
    AnimationTimer animationTimer;
    Boolean isPause = false;
    Timeline loop;
    LevelFactory levelManager;
    ProcessController processController;

    double sceneWidth, sceneHeight, canvasWidth, canvasHeight;

    @Override
    public void start(Stage primaryStage) {

        Rectangle2D primaryBounds = Screen.getPrimary().getBounds();

        sceneWidth = primaryBounds.getWidth() / 1.5;
        sceneHeight = sceneWidth / 16 * 9;

        root = new BorderPane();
        scene = new Scene(root, sceneWidth, sceneHeight);

        primaryStage.sizeToScene();

        primaryStage.setScene(scene);

        primaryStage.setTitle("Arkanoid");
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setX(primaryBounds.getWidth() / 2 - primaryStage.getWidth() / 2);
        primaryStage.setY(primaryBounds.getHeight() / 2 - primaryStage.getHeight() / 2);

        canvasWidth = sceneWidth;
        canvasHeight = sceneHeight * 0.9;

        canvas = new Pane();
        canvas.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        canvas.setPrefSize(canvasWidth, canvasHeight);
        root.setBottom(canvas);

        //Brick brick = new Brick(canvasWidth, canvasHeight, 0);
        //primaryStage.setFullScreen(true);
        //  canvas.getChildren().add(brick);
        levelManager = new LevelFactory();
        int[][] matrix = {{0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
        {1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 0, 1},
        {0, 0, 0, 1, 2, 1, 1, 2, 0, 0, 1, 0, 2},
        {0, 2, 1, 2, 1, 0, 0, 0, 1, 0, 2, 1, 0}};
        levelManager.build(canvas, matrix);

        initializeButton();

        ball = new Ball(canvasWidth, canvasHeight);
        canvas.getChildren().add(ball);

        PlatformController pc = new PlatformController(scene, canvas, canvasWidth, canvasHeight);

        processController = new ProcessController(canvasWidth, canvasHeight, ball, levelManager.getBricks(), pc);
       // processController.start();
        // ball.setLayoutY(0);
        //  ball.setLayoutX(canvasWidth/2);

//        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/heart.jpg"), 200, 200, true, true));
//        root.setLeft(imageView);
    }

    private void initializeButton() {
        Button pauseButton = new Button("| |");
        pauseButton.setPrefSize(root.getWidth() / 20, root.getHeight() / 20);
        BorderPane.setMargin(pauseButton, new Insets(10.0));
        root.setRight(pauseButton);

        pauseButton.setOnAction((event) -> {
            if (isPause) {
                processController.start();
                isPause = false;
            } else {
                processController.stop();
                isPause = true;
            }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
