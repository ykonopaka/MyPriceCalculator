package com.ekonopaka.mypricecalculator;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ekonopaka.mypricecalculator.model.CsvReporter;
import com.ekonopaka.mypricecalculator.model.Device;
import com.ekonopaka.mypricecalculator.model.ReportBuilder;
import com.ekonopaka.mypricecalculator.model.ReportBuilder.ReportOutput;
import com.ekonopaka.mypricecalculator.model.Values;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.ekonopaka.mypricecalculator.model.Values.boxBrown;
import static com.ekonopaka.mypricecalculator.model.Values.boxWhite;
import static com.ekonopaka.mypricecalculator.model.Values.boxYellow;
import static com.ekonopaka.mypricecalculator.model.Values.cableFourWires;
import static com.ekonopaka.mypricecalculator.model.Values.cableTenWires;
import static com.ekonopaka.mypricecalculator.model.Values.clip;
import static com.ekonopaka.mypricecalculator.model.Values.df2;
import static com.ekonopaka.mypricecalculator.model.Values.findDeviceByName;
import static com.ekonopaka.mypricecalculator.model.Values.montageSet;
import static com.ekonopaka.mypricecalculator.model.Values.powerCord;
import static com.ekonopaka.mypricecalculator.model.Values.sensorCOMKBrown;
import static com.ekonopaka.mypricecalculator.model.Values.sensorCOMKWhite;
import static com.ekonopaka.mypricecalculator.model.Values.sensorSPR600;
import static com.ekonopaka.mypricecalculator.model.Values.sensorSWANPGB;
import static com.ekonopaka.mypricecalculator.model.Values.sensorSmoke;
import static com.ekonopaka.mypricecalculator.model.Values.sensorSwanQuad;
import static com.ekonopaka.mypricecalculator.model.Values.twistedPairOutside;
import static java.util.Collections.singletonList;

