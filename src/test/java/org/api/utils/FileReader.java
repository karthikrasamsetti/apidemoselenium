package org.api.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileReader {

    public static String getData(String filePath) {
        String data = "";
        try {
            System.out.println(filePath);
            data = FileUtils.readFileToString(new File(filePath), "UTF-8");
            System.out.println(data);
        } catch (IOException e) {
        }
        return data;
    }
}