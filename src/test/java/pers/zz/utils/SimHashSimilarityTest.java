package pers.zz.utils;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * @Author: zz
 * @Date: 2022/09/17 22:12
 * @Description:
 */
public class SimHashSimilarityTest {

    @Test
    public void simHash() {
        BigInteger aaa = SimHashSimilarity.simHash("aaaaaaa");
        System.out.println(aaa);
    }

    @Test
    public void hash() {
        BigInteger aaa = SimHashSimilarity.hash("aaaaaaa");
        System.out.println(aaa);
    }

    @Test
    public void hammingDistance() {
        int distance = SimHashSimilarity.hammingDistance("aaaaaa", "abbaaa");
        System.out.println(distance);
    }

    @Test
    public void getSimilarity() {
        double v = SimHashSimilarity.GetSimilarity("aaaaaaaaaaaaa", "abbaaaaaaaaa");
        System.out.println(v);
    }
}