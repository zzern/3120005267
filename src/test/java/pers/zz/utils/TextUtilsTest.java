package pers.zz.utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @Author: zz
 * @Date: 2022/09/17 22:03
 * @Description:
 */
public class TextUtilsTest {

    /**
    *
    * @Date: 2022/9/18 0:52
    * @Description: 测试读取文件转化为字符串
    *
    */
    @Test
    public void readFileToString() throws IOException {
        String s = TextUtils.readFileToString("C:\\Users\\yyoukaii\\Desktop\\test.txt");
        System.out.println(s);
    }
    /**
    *
    * @Date: 2022/9/18 0:53
    * @Description: 测试将相似度结果写入文件
    *
    */
    @Test
    public void writeResultToFile() {
        TextUtils.writeResultToFile("C:\\Users\\yyoukaii\\Desktop\\test.txt",666);
    }
}