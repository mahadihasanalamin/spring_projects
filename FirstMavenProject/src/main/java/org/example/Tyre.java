package org.example;

import org.springframework.stereotype.Component;

@Component
public class Tyre {
    private String brand;
    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getBrand(){
        return brand;
    }

//    public Tyre(String brand) {
//        this.brand = brand;
//    }

    @Override
    public String toString() {
        return "MRF";
    }
}
