package com.base.advance.reflact;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/4/1 23:04
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public class MyCar {
    private String carType;
    private String carName;
    private int number;

    public MyCar(){

    }

    public MyCar(String carType,String carName,int number){
        this.carName=carName;
        this.carType=carType;
        this.number=number;
    }
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public void display(){
        System.out.println("Test--------23:09--->:"+" carType:"+carType+" carName:"+carName+" number:"+number);
    }



}
