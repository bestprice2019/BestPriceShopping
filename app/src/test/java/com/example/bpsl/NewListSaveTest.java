package com.example.bpsl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NewListSaveTest {

    NewListSave NLSave = new NewListSave();

    @Test
    public void calculateTotal() {
        Item firstItem = new Item("PNS","Milk","3.3");
        Item secondItem = new Item("PNS","Bread","4.1");
        Item thirdItem = new Item("PNS","Sugar","2");
        List<Item> input = new ArrayList<>();
        input.add(firstItem);
        input.add(secondItem);
        input.add(thirdItem);
        double output = NLSave.calculateTotal(input);
        double expect = 9.4;
        double delta = 0.1;

        assertEquals(expect,output,delta);
    }
}