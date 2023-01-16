package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Samsung implements Mobile{
    @Autowired
    @Qualifier("exynos")
    private MobileProcessor cpu;

    public MobileProcessor getCpu() {
        return cpu;
    }

    public void setCpu(MobileProcessor cpu) {
        this.cpu = cpu;
    }

    @Override
    public void aboutPhone() {
        System.out.println("Samsung Processor: "+cpu);
    }
}
