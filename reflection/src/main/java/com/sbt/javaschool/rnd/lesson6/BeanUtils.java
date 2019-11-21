package com.sbt.javaschool.rnd.lesson6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtils {

    private enum EncapsulateType {GETTER, SETTER};

    public static <T, E extends T> void assign(T to, E from){
        List<Method> fromGetters = getEncapsulateMethods(from, EncapsulateType.GETTER);
        List<Method> toSetters = getEncapsulateMethods(to, EncapsulateType.SETTER);
        for (Method toSetter : toSetters) {
            List<Method> correspondingGetters = fromGetters.stream().filter(fromGetter -> {
                return methodTofieldName(fromGetter, EncapsulateType.GETTER)
                        .equals(methodTofieldName(toSetter, EncapsulateType.SETTER)) &&
                        fromGetter.getReturnType() == toSetter.getParameterTypes()[0];
            }).collect(Collectors.toList());
            if (correspondingGetters.size() != 0) {
                Method correspondingGetter = correspondingGetters.get(0);
                try {
                    toSetter.invoke(to, correspondingGetter.invoke(from));
                } catch (IllegalAccessException e) {
                    System.out.println(String.format("Found non public method"));
                } catch (InvocationTargetException e) {
                    if (e.getCause() != null) {
                        System.out.println(e.getCause());
                    }
                }
            }
        }
    }

    private static <T> List<Method> getEncapsulateMethods(T instance, EncapsulateType type) {
        switch (type) {
            case GETTER:
                return Arrays.stream(instance.getClass().getMethods()).filter(method -> {
                    if (method.getName().matches("^(get|is)\\w+")) {
                        String methodName = methodTofieldName(method, type);
                        return method.getParameterCount() == 0 &&
                                Arrays.stream(instance.getClass().getDeclaredFields()).filter(field -> {
                                    return field.getName().equals(methodName);
                                }).count() == 1;
                    }
                    return false;
                }).collect(Collectors.toList());
            case SETTER:
                return Arrays.stream(instance.getClass().getMethods()).filter(method -> {
                    if (method.getName().matches("^set\\w+")) {
                        String methodName = methodTofieldName(method, type);
                        return method.getParameterCount() == 1 &&
                                Arrays.stream(instance.getClass().getDeclaredFields()).filter(field -> {
                                    return field.getName().equals(methodName);
                                }).count() == 1;
                    }
                    return false;
                }).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException();
        }
    }

    private static String methodTofieldName(Method method, EncapsulateType type) {
        String methodName;
        switch (type) {
            case GETTER:
                methodName = method.getName().replaceFirst("^(get|is)", "");
                methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                break;
            case SETTER:
                methodName = method.getName().replaceFirst("^set", "");
                methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return methodName;
    }
}
