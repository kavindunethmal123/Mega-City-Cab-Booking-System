/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.MagaCityCab.model;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nimeshbuddika
 */
public class VehicleTest {
    
    public VehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVehicleId method, of class Vehicle.
     */
    @Test
    public void testGetVehicleId() {
        System.out.println("getVehicleId");
        Vehicle instance = null;
        int expResult = 0;
        int result = instance.getVehicleId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVehicleId method, of class Vehicle.
     */
    @Test
    public void testSetVehicleId() {
        System.out.println("setVehicleId");
        int vehicleId = 0;
        Vehicle instance = null;
        instance.setVehicleId(vehicleId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class Vehicle.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        Vehicle instance = null;
        String expResult = "";
        String result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class Vehicle.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        String model = "";
        Vehicle instance = null;
        instance.setModel(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlateNumber method, of class Vehicle.
     */
    @Test
    public void testGetPlateNumber() {
        System.out.println("getPlateNumber");
        Vehicle instance = null;
        String expResult = "";
        String result = instance.getPlateNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlateNumber method, of class Vehicle.
     */
    @Test
    public void testSetPlateNumber() {
        System.out.println("setPlateNumber");
        String plateNumber = "";
        Vehicle instance = null;
        instance.setPlateNumber(plateNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCapacity method, of class Vehicle.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        Vehicle instance = null;
        int expResult = 0;
        int result = instance.getCapacity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCapacity method, of class Vehicle.
     */
    @Test
    public void testSetCapacity() {
        System.out.println("setCapacity");
        int capacity = 0;
        Vehicle instance = null;
        instance.setCapacity(capacity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPricePerKm method, of class Vehicle.
     */
    @Test
    public void testGetPricePerKm() {
        System.out.println("getPricePerKm");
        Vehicle instance = null;
        BigDecimal expResult = null;
        BigDecimal result = instance.getPricePerKm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPricePerKm method, of class Vehicle.
     */
    @Test
    public void testSetPricePerKm() {
        System.out.println("setPricePerKm");
        BigDecimal pricePerKm = null;
        Vehicle instance = null;
        instance.setPricePerKm(pricePerKm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
