package pers.zz.utils;

import java.io.*;

/**
 * @Author: zz
 * @Date: 2022/09/17 20:28
 * @Description:
 */
public class TextUtils {
    /**
    * 
    * @Date: 2022/9/17 20:30
    * @Description: 根据传入的路径将文件内容转为字符串
    * 
    */
    public static String readFileToString(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            // 拼接换行符
            sb.append(temp + "\n");
        }
        br.close();
        return sb.toString();
    }
    /**
    *
    * @Date: 2022/9/17 20:40
    * @Description: 根据路径名将相似度写入文件
    *
    */
    public static void writeResultToFile(String path,double similarity){
        try {
            File file;
            file = new File(path);
            if (!file.exists()){
                file.createNewFile();
                file = new File(path);
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(String.format("%.2f",similarity));
            writer.flush();
            writer.newLine();
            //关闭资源
            writer.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
