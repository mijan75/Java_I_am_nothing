/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.car.program;

/**
 *
 * @author mijan
 */
public class car {
    
    private String makeName;
    private String modelName;
    private int carCC;
    private int carSeats ;
    
    public car(String mName,String moName,int cc,int seats){
        makeName=mName;
        modelName=moName;
        carCC=cc;
        carSeats=seats;
    }
    
    public String getmakeName(){
        return makeName;
    } 
    
    public String getmodelName(){
        return modelName;
    } 
    
    public int getCC(){
        return carCC;
    }
    
    public int getcarSeats(){
        return carSeats;
    }

    public void print(){
        System.out.printf("%s %s %d %d",makeName,modelName,carCC,carSeats);
    }
}
