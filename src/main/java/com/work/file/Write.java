package com.work.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Write {

    public static void writing(String path, List<Integer> list){
        try(FileWriter writer = new FileWriter(path, true))
        {
            for(int i: list){
                writer.write(Integer.toString(i));
                writer.append('\n');
            }
            writer.append('\n');
            writer.append('\n');
            writer.append("end");
            writer.append('\n');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public static void clear(String path){
        try(FileWriter writer = new FileWriter(path, false))
        {
            writer.write("");
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
