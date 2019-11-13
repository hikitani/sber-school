package com.sbt.javaschool.rnd.lesson4;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MyCountMapTest
{
    private MyCountMap<Object> myCountMap;

    @Before
    public void setUpMap() {
        myCountMap = new MyCountMap<>();
    }

    @Test
    public void addTest() {
        Object obj = new Object();
        myCountMap.add(obj);
        assertTrue(myCountMap.getCount(obj) == 1);
        myCountMap.add(obj);
        assertTrue(myCountMap.getCount(obj) == 2);
        myCountMap.add(obj);
        assertTrue(myCountMap.getCount(obj) == 3);
    }

    @Test
    public void removeObjectTest() {
        Object obj = new Object();
        for (int i = 0; i < 10; i++) {
            myCountMap.add(obj);
        }
        assertTrue(10 == myCountMap.remove(obj));
    }

    @Test
    public void sizeMapTest() {
        Object[] objs = new Object[10];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new Object();
            myCountMap.add(objs[i]);
        }
        assertTrue(10 == myCountMap.size());
        myCountMap.remove(objs[0]);
        assertTrue(9 == myCountMap.size());
    }

    @Test
    public void toMapTest() {
        Object[] objs = new Object[10];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new Object();
            myCountMap.add(objs[i]);
        }
        Map<Object, Integer> map = myCountMap.toMap();
        for (int i = 0; i < objs.length; i++) {
            assertTrue(map.containsKey(objs[i]) && 1 == map.get(objs[i]));
        }
        assertTrue(10 == map.size());
    }

    @Test
    public void addAllTest() {
        MyCountMap<Object> source = new MyCountMap<>();
        Object[] objs = new Object[3];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new Object();
            myCountMap.add(objs[i]);
            source.add(objs[i]);
        }

        myCountMap.addAll(source);
        for (int i = 0; i < objs.length; i++) {
            assertTrue(2 == myCountMap.getCount(objs[i]));
        }

        Object newObj = new Object();
        source.add(newObj);
        myCountMap.addAll(source);
        for (int i = 0; i < objs.length; i++) {
            assertTrue(3 == myCountMap.getCount(objs[i]));
        }
        assertTrue(1 == myCountMap.getCount(newObj));
    }

    @Test
    public void toMapDestTest() {
        Map<Object, Integer> map = new HashMap<>();
        Object[] objs = new Object[3];
        for (int i = 0; i < objs.length; i++) {
            objs[i] = new Object();
            myCountMap.add(objs[i]);
        }

        myCountMap.toMap(map);
        for (int i = 0; i < objs.length; i++) {
            assertTrue(map.containsKey(objs[i]) && 1 == map.get(objs[i]));
        }
    }

}
