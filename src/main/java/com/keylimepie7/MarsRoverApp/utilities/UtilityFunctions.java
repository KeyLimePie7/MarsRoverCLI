package com.keylimepie7.MarsRoverApp.utilities;

import java.util.Arrays;
import java.util.HashMap;

import com.keylimepie7.MarsRoverApp.models.RoverModel;

public class UtilityFunctions {
	Integer incrementingInt = 0;

	public RoverModel createRoverFromString(String inputStr) {
		// input string will be in the form 3,4,N
		// so xCoord is 3
		// yCoord is 4
		// direction is North
		String[] inputArr = inputStr.split(",");
		String direction;
		switch (inputArr[2]) {
			case "N":
				direction = "North";
				break;
			case "S":
				direction = "South";
				break;
			case "E":
				direction = "East";
				break;
			case "W":
				direction = "West";
				break;
			default:
				direction = "North";
		}
		incrementingInt++;
		return new RoverModel(String.valueOf(incrementingInt), direction, Integer.parseInt(inputArr[0]), Integer.parseInt(inputArr[1]));
	}
	
	public Boolean isThereCollision(HashMap<Integer, Integer> occupiedCoords, Integer xCoord, Integer yCoord) {
		if (occupiedCoords.containsKey(xCoord)) {
			if (occupiedCoords.get(xCoord) == yCoord) {
				System.out.println("Collision Prevented");
				return true;
			}
		}
		return false;
	}
	
	public RoverModel moveRover(RoverModel roverToMove, HashMap<String, RoverModel> roverSet, String moveInstructions) {
		// moveInstructions will be in the form “f,f,r,f,f”
		// Possible Commands:
//		f – Move forward 1 coordinate in the current direction
//		b – Move backward 1 coordinate in the current direction
//		r – Rotate 90 degree clock-wise from current direction
//		l – Rotate 90 degree anti clock-wise from current direction
		String roverToMoveName = roverToMove.getRoverName();
		String roverToMoveDirection = roverToMove.getRoverDirection();
		Integer roverToMoveXCoord = roverToMove.getxCoord();
		Integer roverToMoveYCoord = roverToMove.getyCoord();
		String[] instructionArr = moveInstructions.split(",");
		HashMap<Integer,Integer> occupiedCoords = new HashMap<>();
		roverSet.forEach((key, value) -> {
			if (!key.equals(roverToMove.getRoverName())) {
				occupiedCoords.put(value.getxCoord(), value.getyCoord());				
			}
		});
		for (int i = 0; i < instructionArr.length ; i++) {
			switch (instructionArr[i]) {
			case ("f"): {
				Integer newXCoord = roverToMoveXCoord;
				Integer newYCoord = roverToMoveYCoord;
				if (roverToMoveDirection.equalsIgnoreCase("NORTH")) {
					newYCoord++;
				} else if (roverToMoveDirection.equalsIgnoreCase("SOUTH")) {
					newYCoord--;
				} else if (roverToMoveDirection.equalsIgnoreCase("EAST")) {
					newXCoord++;
				} else if (roverToMoveDirection.equalsIgnoreCase("WEST")) {
					newXCoord--;
				}
				if (!isThereCollision(occupiedCoords, newXCoord, newYCoord)) {
					roverToMoveXCoord = newXCoord;
					roverToMoveYCoord = newYCoord;
				} else {
					return new RoverModel(roverToMoveName, roverToMoveDirection, roverToMoveXCoord, roverToMoveYCoord);
				}
				break;
			}
			case ("b"): {
				Integer newXCoord = roverToMoveXCoord;
				Integer newYCoord = roverToMoveYCoord;
				if (roverToMoveDirection.equalsIgnoreCase("NORTH")) {
					newYCoord--;
				} else if (roverToMoveDirection.equalsIgnoreCase("SOUTH")) {
					newYCoord++;
				} else if (roverToMoveDirection.equalsIgnoreCase("EAST")) {
					newXCoord--;
				} else if (roverToMoveDirection.equalsIgnoreCase("WEST")) {
					newXCoord++;
				}
				if (!isThereCollision(occupiedCoords, newXCoord, newYCoord)) {
					roverToMoveXCoord = newXCoord;
					roverToMoveYCoord = newYCoord;
				} else {
					return new RoverModel(roverToMoveName, roverToMoveDirection, roverToMoveXCoord, roverToMoveYCoord);
				}
				break;
			}
			case ("r"):
				if (roverToMoveDirection.equalsIgnoreCase("NORTH")) {
					roverToMoveDirection = "EAST";
				} else if (roverToMoveDirection.equalsIgnoreCase("SOUTH")) {
					roverToMoveDirection = "WEST";
				} else if (roverToMoveDirection.equalsIgnoreCase("EAST")) {
					roverToMoveDirection = "SOUTH";
				} else if (roverToMoveDirection.equalsIgnoreCase("WEST")) {
					roverToMoveDirection = "NORTH";
				}
				break;
			case ("l"):
				if (roverToMoveDirection.equalsIgnoreCase("NORTH")) {
					roverToMoveDirection = "WEST";
				} else if (roverToMoveDirection.equalsIgnoreCase("SOUTH")) {
					roverToMoveDirection = "EAST";
				} else if (roverToMoveDirection.equalsIgnoreCase("EAST")) {
					roverToMoveDirection = "NORTH";
				} else if (roverToMoveDirection.equalsIgnoreCase("WEST")) {
					roverToMoveDirection = "SOUTH";
				}
				break;
			}
		}
		return new RoverModel(roverToMoveName, roverToMoveDirection, roverToMoveXCoord, roverToMoveYCoord);
	}
}
