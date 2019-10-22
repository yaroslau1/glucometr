package com.work.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {

    public String reading(String path) {
        String string = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String s;
            while ( (s = br.readLine()) != null) {
                if (string == null) {
                    string = s;
                } else {
                    string += s;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return string;

    }
}