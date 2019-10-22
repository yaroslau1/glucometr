package com.work.comPort;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.work.control.Control;
import com.work.controller.MainController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;

public class ComPortConnectivity implements SerialPortDataListener {

    private SerialPort userPort;
    private InputStream in;
    private LinkedList<Byte> bytesRiesived = new LinkedList<>();
    private long start;
    private long stop;

    public ComPortConnectivity() {

    }

    public ComPortConnectivity(String comPortName) {
        userPort = SerialPort.getCommPort(comPortName);
        userPort.addDataListener(this);
    }

    public SerialPort getUserPort() {
        return this.userPort;
    }

    public static String[] getPortNames() {
        SerialPort[] ports = SerialPort.getCommPorts();
        String[] result = new String[ports.length];
        for (int i = 0; i < ports.length; i++) {
            // result[i] = ports[i].getSystemPortName();
            result[i] = ports[i].getDescriptivePortName();
        }
        return result;
    }

    public static String getPortName() {
        SerialPort[] ports = SerialPort.getCommPorts();
        String result = null;
        for (int i = 0; i < ports.length; i++) {
            if (ports[i].getDescriptivePortName().contains("USB-SERIAL CH340"))
                result = ports[i].getSystemPortName();
        }
        return result;
    }

    public void openPort() {
        userPort.setBaudRate(115200);
        userPort.setNumDataBits(8);
        userPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        userPort.setParity(SerialPort.NO_PARITY);
        userPort.openPort();
    }

    public void openPort(int baudRate, int numDataBits, int numStopBits, int parity) {
        userPort.setBaudRate(baudRate);
        userPort.setNumDataBits(numDataBits);
        userPort.setNumStopBits(numStopBits);
        userPort.setParity(parity);
        userPort.openPort();
    }

    public void closePort() {
        userPort.closePort();
    }

    public void sendString(String string) {

    }

    public void sendStartBytes(byte[] bytes) {
        userPort.writeBytes(bytes, bytes.length);

    }

    public void sendEndBytes(byte[] bytes) {
        userPort.writeBytes(bytes, bytes.length);

    }

    public void sendBytes(byte[] bytes) {
        userPort.writeBytes(bytes, bytes.length);

    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (userPort.bytesAvailable() > 0) {
            int counterBytes;
            do {
                counterBytes = userPort.bytesAvailable();
                //System.out.println("counter" + counterBytes);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println(userPort.bytesAvailable());
            } while (counterBytes < userPort.bytesAvailable());

            byte[] readBuffer = new byte[userPort.bytesAvailable()];
            int numRead = userPort.readBytes(readBuffer, readBuffer.length);
            for (int i = 0; i < readBuffer.length; i++) {
                bytesRiesived.add(readBuffer[i]);
            }
            System.out.println(bytesRiesived);
            Control control = new Control();
            control.addToList(bytesRiesived);
            System.out.println("end");
            bytesRiesived.clear();
            MainController mainController = new MainController();
            mainController.test();
        }
    }

}
