package com.sbt.javaschool.rnd.lesson6;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class CalculateHandler implements InvocationHandler {
    private final Object delegate;
    private Object[] lastParameters;
    private Object result;

    public CalculateHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean perfAnnotationExist = false;
        // получаем метод класса, чтобы увидеть аннотацию
        String name = method.getName();
        Method methodClass = (Method)Arrays.stream(delegate.getClass().getMethods()).filter((Method m) -> {
            return m.getName() == method.getName();
        }).toArray()[0];
        for (Annotation annotation : methodClass.getAnnotations()) {
            if (annotation.annotationType() == PerfCalc.class) {
                perfAnnotationExist = true;
                break;
            }
        }
        if (perfAnnotationExist) {
            boolean paramsEquals = true;
            if (lastParameters != null) {
                if (args.length == lastParameters.length) {
                    for (int i = 0; i < args.length; i++) {
                        if (!args[i].equals(lastParameters[i])) {
                            paramsEquals = false;
                            break;
                        }
                    }
                } else {
                    paramsEquals = false;
                }
                if (paramsEquals) {
                    System.out.println("Уже расчитано!");
                    return result;
                }
            }
            result = methodClass.invoke(delegate, args);
            lastParameters = args;
            return result;
        }
        Object res = methodClass.invoke(delegate, args);
        return res;
    }
}
