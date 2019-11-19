/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarkanoid;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import models.Brick;

/**
 *
 * @author Dmitry
 */
public class LevelFactory {
    
    private final ArrayList<Brick> bricks = new ArrayList<>();

    public void build(Pane parent, int [][] matrix){
        
        int rows = matrix.length; 
        int columns = matrix[0].length;

        double brickWidth = parent.getPrefWidth()/ columns;
        double brickHeight = brickWidth / 3;
        
        double newX = 0,newY = 0;        
        
        
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Brick brick = new Brick(brickWidth, brickHeight, matrix[i][j]);
              
                brick.setX(newX);
                brick.setY(newY);
                parent.getChildren().add(brick);
                bricks.add(brick);
                newX += brickWidth;
            }
            newX = 0;
            newY += brickHeight;
        }
        
        
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
    
    
    
}
