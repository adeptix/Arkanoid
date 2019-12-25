/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Ball extends Circle {

    private final double speed = 25;
    private double angle = Math.PI / 4;
//    private final AnimationTimer animationTimer;

    private final double minX;
    private final double maxX;
    private final double maxY;
    
    private double dX;
    private double dY;

    public Ball(double parentWidth, double parentHeight) {
        super(parentWidth / 60, Color.RED);
        // 
        setLayoutX(parentWidth / 2);
        setLayoutY(parentHeight / 2);

        minX = getRadius();
        maxX = parentWidth - getRadius();
        maxY = parentHeight - getRadius();
        System.out.println(minX);
        System.out.println(maxX);
        // setCenterX(maxX);
        //  setLayoutX(maxX); 
      //  calculatePath();

      

    }

//    public void move(double angle) {
//        setLayoutX(checkXBounds(getLayoutX() + speed * Math.cos(angle)));
//        setLayoutY(checkYBounds(getLayoutY() - speed * Math.sin(angle)));
//    }
    
//    public void move2(){
//        setLayoutX(checkXBounds(getLayoutX() + dX));
//        setLayoutY(checkYBounds(getLayoutY() + dY));
//    }
    
    public void moveTo(double newX, double newY){
        setLayoutX(newX);
        setLayoutY(newY);
    }
    
//    private void calculatePath(){
//        dX = speed * Math.cos(angle);
//        dY = speed * Math.sin(angle);
//        System.out.println("calculate");
//    }
        

//    private double checkXBounds(double x) {
//        if (x < minX) {
//            angle = Math.PI - angle;
//            calculatePath();
//            return minX;
//        }
//        if (x > maxX) {
//            angle = Math.PI - angle;
//              calculatePath();
//            return maxX;
//        }
//        return x;
//    }
//
//    private double checkYBounds(double y) {
//        if (y < getRadius()) {
//            angle = -angle;
//              calculatePath();
//            return getRadius();
//        }
//        if (y > maxY) {
//            angle = -angle;
//              calculatePath();
//            return maxY;
//        }
//        return y;
//    }
    
    public double getX(){
        return getLayoutX();
    }
    
    public double getY(){
        return getLayoutY();
    }

//    public void start() {
//        animationTimer.start();
//    }
//
//    public void pause() {
//        animationTimer.stop();
//    }

}
