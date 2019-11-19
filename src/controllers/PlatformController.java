/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlatformController {

    private final double speed = 10;
    private boolean isLeft = false;
    private boolean isRight = false;
    private final Rectangle platform;
    
    private final double zoneWidth;

    public PlatformController(Scene scene, Pane zone, double zoneWidth, double zoneHeight) {
       
        this.zoneWidth = zoneWidth;
        
        platform = new Rectangle(zoneWidth / 6, zoneHeight / 30);
        platform.relocate(zoneWidth / 2 - platform.getWidth() / 2, zoneHeight * 0.9);
        zone.getChildren().add(platform);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                case LEFT:
                    isLeft = true;
                    break;

                case D:
                case RIGHT:
                    isRight = true;
                    break;
            }
        });
        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case A:
                case LEFT:
                    isLeft = false;
                    break;

                case D:
                case RIGHT:
                    isRight = false;
                    break;
            }
        });
        
    }
    
    public void movePlatform(){
        if(!isLeft && !isRight) return;
        if(isLeft && isRight) return;
        if(isLeft) platform.setLayoutX(checkBounds(platform.getLayoutX() - speed));
        if(isRight) platform.setLayoutX(checkBounds(platform.getLayoutX() + speed));
    }
    
    private double checkBounds(double x){
        if(x < 0) return 0;
        
        if(x > zoneWidth - platform.getWidth()){
            return zoneWidth - platform.getWidth();
        }
        
        return x;
    }

}
