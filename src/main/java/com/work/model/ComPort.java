package com.work.model;

import com.fazecast.jSerialComm.SerialPort;

public class ComPort{
    private String systemName;
    private String descriptiveName;
    private SerialPort port;

    public ComPort() {
    }

    public ComPort(String systemName) {
    }

    public String getSystemName() {
        return port.getSystemPortName();
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDescriptiveName() {
        return port.getDescriptivePortName();
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }
}
