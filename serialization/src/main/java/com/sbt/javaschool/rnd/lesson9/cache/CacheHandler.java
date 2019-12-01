package com.sbt.javaschool.rnd.lesson9.cache;

import com.sbt.javaschool.rnd.lesson9.cache.exceptions.InvalidSignatureException;

import javax.lang.model.type.NullType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

public class CacheHandler implements InvocationHandler {

    private Object instance;
    private CacheProxy cacheProxy;

    public CacheHandler(Object instance, CacheProxy cacheProxy) {
        this.instance = instance;
        this.cacheProxy = cacheProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object result = null;
        Method methodClass = (Method)Arrays.stream(instance.getClass().getMethods()).filter((Method m) -> {
            return m.getName() == method.getName();
        }).toArray()[0];
        if (isCacheable(methodClass)) {
            Cache cache = methodClass.getAnnotation(Cache.class);
            try {
                if (!isValidSignature(cache, methodClass)) {
                    throw new InvalidSignatureException(String.format("Сигнатура, описанная в аннотации не соответствует той, " +
                            "которая описана в методе. Если не хотите учитывать определенный параметр, пометьте его в аннотации " +
                            "как NullType"), cache.signature(), methodClass.getParameterTypes());
                }
                ResultMethod resultMethod = tryGetResultFromStorage(cache, methodClass);
                if (resultMethod == null) {
                    result = methodClass.invoke(instance, args);
                    saveResultToStorage(cache, methodClass, result);
                } else {
                    result = resultMethod.getValue();
                }
            } catch (InvalidSignatureException | InvalidParameterException e) {
                System.err.println(e.getMessage());
            }
            return result;
        }
        result = methodClass.invoke(instance, args);
        return result;
    }

    private void saveResultToStorage(Cache cache, Method method, Object result) {
        switch (cache.storage()) {
            case JVM_MEMORY:
                String key = this.getKey(cache, method);
                ResultMethod resultMethod = new ResultMethod(result, method.getReturnType());
                Map.Entry<Class[], ResultMethod> entryResult = new HashMap.SimpleEntry<Class[], ResultMethod>(method.getParameterTypes(), resultMethod);
                Map<String, List<Map.Entry<Class[], ResultMethod>>> resultsMapMethod = cacheProxy.getResultsMethod();
                if (resultsMapMethod.get(key) != null) {
                    resultsMapMethod.get(key).add(entryResult);
                } else {
                    List<Map.Entry<Class[], ResultMethod>> entryList = new LinkedList<>();
                    entryList.add(entryResult);
                    resultsMapMethod.put(key, entryList);
                }
                break;
            case LOCAL_FILE:

                break;
            default:
                throw new InvalidParameterException(String.format("Неверное значение типа хранения в аннотации %s", Cache.class.getSimpleName()));
        }
    }

    private ResultMethod tryGetResultFromStorage(Cache cache, Method method) {
        ResultMethod resultMethod = null;
        switch (cache.storage()) {
            case JVM_MEMORY:
                Map<String, List<Map.Entry<Class[], ResultMethod>>> resultsMapMethod = cacheProxy.getResultsMethod();
                String key = this.getKey(cache, method);
                // достать результат, чья сигнатура совпадает с сигнатурой, описанной в аннотации
                List<Map.Entry<Class[], ResultMethod>> entriesResult = resultsMapMethod.get(key);
                if (entriesResult != null) {
                    entriesResult.stream().filter(entry -> {
                        if (cache.signature().length == 0 && entry.getKey().length == method.getParameterCount()) {
                            return true;
                        }
                        for (int i = 0; i < cache.signature().length; i++) {
                            if (cache.signature()[i] != entry.getKey()[i] && cache.signature()[i] != NullType.class) {
                                return false;
                            }
                        }
                        return true;
                    }).collect(Collectors.toList());
                    resultMethod = entriesResult.get(0).getValue();
                }
                break;
            case LOCAL_FILE:

                break;
            default:
                throw new InvalidParameterException(String.format("Неверное значение типа хранения в аннотации %s", Cache.class.getSimpleName()));
        }
        return resultMethod;
    }

    private boolean isValidSignature(Cache cache, Method method) {
        if (cache.signature().length == 0) {
            return true;
        }
        if (cache.signature().length == method.getParameterTypes().length) {
            for (int i = 0; i < cache.signature().length; i++) {
                if (cache.signature()[i] != method.getParameterTypes()[i] && cache.signature()[i] != NullType.class) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isCacheable(Method method) {
        if (method.getAnnotation(Cache.class) == null) {
            return false;
        } else {
            return true;
        }
    }

    private String getKey(Cache cache, Method method) {
        String key;
        if (cache.key().equals("")) {
            key = method.getName();
        } else {
            key = cache.key();
        }
        return key;
    }
}
