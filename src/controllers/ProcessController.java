/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import models.Ball;
import models.Brick;

public class ProcessController extends AnimationTimer {

    private final Ball ball;
    private final double speed = 15;
    private double angle = Math.PI * 3 / 4;
    private double dX, dY;
    private final double radius;

    private final double minX;
    private final double minY;
    private final double maxX;
    private final double maxY;

    //private int k = 0;
    private double ballX, ballY;

    private final ArrayList<Brick> bricks;
    private double end;

    private final PlatformController platformController;

    public ProcessController(double zoneWidth, double zoneHeight, Ball ball, ArrayList<Brick> bricks, PlatformController platformController) {
        this.platformController = platformController;

        this.ball = ball;
        this.bricks = bricks;
        radius = ball.getRadius();

        ballX = ball.getX();
        ballY = ball.getY();

        minX = minY = radius;
        maxX = zoneWidth - radius;
        maxY = zoneHeight - radius;
        calculatePath();

        calcEnd();
      
    }

    private double checkXBounds(double x) {
        if (x < minX) {
            changeAngle(false);
            return minX;
        }
        if (x > maxX) {
            changeAngle(false);
            return maxX;
        }
        return x;
    }

    private double checkYBounds(double y) {
        if (y < minY) {
            changeAngle(true);
            return minY;
        }
        if (y > maxY) {
            changeAngle(true);
            return maxY;
        }
        return y;
    }

    private void changeAngle(boolean reverse) {
        if (reverse) {
            angle = -angle;
        } else {
            angle = Math.PI - angle;
        }
        calculatePath();
    }

    private void calculatePath() {
        dX = speed * Math.cos(angle);
        dY = -speed * Math.sin(angle);

    }

    @Override
    public void handle(long now) {
        ballX = checkXBounds(ballX + dX);
        ballY = checkYBounds(ballY + dY);

        checkCollision();
        ball.moveTo(ballX, ballY);

        platformController.movePlatform();

    }

    private void checkCollision() {

        Iterator iterator = bricks.iterator();

        while (iterator.hasNext()) {

            Brick brick = (Brick) iterator.next();

            double bottom = brick.getY() + brick.getHeight();//y =213
            double left = brick.getX(); //x = 231
            double right = brick.getX() + brick.getWidth(); //x = 12
            double up = brick.getY(); //y =123

            if (ball.getX() >= brick.getX() - ball.getRadius() && ball.getX() <= brick.getX() + brick.getWidth() + ball.getRadius()) {
                if (equation(bottom, true)) {
                   // ballY = bottom + radius;
                    brick.changeColor();

                }
                if (equation(up, true)) {
                   // ballY = up - radius;
                    brick.changeColor();

                }
            }
            if (ball.getY() >= brick.getY() - radius && ball.getY() <= brick.getY() + brick.getHeight() + radius) {
                if (equation(left, false)) {
                 //   ballX = left + radius;
                    brick.changeColor();

                }

                if (equation(right, false)) {
                  //  ballX = right + radius;
                    brick.changeColor();
                }
            }

            //ball = (x-ballX)^2 + (y-ballY)^2 = ball.getRadius()^2
            // x^2 - 2*ballX*x + ballX^2 - ball.getRadius()^2 + (y-ballY)^2 = 0
            //D = (2*ballX)^2 - 4*ballX^2 - ball.getRadius()^2 + (y-ballY)^2 >= 0
            //yes
        }
    }

    private boolean equation(double line, boolean findX) {

        double D;
        if (findX) {
            D = 2 * ballX * 2 * ballX - 4 * (ballX * ballX - ball.getRadius() * ball.getRadius() + (line - ballY) * (line - ballY));
        } else {
            D = 2 * ballY * 2 * ballY - 4 * (ballY * ballY - ball.getRadius() * ball.getRadius() + (line - ballX) * (line - ballX));
        }
        return D > 0;

    }

    private void checkCollision2() {
        for (Brick brick : bricks) {

            double left = brick.getX() - ball.getRadius();
            double right = brick.getX() + brick.getWidth() + ball.getRadius();
            double up = brick.getY() - ball.getRadius();
            double down = brick.getY() + brick.getHeight() + ball.getRadius();

            System.out.println(down);

            if (ballY < down) {

                if (ballY > up) {

                }

                if (ballX > left && ballX < right) {
                    brick.changeColor();
                    changeAngle(true);
                    ballY = down;
                    return;
                }
            }

//            if(ballY > up){
//                if (ballX > left && ballX < right) {
//                    brick.changeColor();
//                    changeAngle(true);
//                    ballY = up;
//                    return;
//                }
//            }
//            
//            if(ballX > left){
//                if (ballY > up && ballY < down) {
//                    brick.changeColor();
//                    changeAngle(false);
//                    ballX = left;
//                    return;
//                }
//            }
//            
//            if(ballX < right){
//                if (ballY > up && ballY < down) {
//                    brick.changeColor();
//                    changeAngle(false);
//                    ballX = right;
//                    return;
//                }
//            }
        }
    }

    private void calcEnd() {

        end = bricks.get(bricks.size() - 1).getY();
        System.out.println("end " + end);
    }

}
