package RESTAPI;

import java.util.ArrayList;
import java.util.HashMap;

public record RCompany(String id,
String name, 
String description, 
ArrayList<String> externalLinks, 
HashMap<String, ArrayList<String>> internalLinks) {

}
