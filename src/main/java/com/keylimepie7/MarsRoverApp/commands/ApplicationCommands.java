package com.keylimepie7.MarsRoverApp.commands;

import java.util.HashMap;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.keylimepie7.MarsRoverApp.models.RoverModel;
import com.keylimepie7.MarsRoverApp.utilities.UtilityFunctions;

@ShellComponent
public class ApplicationCommands {
	UtilityFunctions utils = new UtilityFunctions();
	HashMap<String, RoverModel> roverList = new HashMap<>();

	@ShellMethod(key = "testing",value = "I will return a test string")
	public String testing() {
		return "Hello";
	}
	
	@ShellMethod(key = "CreateRover",value = "Create a Rover")
	public String createRover(
		@ShellOption(help = "Input <xCoord>,<yCoord>,<Direction>") String inputStr
		) {
		RoverModel newRover = utils.createRoverFromString(inputStr);
		roverList.put(newRover.getRoverName(),newRover);
		return "A New Rover was created with the name: " + newRover.getRoverName();
	}
	
	@ShellMethod(key = "GetRover",value = "Get a Rover's Details")
	public String getRover(
		@ShellOption(help = "Rover Name") String roverName
		) {
		RoverModel ourRover = roverList.get(roverName);
		return "Rover Name: " + roverName + "\nX, Y Coordinates: " + ourRover.getxCoord()+ ", " + ourRover.getyCoord() + "\nDirection: " + ourRover.getRoverDirection();
	}
	
	@ShellMethod(key = "MoveRover",value = "Move a Rover")
	public String moveRover(
		@ShellOption(help = "Rover Name") String roverName,
		@ShellOption(help = "Movement Instructions in the form <f,f,r,l,b>") String instructionStr
		) {
		RoverModel ourRover = roverList.get(roverName);
		RoverModel newPositionRover = utils.moveRover(ourRover, roverList, instructionStr);
		roverList.put(roverName, newPositionRover);
		return "Rover moved successfully";
	}
	
}
