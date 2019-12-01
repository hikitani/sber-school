package com.sbt.javaschool.rnd.lesson9.example;

import com.sbt.javaschool.rnd.lesson9.cache.Cache;
import com.sbt.javaschool.rnd.lesson9.cache.StorageType;

public class ServiceImpl implements Service{

    @Override
    @Cache(signature = {int.class, int.class})
    public long hardWork(int count, int iter) {
        long res = 0L;
        for (int i = 0; i < count; i++) {
            for (int j = i - count; j < i + count; j++) {
                res += i + j * i;
            }
        }
        return res;
    }
}
