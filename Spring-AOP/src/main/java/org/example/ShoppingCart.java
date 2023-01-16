package org.example;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void checkout( String status){
        System.out.println("From ShoppingCart Checkout method is called");
    }
    public int quantity(){
        return 7;
    }
}
