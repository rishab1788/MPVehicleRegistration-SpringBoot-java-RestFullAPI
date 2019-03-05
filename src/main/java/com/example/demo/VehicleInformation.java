package com.example.demo;

public class VehicleInformation {

	String number;
	String name;
	String city;
	String data;
	String Colour;
	String type;
	String bikemaker;
	String model;
	
	public VehicleInformation()
	{
		
	}
	public VehicleInformation(String number, String name, String city, String data, String colour, String type,
			String bikemaker, String model) {
		super();
		this.number = number;
		this.name = name;
		this.city = city;
		this.data = data;
		Colour = colour;
		this.type = type;
		this.bikemaker = bikemaker;
		this.model = model;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getColour() {
		return Colour;
	}
	public void setColour(String colour) {
		Colour = colour;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBikemaker() {
		return bikemaker;
	}
	public void setBikemaker(String bikemaker) {
		this.bikemaker = bikemaker;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

}
