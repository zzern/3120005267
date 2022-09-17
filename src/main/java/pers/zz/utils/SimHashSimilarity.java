package pers.zz.utils;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zz
 * @Date: 2022/09/17 21:05
 * @Description:
 */
public class SimHashSimilarity {
	//��ȡhashֵ
	public static BigInteger simHash(String str) {
		int[] hashValue = new int[64];
		
		List<Term> termList = StandardTokenizer.segment(str);
		Map<String, Integer> natureWeight = new HashMap<String, Integer>();
		Map<String, String> stopNatures = new HashMap<String, String>();//ͣ�õĴ��� ��һЩ������֮���;
	
		//���ݴ�Ƶ����Ȩ��
		for(Term term: termList) {
			String word = term.word;
			String nature = term.nature.toString();
			if(nature.equals("w")) {
				stopNatures.put(word, "");
				continue;
			} else {
				if(!natureWeight.containsKey(word))
					natureWeight.put(word, 1);
				else {
					int preWeight = natureWeight.get(word);
					natureWeight.put(word, preWeight);
				}
			}
		}
		
        int overCount = 5; //�趨��Ƶ�ʻ�Ľ��� ;
        
        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        for(Term term: termList) {
            String word = term.word; //�ִ��ַ���
            String nature = term.nature.toString(); // �ִ�����
            //  ���˳�Ƶ��
            if (wordCount.containsKey(word)) {
                int count = wordCount.get(word);
                if (count > overCount) {
                    continue;
                }
                wordCount.put(word, count + 1);
            } else {
                wordCount.put(word, 1);
            }

            // ����ͣ�ô���
            if (stopNatures.containsKey(nature)) {
                continue;
            }

            //��ÿһ���ִʽ���hash,hash��64bit�Ĵ�����.
            BigInteger hashNumber = hash(word);
            
            for (int i = 0; i < 64; i++) {
                BigInteger bitMask = new BigInteger("1").shiftLeft(i);
              //���Ȩ��
                int weight = 1;  
                if (natureWeight.containsKey(nature)) {
                    weight = natureWeight.get(nature);
                }
                //��1�ӣ���0��
                if (hashNumber.and(bitMask).signum() != 0) {
                    // �����Ǽ��������ĵ�������������������
                    hashValue[i] += weight;
                } else {
                    hashValue[i] -= weight;
                }
            }
        }
        
        BigInteger fingerprint = new BigInteger("0");
        for (int i = 0; i < 64; i++) {
            if (hashValue[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
            }
        }
        return fingerprint;
		
	}
	
	//simHash,���ִ�hash
	public static BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
        	//��source�ĳ��ȹ���hash�㷨��ʧЧ������Ҫ�Թ��̵Ĵʽ��в���
	        while (source.length() < 3) {
	            source = source + source.charAt(0);
	        }
	        char[] sourceArray = source.toCharArray();
	        BigInteger ans = BigInteger.valueOf(((long) sourceArray[0]) << 7);
	        BigInteger MAX_NUM = new BigInteger("1000003");
	        
	        BigInteger mask = new BigInteger("2").pow(64).subtract(new BigInteger("1"));
	       
	        for (char item: sourceArray) {
	            BigInteger temp = BigInteger.valueOf((long) item);
	            ans = ans.multiply(MAX_NUM).xor(temp).and(mask);
	        }
	        ans = ans.xor(new BigInteger(String.valueOf(source.length())));
	        if (ans.equals(new BigInteger("-1"))) {
	            ans = new BigInteger("-2");
	        }
	       
	        return ans;
	    }
    }
	
	
	//simHash,��������������֮��ľ���
	public static int hammingDistance(String str, String otherStr) {
		BigInteger simHashNum = simHash(str);
		BigInteger another = simHash(otherStr);
		
        BigInteger MAX_NUM = new BigInteger("1").shiftLeft(64).subtract(new BigInteger("1"));
        BigInteger x = simHashNum.xor(another).and(MAX_NUM);
      
        int ans = 0;
        while (x.signum() != 0) {
            ans += 1;
            x = x.and(x.subtract(new BigInteger("1"))); //x &= x - 1
        }
   
        return ans;
    }
    //��ȡ���ƶ�
	public static double GetSimilarity(String str, String otherStr) {
		double i = (double) hammingDistance(str, otherStr);
        return 1 - i / 64;
	}
}
