package com.leran.serial.ms;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtUtils {
    static SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat ft2 = new SimpleDateFormat("yyyy/MM/dd");

    public static void main(String[] args) {
        List<String> stringList = readTxt("D:\\ms\\csv.txt");
        List<String> outString = new ArrayList<>();
        for (String str: stringList) {
            if (!str.contains(",") && !str.contains("\"")) {
                outString.add(str);
            }else{
                List<String> split = Arrays.asList(str.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)",-1));
                String format = format(split);
                outString.add(format);
            }

        }
        writeOut(outString);
    }

    private static String format(List<String> list) {
        int size = list.size();
        int a1 = Integer.valueOf(list.get(0));
        String a2 = "'"+list.get(1)+"'";
        String a3 = "";

        for(int i = 2;i<size-2 ;i++){
            a3 = a3+list.get(i)+",";
        }
        a3 = a3.substring(0, a3.lastIndexOf(","))
                .replace("\"\"","_")
                .replace("\"","'")
                .replace("_","\"");

        Float a4 = Float.valueOf(list.get(size-2));

        String a5 = "";
        try {
            a5 = ft2.format(ft1.parse(list.get(size-1)));

        } catch (ParseException e) {
            a5 = list.get(size-1).replace("-","/");
        }
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append(a1).append("\\t")
                .append(a2).append("\\t")
                .append(a3).append("\\t")
                .append(a4).append("\\t")
                .append(a5).append("\\t").toString();
    }

    private static void writeOut(List<String> array) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter("D:\\ms\\output.txt"));
            //遍历集合，得到每一个字符串数据
            for (String s : array) {
                bw.write(s);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private static List<String> readTxt(String filePath) {
        File file = new File(filePath);
        try{
            BufferedReader textFile = new BufferedReader(new FileReader(file));
            String lineDta = "";

            List<String> stringList = new ArrayList<>();
            while ((lineDta = textFile.readLine()) != null){
                stringList.add(lineDta);
            }
            textFile.close();
            return stringList;
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        return null;
    }
}
