package com.company.skyproeducationspring.list;

import com.company.skyproeducationspring.utils.IntList;
import com.company.skyproeducationspring.utils.IntListImpl;
import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@DisplayName("IntList Test")
@Epic("IntList")
public class IntListUnitTest {
    private IntList intList;
    private RandomService randomGenerator;
    private int randomInt;

    @BeforeEach
    void init() {
        intList = new IntListImpl(5);
        randomGenerator = (new Faker()).random();
    }

    @Test
    @Description("Check")
    public void testAddAndGet() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);

            Assertions.assertEquals(randomInt, intList.add(randomInt));

            Assertions.assertEquals(randomInt, intList.get(i));
            Assertions.assertEquals(i, intList.indexOf(randomInt));
            Assertions.assertEquals(i, intList.lastIndexOf(randomInt));
        }
    }

    @Test
    @Description("Check")
    public void testAddAndRemove() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);

            Assertions.assertEquals(randomInt, intList.add(randomInt));
            Assertions.assertEquals(1, intList.size());
            Assertions.assertFalse(intList.isEmpty());

            Assertions.assertEquals(randomInt, intList.removeByValue(randomInt));
            Assertions.assertEquals(0, intList.size());
            Assertions.assertTrue(intList.isEmpty());
        }
    }

    @Test
    @Description("Check")
    public void correctAddAndSet() {
        Assertions.assertEquals(0, intList.size());

        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        Assertions.assertEquals(10, intList.size());

        int oldValue;

        for (int i = 0; i < intList.size(); i++) {
            oldValue = intList.get(i);
            Assertions.assertEquals(i, intList.indexOf(oldValue));

            randomInt = randomGenerator.nextInt(1, 1000);
            Assertions.assertEquals(randomInt, intList.set(i, randomInt));
            Assertions.assertEquals(randomInt, intList.get(i));
            Assertions.assertEquals(i, intList.indexOf(randomInt));

            Assertions.assertNotEquals(oldValue, intList.get(i));
        }

        Assertions.assertEquals(10, intList.size());
    }

    @Test
    @Description("Check")
    public void testIsEmpty() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);

            Assertions.assertTrue(intList.isEmpty());
            Assertions.assertEquals(0, intList.size());

            Assertions.assertEquals(randomInt, intList.add(randomInt));
            Assertions.assertEquals(1, intList.size());
            Assertions.assertFalse(intList.isEmpty());

            Assertions.assertEquals(randomInt, intList.removeByValue(randomInt));
            Assertions.assertTrue(intList.isEmpty());
            Assertions.assertEquals(0, intList.size());
        }
    }

    @Test
    @Description("Check")
    public void testIndexOfAndLastIndexOf() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        randomInt = randomGenerator.nextInt(1, 1000);

        Assertions.assertEquals(randomInt, intList.set(4, randomInt));
        Assertions.assertEquals(randomInt, intList.set(5, randomInt));

        Assertions.assertEquals(4, intList.indexOf(randomInt));
        Assertions.assertEquals(5, intList.lastIndexOf(randomInt));
    }

    @Test
    @Description("Check")
    public void testEquals() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1, 1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        IntListImpl copiedList = new IntListImpl(intList.toArray());

        Assertions.assertTrue(intList.equals(copiedList));
    }

    @Test
    @Description("Check")
    public void testContainsAndSort() {
        int iterationCount = 1_000;
        int[] array = new int[iterationCount];

        for (int i = 0; i < iterationCount; i++) {
            randomInt = randomGenerator.nextInt((-1) * iterationCount, iterationCount);
            array[i] = randomInt;
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        for (int i = 0; i < iterationCount; i++) {
            Assertions.assertTrue(intList.contains(array[i]));
        }


        Arrays.sort(array);
        intList.sort();

        Assertions.assertTrue(intList.equals(new IntListImpl(array)));
    }
}
