package com.summerchill.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileFormat {
    public enum FileType { WINDOWS, UNIX, MAC, UNKNOWN }

    private static final char CR = '\r';
    private static final char LF = '\n';

    public static FileType discover(String fileName) throws IOException {

        Reader reader = new BufferedReader(new FileReader(fileName));
        FileType result = discover(reader);
        reader.close();
        return result;
    }

    private static FileType discover(Reader reader) throws IOException {
        int c;
        while ((c = reader.read()) != -1) {
            switch(c) {
                case LF: return FileType.UNIX;
                case CR: {
                    if (reader.read() == LF) return FileType.WINDOWS;
                    return FileType.MAC;
                }
                default: continue;
            }
        }
        return FileType.UNKNOWN;
    }

    public static void main(String[] args) throws IOException {
        String filepath = "/Users/kongxiaohan/project_code/idea/longfor/bi_task/hive/app/hv_e2_app_a22_gxc_device_value_temp.conf";
        FileType discover = discover(filepath);
        //FileType是枚举类型,对应枚举值要使用toString()方法住转换
        System.out.println(discover.toString());
    }
}
