package com.lyn.nova.algorithm;

import java.util.HashMap;

/**
 * @ClassName BloomFilter
 * @Description 自己实现一个布隆过滤器
 * @Author Lyn
 * @Date 2019/3/18 0018 下午 5:25
 * @Version 1.0
 */
public class BloomFilter {

    /**数据长度*/
    private int arraySize;
    /**用于存放数据的数组*/
    private int[] array;

    public BloomFilter(int  arraySize) {
        this.arraySize  = arraySize;
        array = new int[arraySize];
    }

    public void add(String key) {
        int first = hash_code_1(key);
        int second = hash_code_2(key);
        int third = hash_code_3(key);

        array[first % arraySize] = 1;
        array[second % arraySize] = 1;
        array[third % arraySize] = 1;
    }

    public boolean exist(String key) {
        int first = hash_code_1(key);
        int second = hash_code_2(key);
        int third = hash_code_3(key);

        if (array[first % arraySize] == 0) return false;
        if (array[second % arraySize] == 0) return false;
        if (array[third % arraySize] == 0) return false;

        return true;
    }
    /**HashMap中的hash算法*/
    //    static final int hash(Object key) {
    //        int h;
    //        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    //    }

    private int hash_code_1(String key) {
        int hash = 0;
        int i;
        for (i = 0; i < key.length(); ++i) {
            hash = 33 * hash + key.charAt(i);
        }
        return Math.abs(hash);
    }

    private int hash_code_2(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    private int hash_code_3(String key) {
        int hash, i;
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
        return Math.abs(hash);
    }


}
