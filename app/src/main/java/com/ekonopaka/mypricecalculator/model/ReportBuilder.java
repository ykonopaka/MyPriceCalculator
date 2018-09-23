package com.ekonopaka.mypricecalculator.model;

import java.util.HashMap;
import java.util.Map;

public class ReportBuilder {
    private final Map<Device, Integer> devicesMap;
    private final Map<Component, Integer> componentsMap;

    public ReportBuilder() {
        this.devicesMap = new HashMap<>();
        this.componentsMap = new HashMap<>();
    }

    public ReportBuilder addDevice(Device device) {
        Integer savedNumber = devicesMap.getOrDefault(device, 0);
        devicesMap.put(device, savedNumber + 1);
        return this;
    }

    public ReportBuilder addDevices(Device device, Integer number) {
        Integer savedNumber = devicesMap.getOrDefault(device, 0);
        devicesMap.put(device, savedNumber + number);
        return this;
    }

    public ReportBuilder addComponent(Component component) {
        Integer savedNumber = componentsMap.getOrDefault(component, 0);
        componentsMap.put(component, savedNumber + 1);
        return this;
    }

    public ReportBuilder addComponents(Component component, Integer number) {
        Integer savedNumber = componentsMap.getOrDefault(component, 0);
        componentsMap.put(component, savedNumber + number);
        return this;
    }

    public ReportOutput calculate(Double complexity) {
        double equipment = 0;

        for (Device device : devicesMap.keySet()) {
            equipment += device.getPrice() * devicesMap.get(device).doubleValue();
        }

        for (Component component : componentsMap.keySet()) {
            equipment += component.getPrice() * componentsMap.get(component).doubleValue();
        }

        double manager = equipment * 0.055D;
        double supportManager = equipment * 0.08D * complexity;
        double techician = equipment * 0.9D * complexity;
        double controlManager = equipment * 0.02D * complexity;
        double work = manager + supportManager + techician + controlManager;
        double total = equipment + work;

        return new ReportOutput(
                devicesMap,
                componentsMap,
                complexity,
                equipment,
                manager,
                supportManager,
                techician,
                controlManager,
                work,
                total
        );
    }

    public static class ReportOutput {
        private final Map<Device, Integer> devicesMap;
        private final Map<Component, Integer> componentsMap;
        private final Double complexity;
        private final Double equipment;
        private final Double manager;
        private final Double supportManager;
        private final Double techician;
        private final Double controlManager;
        private final Double work;
        private final Double total;

        public ReportOutput(Map<Device, Integer> devicesMap,
                            Map<Component, Integer> componentsMap,
                            Double complexity,
                            Double equipment,
                            Double manager,
                            Double supportManager,
                            Double techician,
                            Double controlManager,
                            Double work,
                            Double total) {
            this.devicesMap = devicesMap;
            this.componentsMap = componentsMap;
            this.complexity = complexity;
            this.equipment = equipment;
            this.manager = manager;
            this.supportManager = supportManager;
            this.techician = techician;
            this.controlManager = controlManager;
            this.work = work;
            this.total = total;
        }

        public Map<Device, Integer> getDevicesMap() {
            return devicesMap;
        }

        public Map<Component, Integer> getComponentsMap() {
            return componentsMap;
        }

        public Double getComplexity() {
            return complexity;
        }

        public Double getEquipment() {
            return equipment;
        }

        public Double getManager() {
            return manager;
        }

        public Double getSupportManager() {
            return supportManager;
        }

        public Double getTechician() {
            return techician;
        }

        public Double getControlManager() {
            return controlManager;
        }

        public Double getWork() {
            return work;
        }

        public Double getTotal() {
            return total;
        }
    }
}
