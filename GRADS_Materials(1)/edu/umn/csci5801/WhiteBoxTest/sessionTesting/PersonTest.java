package edu.umn.csci5801.WhiteBoxTest.sessionTesting;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.umn.csci5801.session.Person;

/**
 */
public class PersonTest {
    private static Person person;

    @BeforeClass
    public static void init() {
        person = new Person("Xum", "Giang");
    }

    @Test
    public void gettersTest() {
        Assert.assertEquals(person.getFirstName(), "Xum");
        Assert.assertEquals(person.getLastName(), "Giang");
    }

    @Test
    public void settersTest() {
        person.setFirstName("John");
        person.setLastName("Nguyen");
        Assert.assertEquals(person.getFirstName(), "John");
        Assert.assertEquals(person.getLastName(), "Nguyen");
    }
}
