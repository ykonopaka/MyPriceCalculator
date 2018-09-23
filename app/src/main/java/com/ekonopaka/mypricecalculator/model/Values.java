package com.ekonopaka.mypricecalculator.model;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.ekonopaka.mypricecalculator.model.Component.Component;
import static com.ekonopaka.mypricecalculator.model.Device.Antenna;
import static com.ekonopaka.mypricecalculator.model.Device.Battery;
import static com.ekonopaka.mypricecalculator.model.Device.ExtensionModule;
import static com.ekonopaka.mypricecalculator.model.Device.Indication;
import static com.ekonopaka.mypricecalculator.model.Device.Keyboard;
import static com.ekonopaka.mypricecalculator.model.Device.MainDevice;
import static com.ekonopaka.mypricecalculator.model.Unit.m;
import static com.ekonopaka.mypricecalculator.model.Unit.pc;
import static java.util.Arrays.asList;

public class Values {

    public static final DecimalFormat df2 = new DecimalFormat("#0.00");
    public static final DecimalFormat df3 = new DecimalFormat("#0.000");

    private Values() {
    }

    public static final List<Device> mainDevices = asList(
            MainDevice("Кронос моноблок", 105D, 1),
            MainDevice("Кронос ОП-4", 240D, 2),
            MainDevice("Кронос ОП-8 30Вт", 240D, 3),
            MainDevice("Кронос ОП-8 50Вт", 250D, 4),
            MainDevice("Кронос ОП-8 новый", 222D, 5),
            MainDevice("Лунь-11", 160D, 6));

    public static final List<Device> primaryKeyboards = asList(
            Keyboard("КЖ2", 46D, 7),
            Keyboard("УПУ-А", 10D, 8),
            Keyboard("РПУ44-ТМ", 30D, 9),
            Keyboard("Линд-11", 29D, 10),
            Keyboard("Линд-11 LCD", 80D, 11));

    public static final List<Device> secondaryKeyboards = asList(
            Keyboard("Нет клав.", 0D, 12),
            Keyboard("КЖ2", 46D, 13),
            Keyboard("УПУ-А", 10D, 14),
            Keyboard("РПУ44-ТМ", 30D, 15),
            Keyboard("Линд-11", 29D, 16),
            Keyboard("Линд-11 LCD", 80D, 17));

    public static final List<Device> batteries = asList(
            Battery("7Ah", 20D, 18),
            Battery("17Ah", 65D, 19));

    public static final List<Device> antennas = asList(
            Antenna("Штыревая", 0.01D, 20),
            Antenna("Внешняя", 10D, 21));

    public static final List<Device> extensionModules = asList(
            ExtensionModule("Нет модуля", 0D, 22),
            ExtensionModule("Внутренний", 19D, 23));

    public static final List<Device> indicationModules = asList(
            Indication("Сирена", 24D, 24),
            Indication("Светодиод", 1D, 25));

    public static final Component sensorSwanQuad = Component("Датчик SWAN QUAD", 12D, pc, 1);
    public static final Component sensorSPR600 = Component("Датчик SPR-600", 15D, pc, 2);
    public static final Component sensorSWANPGB = Component("Датчик SWAN PGB", 25D, pc, 3);
    public static final Component sensorCOMKWhite = Component("Датчик СОМК белый", 5D, pc, 4);
    public static final Component sensorCOMKBrown = Component("Датчик СОМК коричневый", 6D, pc, 5);
    public static final Component sensorSmoke = Component("Датчик дыма", 10D, pc, 6);

    public static final Component cableFourWires = Component("Кабель 4 жилы", 0.2D, m, 7);
    public static final Component cableTenWires = Component("Кабель 10 жил", 0.32D, m, 8);
    public static final Component twistedPairOutside = Component("Витая пара наружная", 0.8D, m, 9);
    public static final Component powerCord = Component("Сетевой кабель", 0.45D, m, 10);

    public static final Component boxWhite = Component("Короб белый", 1.4D, pc, 11);
    public static final Component boxYellow = Component("Короб желтый", 1.4D, pc, 12);
    public static final Component boxBrown = Component("Короб коричневый", 1.4D, pc, 13);

    public static final Component clip = Component("Скоба", 5D, pc, 14);
    public static final Component montageSet = Component("Монтажный комплект", 3D, pc, 15);

    public static List<Integer> sensorQuantity = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    public static List<Integer> cabelQuantity = Arrays.asList(0, 25, 50, 75, 100);
    public static List<Integer> boxQuantity = Arrays.asList(0, 4, 10);

    public static List<Double> complexity = Arrays.asList(0.195D, 0.205D, 0.215D, 0.225D, 0.23D);

    public static Optional<Device> findDeviceByName(String name) {
        return Stream.of(
                mainDevices,
                primaryKeyboards,
                secondaryKeyboards,
                batteries,
                antennas,
                extensionModules,
                indicationModules)
                .flatMap(Collection::stream)
                .filter(f -> f.getName().equals(name))
                .findFirst();
    }
}