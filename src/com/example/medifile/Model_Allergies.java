package com.example.medifile;

public class Model_Allergies {
	/**
	 * @param food
	 * @param medicines
	 * @param skin
	 * @param animals
	 */
	public Model_Allergies(String food, String medicines, String skin,
			String animals) {
		super();
		this.food = food;
		this.medicines = medicines;
		this.skin = skin;
		this.animals = animals;
	}
	String food;
	String medicines;
	String skin;
	String animals;
	public String getFood() {
		return food;
	}
	public String getMedicines() {
		return medicines;
	}
	public String getSkin() {
		return skin;
	}
	public String getAnimals() {
		return animals;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	public void setSkin(String skin) {
		this.skin = skin;
	}
	public void setAnimals(String animals) {
		this.animals = animals;
	}
	
}
