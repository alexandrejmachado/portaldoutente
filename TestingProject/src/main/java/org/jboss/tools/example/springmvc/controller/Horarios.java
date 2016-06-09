package org.jboss.tools.example.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Horarios {

	
	public static ArrayList<String> getHorarios(){
		return new ArrayList<String>(Arrays.asList(new String[]{"08:00","08:30",
				"09:00","09:30","10:00","10:30","11:00","11:30",
				"14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30"}));
	}
}
