package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle{
    @Autowired
    private Tyre tyre;

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Tyre getTyre() {
        return tyre;
    }

    @Override
    public void Info() {
        System.out.println("Car has Four "+tyre+" wheel");
    }
}
