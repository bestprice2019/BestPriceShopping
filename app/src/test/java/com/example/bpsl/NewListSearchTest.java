package com.example.bpsl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NewListSearchTest {

    NewListSearch NLS = new NewListSearch();

    @Test
    public void testOnSearchMachine() {
        Item firstItem = new Item("PNS","Milk","3.3");
        Item secondItem = new Item("PNS","Bread","4.1");
        Item thirdItem = new Item("PNS","Sugar","2");
        Item fourthItem = new Item("PNS","Beer","7");
        List<Item> input = new ArrayList<>();
        List<Item> expect = new ArrayList<>();
        input.add(firstItem);
        input.add(secondItem);
        input.add(thirdItem);
        input.add(fourthItem);
        expect.add(secondItem);
        expect.add(fourthItem);
        List<Item> output = NLS.searchMachine("B",input);
        assertEquals(expect,output);

    }
}