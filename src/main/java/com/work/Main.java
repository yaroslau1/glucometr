package com.work;

import com.work.comPort.ComPortConnectivity;
import com.work.file.Write;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Write.clear("notes3.txt");
        ComPortConnectivity connectivity = new ComPortConnectivity(ComPortConnectivity.getPortName());
        connectivity.openPort();
    }
}
