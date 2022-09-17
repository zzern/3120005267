package pers.zz;

import pers.zz.utils.SimHashSimilarity;
import pers.zz.utils.TextUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * @Author: zz
 * @Date: 2022/09/17 19:55
 * @Description:
 */
public class main {
    public static void main(String[] args) {
        String text1 = null;
        String text2 = null;
        try {
            //根据文件获得字符串
            text1 = TextUtils.readFileToString(args[0]);
            text2 = TextUtils.readFileToString(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //根据算法获得相似度
        double result = SimHashSimilarity.GetSimilarity(text1, text2);
        //将结果写入文件
        TextUtils.writeResultToFile(args[2],result);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
