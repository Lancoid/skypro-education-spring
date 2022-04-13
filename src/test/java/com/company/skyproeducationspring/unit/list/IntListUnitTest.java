package com.company.skyproeducationspring.unit.list;

import com.company.skyproeducationspring.utils.IntList;
import com.company.skyproeducationspring.utils.IntListImpl;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

@DisplayName("IntList Test")
@Epic("IntList")
public class IntListUnitTest {
    private IntList intList;
    private Random randomGenerator;
    private int randomInt;

    @BeforeEach
    void init() {
        intList = new IntListImpl(5);
        randomGenerator = new Random();
    }

    @Test
    @Description("Check")
    public void testAddAndGet() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1000);

            Assertions.assertEquals(randomInt, intList.add(randomInt));

            Assertions.assertEquals(randomInt, intList.get(i));
            Assertions.assertEquals(i, intList.indexOf(randomInt));
            Assertions.assertEquals(i, intList.lastIndexOf(randomInt));
        }
    }

    @Test
    @Description("Check")
    public void testAddByIndex() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1000);

            Assertions.assertEquals(randomInt, intList.add(randomInt));
            Assertions.assertEquals(randomInt, intList.get(i));
        }

        Assertions.assertEquals(10, intList.size());

        for (int i = 0; i < 10; i++) {
            int oldValue = intList.get(i);

            randomInt = randomGenerator.nextInt(1000);

            Assertions.assertEquals(randomInt, intList.add(i, randomInt));
            Assertions.assertEquals(randomInt, intList.get(i));

            Assertions.assertEquals(oldValue, intList.get(i + 1));

            Assertions.assertEquals(i + 11, intList.size());
        }
    }

    @Test
    @Description("Check")
    public void testAddAndRemove() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1000);

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
            randomInt = randomGenerator.nextInt(1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        Assertions.assertEquals(10, intList.size());

        int oldValue;

        for (int i = 0; i < intList.size(); i++) {
            oldValue = intList.get(i);
            Assertions.assertEquals(i, intList.indexOf(oldValue));

            randomInt = randomGenerator.nextInt(1000);
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
            randomInt = randomGenerator.nextInt(1000);

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
            randomInt = randomGenerator.nextInt(1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        randomInt = randomGenerator.nextInt(1000);

        Assertions.assertEquals(randomInt, intList.set(4, randomInt));
        Assertions.assertEquals(randomInt, intList.set(5, randomInt));

        Assertions.assertEquals(4, intList.indexOf(randomInt));
        Assertions.assertEquals(5, intList.lastIndexOf(randomInt));
    }

    @Test
    @Description("Check")
    public void testEquals() {
        for (int i = 0; i < 10; i++) {
            randomInt = randomGenerator.nextInt(1000);
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        IntListImpl copiedList = new IntListImpl(intList.toArray());

        Assertions.assertTrue(intList.equals(copiedList));
    }

    @Test
    @Description("Check")
    public void testContainsAndSort() {
        int iterationCount = 100;
        int[] array = new int[iterationCount];

        for (int i = 0; i < iterationCount; i++) {
            randomInt = randomGenerator.nextInt(iterationCount) + iterationCount;
            array[i] = randomInt;
            Assertions.assertEquals(randomInt, intList.add(randomInt));
        }

        for (int i = 0; i < iterationCount; i++) {
            Assertions.assertTrue(intList.contains(array[i]));
        }

        Arrays.sort(array);
        intList.sort();

        int startIndex = intList.indexOf(array[0]);

        for (int i = 0; i < array.length; i++) {
            Assertions.assertEquals(array[i], intList.get(startIndex + i));
        }
    }
}
