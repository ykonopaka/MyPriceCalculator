package com.ekonopaka.mypricecalculator.model;

import java.util.Objects;

import static com.ekonopaka.mypricecalculator.model.Unit.pc;

public class Device {
    private final Type type;
    private final String name;
    private final Double price;
    private final Unit unit;
    private final Integer number;

    enum Type {
        Main("Прибор"),
        Keyboard("Клавиатура"),
        Battery("Аккумулятор"),
        Antenna("Антенна"),
        ExtensionModule("Модуль расширения"),
        Indication("Индикация");

        private final String title;

        Type(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public Device(Type type, String name, Double price, Unit unit, Integer number) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.number = number;
    }

    public static Device MainDevice(String name, Double price, Integer number) {
        return new Device(Type.Main, name, price, pc, number);
    }

    public static Device Keyboard(String name, Double price, Integer number) {
        return new Device(Type.Keyboard, name, price, pc, number);
    }

    public static Device Battery(String name, Double price, Integer number) {
        return new Device(Type.Battery, name, price, pc, number);
    }

    public static Device Antenna(String name, Double price, Integer number) {
        return new Device(Type.Antenna, name, price, pc, number);
    }

    public static Device ExtensionModule(String name, Double price, Integer number) {
        return new Device(Type.ExtensionModule, name, price, pc, number);
    }

    public static Device Indication(String name, Double price, Integer number) {
        return new Device(Type.Indication, name, price, pc, number);
    }

    public Type getType() {
        return type;
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
        Device device = (Device) o;
        return type == device.type &&
                Objects.equals(name, device.name) &&
                Objects.equals(price, device.price) &&
                Objects.equals(number, device.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, price, number);
    }
}
