/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle {
    
    private int defense;

    // private Double width, height;
    public Brick(double w, double h, int type) {
        super(w, h);
        
        setStroke(Color.BLACK);
        setStrokeWidth(2);
        
        defense = type;

        switch (type) {
            case 0:
                setFill(Color.BURLYWOOD);
                break;

            case 1:
                setFill(Color.CORAL);
                break;

            case 2:
                setFill(Color.AQUA);
                break;
        }
    }
    
    public void changeColor(){
        setFill(Color.TRANSPARENT);
        setStroke(Color.TRANSPARENT);
    }
    
 
    
    public boolean checkDefense(){
        if(defense != 0){
            defense--;
            return false;
        }
        return true;
    }
    
    
    
    

}
