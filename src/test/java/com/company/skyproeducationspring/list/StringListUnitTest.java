package com.company.skyproeducationspring.list;

import com.company.skyproeducationspring.utils.StringList;
import com.company.skyproeducationspring.utils.StringListImpl;
import com.github.javafaker.Faker;
import com.github.javafaker.FunnyName;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StringList Test")
@Epic("StringList")
public class StringListUnitTest {
    private StringList stringList;
    private FunnyName nameGenerator;
    private String funnyName;

    @BeforeEach
    void init() {
        stringList = new StringListImpl(5);
        nameGenerator = (new Faker()).funnyName();
    }

    @Test
    @Description("Check")
    public void testAddAndGet() {
        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();

            Assertions.assertEquals(funnyName, stringList.add(funnyName));

            Assertions.assertEquals(funnyName, stringList.get(i));
            Assertions.assertEquals(i, stringList.indexOf(funnyName));
            Assertions.assertEquals(i, stringList.lastIndexOf(funnyName));
        }
    }

    @Test
    @Description("Check")
    public void testAddAndRemove() {
        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();

            Assertions.assertEquals(funnyName, stringList.add(funnyName));
            Assertions.assertEquals(1, stringList.size());
            Assertions.assertFalse(stringList.isEmpty());

            Assertions.assertEquals(funnyName, stringList.remove(funnyName));
            Assertions.assertEquals(0, stringList.size());
            Assertions.assertTrue(stringList.isEmpty());
        }
    }

    @Test
    @Description("Check")
    public void correctAddAndSet() {
        Assertions.assertEquals(0, stringList.size());

        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();
            Assertions.assertEquals(funnyName, stringList.add(funnyName));
        }

        Assertions.assertEquals(10, stringList.size());

        String oldFunnyName;

        for (int i = 0; i < stringList.size(); i++) {
            oldFunnyName = stringList.get(i);
            Assertions.assertEquals(i, stringList.indexOf(oldFunnyName));

            funnyName = nameGenerator.name();
            Assertions.assertEquals(funnyName, stringList.set(i, funnyName));
            Assertions.assertEquals(funnyName, stringList.get(i));
            Assertions.assertEquals(i, stringList.indexOf(funnyName));

            Assertions.assertNotEquals(oldFunnyName, stringList.get(i));
        }

        Assertions.assertEquals(10, stringList.size());
    }

    @Test
    @Description("Check")
    public void testIsEmpty() {
        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();

            Assertions.assertTrue(stringList.isEmpty());
            Assertions.assertEquals(0, stringList.size());

            Assertions.assertEquals(funnyName, stringList.add(funnyName));
            Assertions.assertEquals(1, stringList.size());
            Assertions.assertFalse(stringList.isEmpty());

            Assertions.assertEquals(funnyName, stringList.remove(funnyName));
            Assertions.assertTrue(stringList.isEmpty());
            Assertions.assertEquals(0, stringList.size());
        }
    }

    @Test
    @Description("Check")
    public void testIndexOfAndLastIndexOf() {
        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();
            Assertions.assertEquals(funnyName, stringList.add(funnyName));
        }

        Assertions.assertEquals("test index of", stringList.set(4, "test string"));
        Assertions.assertEquals("test index of", stringList.set(5, "test string"));

        Assertions.assertEquals(4, stringList.indexOf("test string"));
        Assertions.assertEquals(5, stringList.lastIndexOf("test string"));
    }

    @Test
    @Description("Check")
    public void testEquals() {
        for (int i = 0; i < 10; i++) {
            funnyName = nameGenerator.name();
            Assertions.assertEquals(funnyName, stringList.add(funnyName));
        }

        StringListImpl copiedList = new StringListImpl(stringList.toArray());

        Assertions.assertTrue(stringList.equals(copiedList));
    }
}
