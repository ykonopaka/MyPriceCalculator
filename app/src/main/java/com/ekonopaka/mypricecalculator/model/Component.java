package com.ekonopaka.mypricecalculator.model;

import java.util.Objects;

public class Component {
    private final String name;
    private final Double price;
    private final Unit unit;
    private final Integer number;

    public Component(String name, Double price, Unit unit, Integer number) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.number = number;
    }

    public static Component Component(String name, Double price, Unit unit, Integer number) {
        return new Component(name, price, unit, number);
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Unit getUnit() {
        return unit;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(name, component.name) &&
                Objects.equals(price, component.price) &&
                unit == component.unit &&
                Objects.equals(number, component.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, unit, number);
    }
}
