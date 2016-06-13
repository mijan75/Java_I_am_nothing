/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.reading.demo;

/**
 *
 * @author kmhasan
 */
public class Car {
    private String make;
    private String model;
    private int cc;
    private int seats;
    
    public Car(String carMake, String carModel, int carCc, int carSeats) {
        make = carMake;
        model = carModel;
        cc = carCc;
        seats = carSeats;
    }
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public int getCc() {
        return cc;
    }
    
    public int getSeats() {
        return seats;
    }
    
    public void print() {
        System.out.printf("%s %s %d %d\n", make, model, cc, seats);
    }
}

