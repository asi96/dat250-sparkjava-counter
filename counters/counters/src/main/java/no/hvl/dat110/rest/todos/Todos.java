package no.hvl.dat110.rest.todos;

import com.google.gson.Gson;

public class Todos {

	private int id;
	private String summary, description;
	
	public Todos() {
		this.summary = "";
		this.description = "";
	}
	
	public Todos(int id, String summary, String description) {
		this.id = id;
		this.summary = summary;
		this.description = description;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	String toJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(this);
    	
    	return jsonInString;
    }
}
