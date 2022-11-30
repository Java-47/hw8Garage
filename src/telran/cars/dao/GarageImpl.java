package telran.cars.dao;

import java.util.function.Predicate;

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
			if (cars[i].getRegNumber().equals(regNumber)) {
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
		return findCarByPredicate(c -> c.getRegNumber().equals(regNumber));
	}

	@Override
	public Car[] findCarsByModel(String model) {
		return findCarsByPredicate(c -> c.getModel().equals(model));
	}

	@Override
	public Car[] findCarsByCompany(String company) {
		return findCarsByPredicate(c -> c.getCompany().equals(company));
	}

	@Override
	public Car[] findCarsByEngine(double min, double max) {
		return findCarsByPredicate(c -> c.getEngine() >= min && c.getEngine() < max );
	}

	@Override
	public Car[] findCarsByColor(String color) {
		return findCarsByPredicate(c -> c.getColor().equals(color));
	}

	private Car[] findCarsByPredicate(Predicate<Car> predicate) {
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(cars[i])) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (predicate.test(cars[i])) {
				res[j++] = cars[i];
			}
		}
		return res;
	}
	private Car findCarByPredicate(Predicate<Car> predicate) {
		Car res = null;
		for (int i = 0; i < size; i++) {
			if (predicate.test(cars[i])) {
				res = cars[i];
			}
		}
		return res;

	}

}
