package com.work.control;

import com.work.file.Write;

import java.util.ArrayList;
import java.util.LinkedList;


public class Control extends Thread {

    private ArrayList<Byte> bytesRiesived;
    private LinkedList<Integer> listIntegers = new LinkedList<>();
    private LinkedList<Integer> secondChanel = new LinkedList<>();
    private LinkedList<Integer> thirdChanel = new LinkedList<>();
    private LinkedList<Integer> fourthChanel = new LinkedList<>();
    private LinkedList<Integer> fifthChanel = new LinkedList<>();
    private LinkedList<Integer> sixthChanel = new LinkedList<>();
    private LinkedList<Integer> seventhChanel = new LinkedList<>();
    private LinkedList<Integer> eighthChanel = new LinkedList<>();

    public Control() {
        bytesRiesived = new ArrayList<>();
    }

    public void addToList(LinkedList<Byte> addBytes) {
        bytesRiesived.addAll(addBytes);
        //System.out.println("add to list = " + bytesRiesived.toString());
        parseArray(bytesRiesived);

        //parseByTest(bytesRiesived);
        Write.writing("notes3.txt",listIntegers);
        delete();

    }

    private void parseArray(ArrayList<Byte> bytesRiesived) {
        for (byte byteReceived:bytesRiesived) {
            listIntegers.add(fromBinToInt(dataToBin(byteReceived)));
        }
        System.out.println("listIntegers : " + listIntegers);
    }


    public void parseByTest(LinkedList<Byte> bytesRiesived) {
        for(int i = 0; i < bytesRiesived.size(); i++) {
            if(bytesRiesived.get(i) == 92) {
                bytesRiesived.remove(i);
            }
            if(bytesRiesived.get(i) == -59) {
                bytesRiesived.remove(i);
                i-=2;

            }
        }
    }

    private String dataToBin(byte data) {
        String bs = null;
        if(data >= 0) {
            bs = Integer.toBinaryString(data);
            while (bs.length() < 8) {
                bs = "0" + bs;
            }
        }  else {
            bs = Integer.toBinaryString(data);
            bs = bs.substring(24, 32);
        }
        //System.out.println(bs);
        return bs;

    }

    private int fromBinToInt(String binaryString) {
        int decDigit = 0;
        char[] binStringToArray = binaryString.toCharArray(); // Преобразуем строку в массив символов
        //System.out.println(Arrays.toString(binStringToArray));
        if(binStringToArray[0] != '1') {
            int j = 0;
            for(int i = binStringToArray.length-1; i >= 0; i--) {
                decDigit += Character.getNumericValue(binStringToArray[i]) * Math.pow(2, j);
                j++;
            }
        } else {
            for(int i = binStringToArray.length-1; i >= 0; i--) {
                binStringToArray[i] = binStringToArray[i] == '1' ? '0' : '1' ;

            }
            int j = 0;
            for(int i = binStringToArray.length-1; i >= 0; i--) {
                decDigit += Character.getNumericValue(binStringToArray[i]) * Math.pow(2, j);
                j++;

            }
            decDigit = (decDigit+1)*(-1);
        }
        //System.out.println(Arrays.toString(binStringToArray));

       // System.out.println(decDigit);
        return decDigit;
    }



    public void delete() {
        bytesRiesived.removeAll(bytesRiesived);
        listIntegers.clear();
        secondChanel.removeAll(secondChanel);
        thirdChanel.removeAll(thirdChanel);
        fourthChanel.removeAll(fourthChanel);
        fifthChanel.removeAll(fifthChanel);
        sixthChanel.removeAll(sixthChanel);
        seventhChanel.removeAll(seventhChanel);
        eighthChanel.removeAll(eighthChanel);
    }

}