package com.sbt.javaschool.rnd.lesson9;

import com.sbt.javaschool.rnd.lesson9.cache.CacheProxy;
import com.sbt.javaschool.rnd.lesson9.example.Service;
import com.sbt.javaschool.rnd.lesson9.example.ServiceImpl;

import java.util.LinkedList;

public class App {
    public static void main(String[] args) {
        CacheProxy<ServiceImpl> serviceCacheProxy = new CacheProxy<>();
        Service service = serviceCacheProxy.makeCache(new ServiceImpl());


        long startTime = System.nanoTime();
        long res = service.hardWork(25000, 1000);
        long stopTime = System.nanoTime();
        System.out.println(String.format("first call - %d", stopTime - startTime));
        System.out.println(String.format("result - %d", res));

        startTime = System.nanoTime();
        res = service.hardWork(25000, 1000);
        stopTime = System.nanoTime();
        System.out.println(String.format("second call - %d", stopTime - startTime));
        System.out.println(String.format("result - %d", res));
    }
}
