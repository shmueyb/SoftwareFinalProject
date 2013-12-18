package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.session.Person;

/**
 */
public class PersonTest {
    private Person person;

    @Before
    public void init() {
        person = new Person("Xum", "Giang");
    }

    @Test
    public void getFirstNameTest() {
        Assert.assertEquals(person.getFirstName(), "Xum");
    }

    @Test
    public void getLastNameTest() {
        Assert.assertEquals(person.getLastName(), "Giang");
    }

    @Test
    public void setFirstNameTest() {
        person.setFirstName("John");
        Assert.assertEquals(person.getFirstName(), "John");
    }

    @Test
    public void setLastNameTest() {
        person.setLastName("Nguyen");
        Assert.assertEquals(person.getLastName(), "Nguyen");
    }
}
