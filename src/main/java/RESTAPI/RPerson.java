package RESTAPI;

import java.util.ArrayList;
import java.util.HashMap;

public record RPerson(String id, 
		String name, 
		String description, 
		ArrayList<String> externalLinks, 
		HashMap<String, ArrayList<String>> internalLinks, 
		String pronouns, 
		String email, 
		String phone) {}
