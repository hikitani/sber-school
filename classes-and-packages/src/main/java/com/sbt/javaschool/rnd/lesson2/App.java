package com.sbt.javaschool.rnd.lesson2;

import java.time.LocalDate;
import java.time.Month;

public class App
{
    public static void main( String[] args )
    {
        Person[] persons = {
                new Person("Иван", "Иванов", LocalDate.of(1992, Month.JUNE, 22), true),
                new Person("Сергей", "Лазарев", LocalDate.of(1998, Month.JUNE, 16), true)
        };

        for (Person person : persons) {
            System.out.println(person.toString());
        }
    }
}
