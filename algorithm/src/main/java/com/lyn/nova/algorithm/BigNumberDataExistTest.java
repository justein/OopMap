package com.lyn.nova.algorithm;

/**
 * @ClassName BigNumberDataExistTest
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/18 0018 下午 5:47
 * @Version 1.0
 */
public class BigNumberDataExistTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        BloomFilter bloomFilter = new BloomFilter(100000000);

        for (int i = 0; i < 100000000; i++) {
            bloomFilter.add(i + "");
        }

        System.out.println(bloomFilter.exist(23 + ""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }
}
