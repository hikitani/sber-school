package com.sbt.javaschool.rnd.lesson6;

import java.lang.reflect.*;
import java.time.Month;
import java.util.HashMap;

public class App
{
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException {
        Class<HashMap> hashMapClass = HashMap.class;
        System.out.println("Модификаторы:\n");


        String packageName = hashMapClass.getPackage().getName();
        System.out.println(String.format("Имя пакета: %s", packageName));

        System.out.println("Иерархия:");
        System.out.println(getSuperClasses(hashMapClass));

        System.out.println("Иерархия интерфейсов:");
        System.out.println(getInterfaces(hashMapClass));

        Runtime<Number> runtime = new Runtime();
        Class<?> runtimeClass = runtime.getClass();
        System.out.println(runtimeClass.getName());

        Field integers = runtimeClass.getDeclaredField("integers");
        System.out.println(String.format("generic параметр поля: %s", ((ParameterizedType)integers.getGenericType()).getActualTypeArguments()[0].getTypeName()));

        Method numbers = runtimeClass.getMethod("numbers");
        System.out.println(String.format("generic параметр метода numbers", ((ParameterizedType)numbers.getGenericReturnType()).getActualTypeArguments()[0].getTypeName()));

        Class<Class> classClass = Class.class;
        System.out.println("\nКласс Class:");
        for (Method method : classClass.getMethods()) {
            if (method.getName().matches("get\\w+")) {
                String retType = method.getName().substring(3);
                if (retType == method.getReturnType().getSimpleName()) {
                    System.out.println(method.getName());
                }
            }
        }

        Class<Month> monthClass = Month.class;
        System.out.println("Класс Month:");
        for (Field declaredField : monthClass.getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (Modifier.isStatic(declaredField.getModifiers()) &&
//                    declaredField.getType().getSimpleName() == "String" &&
                    declaredField.getName() == declaredField.get(null).toString()) {
                System.out.println(declaredField.getName());
            }
        }

        Calculator calculator = ProxyUtils.makeCached(new CalculatorImpl());
        System.out.println(calculator.calculate(4));
        System.out.println(calculator.calculate(4));
    }

    public static <T> String getSuperClasses(Class<T> meta) {
        StringBuilder res = new StringBuilder();
        Class superClass = meta.getSuperclass();
        do {
            res.append(superClass.getSimpleName() + "\n");
            superClass = superClass.getSuperclass();
        } while (superClass != null);
        return res.toString();
    }

    public static <T> String getInterfaces(Class<T> meta) {
        StringBuilder res = new StringBuilder();
        Class superClass = meta.getSuperclass();
        res.append(meta.getSimpleName() + ":\n");
        for (Class interf : meta.getInterfaces()) {
            res.append(interf.getSimpleName() + ", ");
        }
        res.append("\n");
        do {
            res.append(superClass.getSimpleName() + ":\n");
            for (Class interf : superClass.getInterfaces()) {
                res.append(interf.getSimpleName() + ", ");
            }
            superClass = superClass.getSuperclass();
        } while (superClass != null);
        return res.toString();
    }


}
