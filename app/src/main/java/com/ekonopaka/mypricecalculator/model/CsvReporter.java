package com.ekonopaka.mypricecalculator.model;

import com.ekonopaka.mypricecalculator.model.ReportBuilder.ReportOutput;

import java.util.Map;

import static com.ekonopaka.mypricecalculator.model.Values.df2;
import static com.ekonopaka.mypricecalculator.model.Values.df3;
import static java.util.Comparator.comparing;

public class CsvReporter {
    private StringBuilder sb;

    public CsvReporter() {
        this.sb = new StringBuilder();
        this.sb.append("Наименование")
                .append(";")
                .append("Единицы измерения")
                .append(";")
                .append("Количество")
                .append(";")
                .append("Цена")
                .append(";")
                .append("Сумма")
                .append("\r\n");
    }

    public String build(ReportOutput reportOutput) {
        Map<Device, Integer> deviceMap = reportOutput.getDevicesMap();
        deviceMap.keySet().stream()
                .sorted(comparing(Device::getNumber))
                .forEach(e -> {
                    if (deviceMap.get(e) > 0 && e.getPrice() > 0) {
                        sb.append(e.getType().getTitle())
                                .append(" ")
                                .append(e.getName())
                                .append(";")
                                .append(e.getUnit().getTitle())
                                .append(";")
                                .append(deviceMap.get(e))
                                .append(";")
                                .append(df2.format(e.getPrice()))
                                .append(";")
                                .append(df2.format(e.getPrice() * deviceMap.get(e)))
                                .append("\r\n");
                    }
                });

        Map<Component, Integer> componentMap = reportOutput.getComponentsMap();
        componentMap.keySet().stream()
                .sorted(comparing(Component::getNumber))
                .forEach(e -> {
                    if (componentMap.get(e) > 0 && e.getPrice() > 0) {
                        sb.append(e.getName())
                                .append(";")
                                .append(e.getUnit().getTitle())
                                .append(";")
                                .append(componentMap.get(e))
                                .append(";")
                                .append(df2.format(e.getPrice()))
                                .append(";")
                                .append(df2.format(e.getPrice() * componentMap.get(e)))
                                .append("\r\n");
                    }
                });
        sb.append("Всего:").append(";;;;").append(df2.format(reportOutput.getEquipment())).append("\r\n");
        sb.append("Коэффициент сложности:").append(";;;;").append(df3.format(reportOutput.getComplexity())).append("\r\n");
        sb.append("Работа:").append(";;;;").append(df2.format(reportOutput.getWork())).append("\r\n");
        sb.append("Итого:").append(";;;;").append(df2.format(reportOutput.getTotal())).append("\r\n");

        return sb.toString();
    }
}