public class CalculatorActivity extends AppCompatActivity {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        createControls();
    }

    private void createControls() {
        Spinner sltDevice = findViewById(R.id.sltDevice);
        List<String> sltDeviceItems = Values.mainDevices.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltDeviceItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltDeviceItems);
        sltDevice.setAdapter(sltDeviceItemsAdapter);

        Spinner sltDeviceQuantity = findViewById(R.id.sltDeviceQuantity);
        List<Integer> sltDeviceQuantityItems = singletonList(1);
        ArrayAdapter<Integer> sltDeviceQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltDeviceQuantityItems);
        sltDeviceQuantity.setAdapter(sltDeviceQuantityItemsAdapter);

        Spinner sltKeyboard = findViewById(R.id.sltKeyboard);
        List<String> sltKeyboardItems = Values.primaryKeyboards.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltKeyboardItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltKeyboardItems);
        sltKeyboard.setAdapter(sltKeyboardItemsAdapter);

        Spinner sltKeyboardQuantity = findViewById(R.id.sltKeyboardQuantity);
        List<Integer> sltKeyboardQuantityItems = Arrays.asList(1, 2);
        ArrayAdapter<Integer> sltKeyboardQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltKeyboardQuantityItems);
        sltKeyboardQuantity.setAdapter(sltKeyboardQuantityItemsAdapter);

        Spinner sltKeyboardSecond = findViewById(R.id.sltKeyboard2);
        List<String> sltKeyboardSecondItems = Values.secondaryKeyboards.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltKeyboardSecondItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltKeyboardSecondItems);
        sltKeyboardSecond.setAdapter(sltKeyboardSecondItemsAdapter);

        Spinner sltKeyboardSecondQuantity = findViewById(R.id.sltKeyboardQuantity2);
        List<Integer> sltKeyboardSecondQuantityItems = Arrays.asList(0, 1, 2);
        ArrayAdapter<Integer> sltKeyboardSecondQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltKeyboardSecondQuantityItems);
        sltKeyboardSecondQuantity.setAdapter(sltKeyboardSecondQuantityItemsAdapter);

        Spinner sltBattery = findViewById(R.id.sltBattery);
        List<String> sltBatteryItems = Values.batteries.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltBatteryItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltBatteryItems);
        sltBattery.setAdapter(sltBatteryItemsAdapter);

        Spinner sltBatteryQuantity = findViewById(R.id.sltBatteryQuantity);
        List<Integer> sltBatteryQuantityItems = singletonList(1);
        ArrayAdapter<Integer> sltBatteryQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltBatteryQuantityItems);
        sltBatteryQuantity.setAdapter(sltBatteryQuantityItemsAdapter);

        Spinner sltAntenna = findViewById(R.id.sltAntenna);
        List<String> sltAntennaItems = Values.antennas.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltAntennaItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltAntennaItems);
        sltAntenna.setAdapter(sltAntennaItemsAdapter);

        Spinner sltAntennaQuantity = findViewById(R.id.sltAntennaQuantity);
        List<Integer> sltAntennaQuantityItems = singletonList(1);
        ArrayAdapter<Integer> sltAntennaQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltAntennaQuantityItems);
        sltAntennaQuantity.setAdapter(sltAntennaQuantityItemsAdapter);

        Spinner sltExtModule = findViewById(R.id.sltExtModule);
        List<String> sltExtModuleItems = Values.extensionModules.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltExtModuleItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltExtModuleItems);
        sltExtModule.setAdapter(sltExtModuleItemsAdapter);

        Spinner sltExtModuleQuantity = findViewById(R.id.sltExtModuleQuantity);
        List<Integer> sltExtModuleQuantityItems = Arrays.asList(0, 1);
        ArrayAdapter<Integer> sltExtModuleQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltExtModuleQuantityItems);
        sltExtModuleQuantity.setAdapter(sltExtModuleQuantityItemsAdapter);

        Spinner sltIndication = findViewById(R.id.sltIndication);
        List<String> sltIndicationItems = Values.indicationModules.stream().map(Device::getName).collect(Collectors.toList());
        ArrayAdapter<String> sltIndicationItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltIndicationItems);
        sltIndication.setAdapter(sltIndicationItemsAdapter);

        Spinner sltIndicationQuantity = findViewById(R.id.sltIndicationQuantity);
        List<Integer> sltIndicationQuantityItems = Collections.singletonList(1);
        ArrayAdapter<Integer> sltIndicationQuantityItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sltIndicationQuantityItems);
        sltIndicationQuantity.setAdapter(sltIndicationQuantityItemsAdapter);

        ArrayAdapter sensorArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Values.sensorQuantity);
        Spinner sltSensorSwanQuadQuantity = findViewById(R.id.sltSensorSwanQuadQuantity);
        sltSensorSwanQuadQuantity.setAdapter(sensorArrayAdapter);

        Spinner sltSensorSPR600Quantity = findViewById(R.id.sltSensorSPR600Quantity);
        sltSensorSPR600Quantity.setAdapter(sensorArrayAdapter);

        Spinner sltSensorSWANPGBQuantity = findViewById(R.id.sltSensorSWANPGBQuantity);
        sltSensorSWANPGBQuantity.setAdapter(sensorArrayAdapter);

        Spinner sltSensorCOMKWhiteQuantity = findViewById(R.id.sltSensorCOMKWhiteQuantity);
        sltSensorCOMKWhiteQuantity.setAdapter(sensorArrayAdapter);

        Spinner sltSensorCOMKBrownQuantity = findViewById(R.id.sltSensorCOMKBrownQuantity);
        sltSensorCOMKBrownQuantity.setAdapter(sensorArrayAdapter);

        Spinner sltSensorSmokeQuantity = findViewById(R.id.sltSensorSmokeQuantity);
        sltSensorSmokeQuantity.setAdapter(sensorArrayAdapter);

        ArrayAdapter cableArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Values.cabelQuantity);
        Spinner sltCableFourWiresQuantity = findViewById(R.id.sltCableFourWiresQuantity);
        sltCableFourWiresQuantity.setAdapter(cableArrayAdapter);

        Spinner sltCableTenWiresQuantity = findViewById(R.id.sltCableTenWiresQuantity);
        sltCableTenWiresQuantity.setAdapter(cableArrayAdapter);

        Spinner sltTwistedPairOutsideQuantity = findViewById(R.id.sltTwistedPairOutsideQuantity);
        sltTwistedPairOutsideQuantity.setAdapter(cableArrayAdapter);

        Spinner sltlblPowerCordQuantity = findViewById(R.id.sltPowerCordQuantity);
        sltlblPowerCordQuantity.setAdapter(cableArrayAdapter);

        ArrayAdapter boxArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Values.boxQuantity);
        Spinner sltBoxWhiteQuantity = findViewById(R.id.sltBoxWhiteQuantity);
        sltBoxWhiteQuantity.setAdapter(boxArrayAdapter);

        Spinner sltBoxYellowQuantity = findViewById(R.id.sltBoxYellowQuantity);
        sltBoxYellowQuantity.setAdapter(boxArrayAdapter);

        Spinner sltBoxBrownQuantity = findViewById(R.id.sltBoxBrownQuantity);
        sltBoxBrownQuantity.setAdapter(boxArrayAdapter);

        Spinner sltClipQuantity = findViewById(R.id.sltClipQuantity);
        sltClipQuantity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(0, 1)));

        Spinner sltMontageSetQuantity = findViewById(R.id.sltMontageSetQuantity);
        sltMontageSetQuantity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(1)));

        Spinner sltComplexity = findViewById(R.id.sltComplexity);
        sltComplexity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Values.complexity));
        sltComplexity.setSelection(1);
    }

    public void onClickCalc(View v) {
        ReportOutput reportOutput = calculate();
        StringBuilder sb = new StringBuilder()
                .append("Оборудование: ")
                .append(df2.format(reportOutput.getEquipment()))
                .append(" грн")
                .append("\r\n")
                .append("Работа: ")
                .append(df2.format(reportOutput.getWork()))
                .append(" грн")
                .append("\r\n")
                .append("Итого: ")
                .append(df2.format(reportOutput.getTotal()))
                .append(" грн")
                .append("\r\n");

        TextView textView = findViewById(R.id.txtQuickCalculation);
        textView.setText(sb.toString());
    }

    public ReportOutput calculate() {
        Spinner sltDevice = findViewById(R.id.sltDevice);
        Spinner sltDeviceQuantity = findViewById(R.id.sltDeviceQuantity);
        Spinner sltKeyboard = findViewById(R.id.sltKeyboard);
        Spinner sltKeyboardQuantity = findViewById(R.id.sltKeyboardQuantity);
        Spinner sltKeyboard2 = findViewById(R.id.sltKeyboard2);
        Spinner sltKeyboardQuantity2 = findViewById(R.id.sltKeyboardQuantity2);
        Spinner sltBattery = findViewById(R.id.sltBattery);
        Spinner sltBatteryQuantity = findViewById(R.id.sltBatteryQuantity);
        Spinner sltAntenna = findViewById(R.id.sltAntenna);
        Spinner sltAntennaQuantity = findViewById(R.id.sltAntennaQuantity);
        Spinner sltExtModule = findViewById(R.id.sltExtModule);
        Spinner sltExtModuleQuantity = findViewById(R.id.sltExtModuleQuantity);
        Spinner sltIndication = findViewById(R.id.sltIndication);
        Spinner sltIndicationQuantity = findViewById(R.id.sltIndicationQuantity);

        Spinner sltSensorSwanQuadQuantity = findViewById(R.id.sltSensorSwanQuadQuantity);
        Spinner sltSensorSPR600Quantity = findViewById(R.id.sltSensorSPR600Quantity);
        Spinner sltSensorSWANPGBQuantity = findViewById(R.id.sltSensorSWANPGBQuantity);
        Spinner sltSensorCOMKWhiteQuantity = findViewById(R.id.sltSensorCOMKWhiteQuantity);
        Spinner sltSensorCOMKBrownQuantity = findViewById(R.id.sltSensorCOMKBrownQuantity);
        Spinner sltSensorSmokeQuantity = findViewById(R.id.sltSensorSmokeQuantity);
        Spinner sltCableFourWiresQuantity = findViewById(R.id.sltCableFourWiresQuantity);
        Spinner sltCableTenWiresQuantity = findViewById(R.id.sltCableTenWiresQuantity);
        Spinner sltTwistedPairOutsideQuantity = findViewById(R.id.sltTwistedPairOutsideQuantity);
        Spinner sltPowerCordQuantity = findViewById(R.id.sltPowerCordQuantity);
        Spinner sltBoxWhiteQuantity = findViewById(R.id.sltBoxWhiteQuantity);
        Spinner sltBoxYellowQuantity = findViewById(R.id.sltBoxYellowQuantity);
        Spinner sltBoxBrownQuantity = findViewById(R.id.sltBoxBrownQuantity);
        Spinner sltClipQuantity = findViewById(R.id.sltClipQuantity);
        Spinner sltMontageSetQuantity = findViewById(R.id.sltMontageSetQuantity);

        Device device = findDeviceByName((String) sltDevice.getSelectedItem()).get();
        Integer deviceQuantity = (Integer) sltDeviceQuantity.getSelectedItem();
        Device keyboard = findDeviceByName((String) sltKeyboard.getSelectedItem()).get();
        Integer keyboardQuantity = (Integer) sltKeyboardQuantity.getSelectedItem();
        Device keyboard2 = findDeviceByName((String) sltKeyboard2.getSelectedItem()).get();
        Integer keyboardQuantity2 = (Integer) sltKeyboardQuantity2.getSelectedItem();
        Device battery = findDeviceByName((String) sltBattery.getSelectedItem()).get();
        Integer batteryQuantity = (Integer) sltBatteryQuantity.getSelectedItem();
        Device antenna = findDeviceByName((String) sltAntenna.getSelectedItem()).get();
        Integer antennaQuantity = (Integer) sltAntennaQuantity.getSelectedItem();
        Device extModule = findDeviceByName((String) sltExtModule.getSelectedItem()).get();
        Integer extModuleQuantity = (Integer) sltExtModuleQuantity.getSelectedItem();
        Device indication = findDeviceByName((String) sltIndication.getSelectedItem()).get();
        Integer indicationQuantity = (Integer) sltIndicationQuantity.getSelectedItem();

        Integer sensorSwanQuadQuantity = (Integer) sltSensorSwanQuadQuantity.getSelectedItem();
        Integer sensorSPR600Quantity = (Integer) sltSensorSPR600Quantity.getSelectedItem();
        Integer sensorSWANPGBQuantity = (Integer) sltSensorSWANPGBQuantity.getSelectedItem();
        Integer sensorCOMKWhiteQuantity = (Integer) sltSensorCOMKWhiteQuantity.getSelectedItem();
        Integer sensorCOMKBrownQuantity = (Integer) sltSensorCOMKBrownQuantity.getSelectedItem();
        Integer sensorSmokeQuantity = (Integer) sltSensorSmokeQuantity.getSelectedItem();
        Integer cableFourWiresQuantity = (Integer) sltCableFourWiresQuantity.getSelectedItem();
        Integer cableTenWiresQuantity = (Integer) sltCableTenWiresQuantity.getSelectedItem();
        Integer twistedPairOutsideQuantity = (Integer) sltTwistedPairOutsideQuantity.getSelectedItem();
        Integer powerCordQuantity = (Integer) sltPowerCordQuantity.getSelectedItem();
        Integer boxWhiteQuantity = (Integer) sltBoxWhiteQuantity.getSelectedItem();
        Integer boxYellowQuantity = (Integer) sltBoxYellowQuantity.getSelectedItem();
        Integer boxBrownQuantity = (Integer) sltBoxBrownQuantity.getSelectedItem();
        Integer clipQuantity = (Integer) sltClipQuantity.getSelectedItem();
        Integer montageSetQuantity = (Integer) sltMontageSetQuantity.getSelectedItem();

        Spinner sltComplexity = findViewById(R.id.sltComplexity);
        Double complexity = (Double) sltComplexity.getSelectedItem();

        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder
                .addDevices(device, deviceQuantity)
                .addDevices(keyboard, keyboardQuantity)
                .addDevices(keyboard2, keyboardQuantity2)
                .addDevices(battery, batteryQuantity)
                .addDevices(antenna, antennaQuantity)
                .addDevices(extModule, extModuleQuantity)
                .addDevices(indication, indicationQuantity)
                .addComponents(sensorSwanQuad, sensorSwanQuadQuantity)
                .addComponents(sensorSPR600, sensorSPR600Quantity)
                .addComponents(sensorSWANPGB, sensorSWANPGBQuantity)
                .addComponents(sensorCOMKWhite, sensorCOMKWhiteQuantity)
                .addComponents(sensorCOMKBrown, sensorCOMKBrownQuantity)
                .addComponents(sensorSmoke, sensorSmokeQuantity)
                .addComponents(cableFourWires, cableFourWiresQuantity)
                .addComponents(cableTenWires, cableTenWiresQuantity)
                .addComponents(twistedPairOutside, twistedPairOutsideQuantity)
                .addComponents(powerCord, powerCordQuantity)
                .addComponents(boxWhite, boxWhiteQuantity)
                .addComponents(boxBrown, boxBrownQuantity)
                .addComponents(boxYellow, boxYellowQuantity)
                .addComponents(clip, clipQuantity)
                .addComponents(montageSet, montageSetQuantity);

        return reportBuilder.calculate(complexity);
    }

    public void onClickSave(View v) {
        ReportOutput reportOutput = calculate();
        String filename = format.format(new Date()) + ".txt";
        String content = new CsvReporter().build(reportOutput);
        saveToFile(filename, content);
        Toast.makeText(this, "Saved as " + filename, Toast.LENGTH_LONG).show();
    }

    public void saveToFile(String filename, String content) {
        File output = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),filename);
        try {
            FileOutputStream fileout = new FileOutputStream(output.getAbsolutePath());
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout, Charset.forName("UTF-8").newEncoder());
            outputWriter.write(content);
            outputWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void onClickReset(View v) {
        createControls();
    }
}
