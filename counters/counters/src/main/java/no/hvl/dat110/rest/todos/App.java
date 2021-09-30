package no.hvl.dat110.rest.todos;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static ArrayList<Todos> todos = new ArrayList<Todos>();
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}
		
		Todos dummyTodo = new Todos(0, "Dummy Summary", "Dummy Description");
		todos.add(dummyTodo);
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		get("/hello", (req, res) -> "Hello World!");
		
        get("/todos", (req, res) -> {
        	Gson gson = new Gson();
        	return gson.toJson(todos);
        });
        
        put("/todos", (req,res) -> {
        	
        	Gson gson = new Gson();
        	Todos newTodo = gson.fromJson(req.body(), Todos.class);
        	
        	for(Todos todo : todos) {
        		if(todo.getID() == newTodo.getID()) {
        			todos.remove(todo);
        			todos.add(newTodo);
        			return gson.toJson(todos);
        		}
        	}
            return "ID does not exist";
        });
        
        post("/todos", (req, res) -> {
        	
            Gson gson = new Gson();
            Todos newTodo = gson.fromJson(req.body(), Todos.class);
            for (Todos todo : todos) {
                if (newTodo.getID() == todo.getID()) {
                    return "ID is not unique";
                }
            }
            
            todos.add(newTodo);
            return gson.toJson(todos);
        });
        
        delete("/todos", (req, res) -> {
        	Gson gson = new Gson();
            Map<String, Double> map = gson.fromJson(req.body(), Map.class);
            int searchedID = (map.get("id")).intValue();
            for (Todos todo : todos) {
                if (todo.getID() == searchedID) {
                    todos.remove(todo);
                    break;
                }
            }
            return gson.toJson(todos);
        });
    }
    
}
