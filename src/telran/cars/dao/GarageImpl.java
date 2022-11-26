package telran.cars.dao;

import telran.cars.model.Car;

public class GarageImpl implements Garage {
	private Car[] cars;
	private int size;

	public GarageImpl(int capacity) {
		cars = new Car[capacity];

	}

	@Override
	public boolean addCar(Car car) {
		if (size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
			return false;
		}
		cars[size++] = car;
		return true;
	}

	@Override
	public Car removeCar(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (cars[i].getRegNumber() == regNumber) {
				Car victim = cars[i];
				cars[i] = cars[--size];
				cars[size] = null;
				return victim;
			}
		}
		return null;
	}

	@Override
	public Car findCarByRegNumber(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (cars[i].getRegNumber() == regNumber) {
				return cars[i];
			}
		}
		return null;
	}

	@Override
	public Car[] findCarsByModel(String model) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (cars[i].getModel() == model) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (cars[i].getModel() == model) {
				res[j++] = cars[i];
			}
		}
		return res;
	}

	@Override
	public Car[] findCarsByCompany(String company) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (cars[i].getCompany() == company) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (cars[i].getCompany() == company) {
				res[j++] = cars[i];
			}
		}
		return res;
	}

	@Override
	public Car[] findCarsByEngine(double min, double max) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (cars[i].getEngine() >= min && cars[i].getEngine() < max) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (cars[i].getEngine() >= min && cars[i].getEngine() < max) {
				res[j++] = cars[i];
			}
		}
		return res;
	}

	@Override
	public Car[] findCarsByColors(String color) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (cars[i].getColor() == color) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (cars[i].getColor() == color) {
				res[j++] = cars[i];
			}
		}
		return res;
	}

	@Override
	public int quantity() {
		return size;
	}
}
