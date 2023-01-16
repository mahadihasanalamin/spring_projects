package org.example;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class OnePlus implements Mobile, BeanNameAware {

    @Autowired
    @Qualifier("snapdragon")
    private MobileProcessor cpu;
    private String ram;

    public MobileProcessor getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setCpu(MobileProcessor cpu) {
        this.cpu = cpu;
    }

    @Override
    public void aboutPhone() {
        System.out.println("OnePlus Processor: "+cpu);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("set bean name method is called");
    }
}
