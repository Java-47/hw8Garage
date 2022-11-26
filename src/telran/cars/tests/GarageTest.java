package telran.cars.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.cars.dao.Garage;
import telran.cars.dao.GarageImpl;
import telran.cars.model.Car;

class GarageTest {
	Garage garage;
	Car[] cars;

	@BeforeEach
	void setUp() throws Exception {
		garage = new GarageImpl(6);

		cars = new Car[5];
		cars[0] = new Car("123", "3", "Mazda", 1.6, "red");
		cars[1] = new Car("1234", "Camry", "Toyota", 2.0, "blue");
		cars[2] = new Car("1235", "Vitara", "Suzuki", 1.6, "black");
		cars[3] = new Car("1236", "3", "Mazda", 2.0, "white");
		cars[4] = new Car("1237", "4Runner", "Toyota", 4.0, "red");

		for (int i = 0; i < cars.length; i++) {
			garage.addCar(cars[i]);
		}

	}

	@Test
	void testAddCar() {
		assertFalse(garage.addCar(cars[1]));
		Car newCar = new Car("1238", "308", "Peugeot", 2.0, "black");
		assertTrue(garage.addCar(newCar));
		assertEquals(newCar, garage.findCarByRegNumber("1238"));
		assertFalse(garage.addCar(new Car("1239", "308", "Peugeot", 2.0, "black")));
	}

	@Test
	void testRemoveCar() {
		assertNull(garage.removeCar("1"));
		assertEquals(cars[0], garage.removeCar("123"));
	}

	@Test
	void findCarByRegNumber() {
		assertNull(garage.findCarByRegNumber("1"));
		Car expected = garage.findCarByRegNumber("1237");
		assertEquals(cars[4], expected);
		assertEquals(cars[4].getRegNumber(), expected.getRegNumber());
	}

	@Test
	void findCarsByModel() {
		Car[] actual = garage.findCarsByModel("3");
		Car[] expected = { cars[0], cars[3] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void findCarsByCompany() {
		Car[] actual = garage.findCarsByCompany("Toyota");
		Car[] expected = { cars[1], cars[4] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void findCarsByEngine() {
		Car[] actual = garage.findCarsByEngine(1.0, 1.7);
		Car[] expected = { cars[0], cars[2] };
		assertArrayEquals(expected, actual);
	}

	@Test
	void findCarsByColors() {
		Car[] actual = garage.findCarsByColor("red");
		Car[] expected = { cars[0], cars[4] };
		assertArrayEquals(expected, actual);
	}

}
