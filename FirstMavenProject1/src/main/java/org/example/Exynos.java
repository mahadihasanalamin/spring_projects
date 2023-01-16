package org.example;

import org.springframework.stereotype.Component;

@Component
public class Exynos implements MobileProcessor{

    @Override
    public String toString() {
        return "Exynos 780";
    }
}
