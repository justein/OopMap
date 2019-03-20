package com.lyn.nova.algorithm;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @ClassName GuavaBloomFilter
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/20 0020 下午 4:46
 * @Version 1.0
 */
public class GuavaBloomFilter {

    public static void main(String[] args) {

        long star = System.currentTimeMillis();
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),
                100000000,0.01);
        //初始化数据
        for (int i = 0; i < 100000000; i++) {
            bloomFilter.put(i);
        }

        System.out.println(bloomFilter.mightContain(126754));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));

    }
}
