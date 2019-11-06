package com.sbt.javaschool.rnd.lesson2;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private static long ID = 0L;

    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Person spouse;
    private boolean man;

    public Person() {
        setId();
    }

    public Person(String firstName, String lastName, LocalDate birthDate, boolean man) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.man = man;
    }

    public long getId() {
        return id;
    }

    private void setId() {
        this.id = Person.ID;
        Person.ID++;
    }

    @Override
    public String toString() {
        return String.format("Person - %s %s\n\tage - %d year\n\tman - %s\n",
                this.getFirstName(),
                this.getLastName(),
                this.getAge(),
                this.man);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Person getSpouse() {
        return spouse;
    }

    /**
     * This method checks gender of the persons. If genders are not equal - join to married.
     * If one of them has spouse, executed divorce (sets spouses to null).
     * @param person - new husband/wife for this person.
     * @return returns true if both gender are different and they are not husband and wife.
     */
    public boolean marry(Person person) {
        boolean isDifferentGenders = person.man != this.man;
        boolean bothNotHavePair = person.spouse == null && this.spouse == null;
        if (isDifferentGenders) {
            person.divorce();
            this.divorce();
            person.spouse = this;
            this.spouse = person;
        }
        return isDifferentGenders && bothNotHavePair;
    }

    /**
     * Set spouse to null, if he is not null.
     * @return true - if person status has been changed.
     */
    public boolean divorce() {
        if (this.spouse == null) {
            return false;
        }
        this.spouse.spouse = null;
        this.spouse = null;
        return true;
    }
}
