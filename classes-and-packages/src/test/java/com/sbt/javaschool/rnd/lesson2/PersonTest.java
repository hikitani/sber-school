package com.sbt.javaschool.rnd.lesson2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class PersonTest extends Assert {

    Person male1;
    Person male2;

    Person female1;
    Person female2;

    @Before
    public void setUpPersons() {
        male1 = new Person("P1", "P1", LocalDate.of(1990, Month.APRIL, 20), true);
        male2 = new Person("P2", "P2", LocalDate.of(1990, Month.APRIL, 20), true);

        female1 = new Person("P3", "P3", LocalDate.of(1990, Month.APRIL, 20), false);
        female2 = new Person("P4", "P3", LocalDate.of(1990, Month.APRIL, 20), false);
    }

    @Test
    public void marrySameGenders() {
        assertFalse(male1.marry(male2));
    }

    @Test
    public void marryDifferentGenders() {
        assertTrue(male1.marry(female1));
        assertTrue(male1.getSpouse() == female1 && female1.getSpouse() == male1);
    }

    @Test
    public void marryGendersHavePair() {
        male1.marry(female1);
        male2.marry(female2);

        assertFalse(male1.marry(female2));
        assertTrue(male2.getSpouse() == null && female1.getSpouse() == null);
    }

    @Test
    public void divorceMarriedGenders() {
        male1.marry(female1);
        assertTrue(male1.divorce());
        assertTrue(male1.getSpouse() == null && female1.getSpouse() == null);
    }

    @Test
    public void divorceUnmarriedGenders() {
        assertFalse(male1.divorce());
    }

}
