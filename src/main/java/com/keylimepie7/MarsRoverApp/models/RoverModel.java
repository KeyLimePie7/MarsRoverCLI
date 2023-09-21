package com.keylimepie7.MarsRoverApp.models;

public class RoverModel {
	String roverName;
	String roverDirection;
	Integer xCoord;
	Integer yCoord;
	
	public RoverModel(String roverName, String roverDirection, Integer xCoord, Integer yCoord) {
		super();
		this.roverName = roverName;
		this.roverDirection = roverDirection;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	public String getRoverName() {
		return roverName;
	}
	public void setRoverName(String roverName) {
		this.roverName = roverName;
	}
	public String getRoverDirection() {
		return roverDirection;
	}
	public void setRoverDirection(String roverDirection) {
		this.roverDirection = roverDirection;
	}
	public Integer getxCoord() {
		return xCoord;
	}
	public void setxCoord(Integer xCoord) {
		this.xCoord = xCoord;
	}
	public Integer getyCoord() {
		return yCoord;
	}
	public void setyCoord(Integer yCoord) {
		this.yCoord = yCoord;
	}
	
}
