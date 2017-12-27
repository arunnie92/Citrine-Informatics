package example;

import static spark.Spark.*;

import com.google.gson.Gson;

public class SparkRestExample {

	public static void main(String[] args) {
		
        get("/users", (request, response) -> {
        	response.type("application/json");
            User user = new Gson().fromJson(request.body(), User.class);
            UserService.addUser(user);
         
            return new Gson()
              .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });
        
		
	}

}
