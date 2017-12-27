package spark;


import database.MySQLDatabase;
import data.Chemical;

import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import static spark.Spark.*;


/**
 * HTTP URL End Points
 */
public class Main {
    public static void main(String[] args) throws SQLException {
    	MySQLDatabase db = new MySQLDatabase();

    	/**
    	 * Gets all the data from the database
    	 */
        get("/citrine-informatics/getAllData", (request, response) -> {
        	StringBuilder query = new StringBuilder();
        	query.append("SELECT * FROM data;");
        	JsonArray result = db.getData(query.toString());
        	response.type("application/json");
        	if (result.size() == 0) {
        		response.status(204);
        	} else {
                response.status(200);
        	}
        	return result;
        });
         
        /**
         * Gets all the data from the database that matches the specified chemical formula
         */
        get("/citrine-informatics/chemical_formula/:chemicalFormula", (request, response) -> {
        	StringBuilder query = new StringBuilder();
        	query.append("SELECT * FROM data WHERE Chemical_formula=").append("'").append(request.params(":chemicalFormula")).append("';");
        	JsonArray result = db.getData(query.toString());
        	
        	response.type("application/json");
        	if (result.size() == 0) {
        		response.status(204);
        	} else {
                response.status(200);
        	}
        	return result;
        });
        
        
        
        /**
         * Gets all the data from the database that matches Property 1 name, Band gap, with the specified Property 1 value
         */
        get("/citrine-informatics/property_1_name/band_gap/property_1_value/:property1value", (request, response) -> {
        	StringBuilder query = new StringBuilder();
        	query.append("SELECT * FROM data WHERE Property_1_name='Band gap' AND Property_1_value=").append(request.params(":property1value")).append(";");
        	JsonArray result = db.getData(query.toString());
        	
        	response.type("application/json");
        	if (result.size() == 0) {
        		response.status(204);
        	} else {
                response.status(200);
        	}
        	return result;
        });
        
        /**
         * Gets all the data from the database that matches property 2 name, Color, with the specified property 2 value
         */
        get("/citrine-informatics/property_2_name/Color/property_2_value/:property2value", (request, response) -> {
        	StringBuilder query = new StringBuilder();
        	query.append("SELECT * FROM data WHERE Property_2_name='Color' AND Property_2_value = ").append("'").append(request.params(":property2value")).append("';");
        	JsonArray result = db.getData(query.toString());
      
        	response.type("application/json");
        	if (result.size() == 0) {
        		response.status(204);
        	} else {
                response.status(200);
        	}
        	return result;
        });
        
        /**
         * Gets all the data from the database that matches the chemical formula, property 1 value and property 2 value 
         */
        post("/citrine-informatics/chemicalformula/property1value/property2value", (request, response) -> {
        	Chemical chem = (new Gson()).fromJson(request.body(), Chemical.class);
        	
        	StringBuilder query = new StringBuilder();
        	
        	query.append("SELECT * FROM data WHERE ")
        		 .append("Chemical_formula = ").append("'").append(chem.getChemical_formula()).append("' ")
        		 .append("AND Property_1_value = ").append(chem.getProperty_1_value())
        		 .append(" AND Property_2_value = ").append("'").append(chem.getProperty_2_value()).append("' ;");
        	System.out.println(query.toString());
        	JsonArray result = db.getData(query.toString());
        	
        	response.type("application/json");
        	if (result.size() == 0) {
        		response.status(204);
        	} else {
                response.status(200);
        	}
        	return result;
        });
        
        
        /**
         * Adds data to database
         * NOTE: data must be over as JSON data with full matching criteria 
         */
        post("/citrine-informatics/addChemical", (request, response) -> {
        	Chemical chem =  (new Gson()).fromJson(request.body(), Chemical.class);
        	
        	if (db.dataExist(chem)) {
        		response.status(404);
        		return false;
        	} else {
        		StringBuilder query = new StringBuilder();
            	query.append("INSERT INTO data (Chemical_formula, Property_1_name, Property_1_value, Property_2_name, Property_2_value) VALUES ")
            		 .append("(")
            	     .append("'").append(chem.getChemical_formula()).append("', ")
            	     .append("'").append(chem.getProperty_1_name()).append("', ")
            	     .append(chem.getProperty_1_value()).append(", ")
            	     .append("'").append(chem.getProperty_2_name()).append("', ")
            	     .append("'").append(chem.getProperty_2_value()).append("'")
            		 .append(");");
            	
            	response.status(201);
            	return db.addRemoveData(query.toString());
        	}
        });

        
        /**
         * Deletes data from database
         * NOTE: data must be sent over as JSON data with full matching criteria 
         */
        delete("/citrine-informatics/removeChemical", (request, response) -> {
        	Chemical chem =  (new Gson()).fromJson(request.body(), Chemical.class);
        	
        	if (db.dataExist(chem)) {
        		StringBuilder query = new StringBuilder();
        		query.append("DELETE FROM data WHERE ")
        			 .append("Chemical_formula = ").append("'").append(chem.getChemical_formula()).append("' AND ")
        			 .append("Property_1_name = ").append("'").append(chem.getProperty_1_name()).append("' AND ")
        			 .append("Property_1_value = ").append(chem.getProperty_1_value()).append(" AND ")
        			 .append("Property_2_name= ").append("'").append(chem.getProperty_2_name()).append("' AND ")
        			 .append("Property_2_value= ").append("'").append(chem.getProperty_2_value()).append("';");
        			 
            	response.status(200);
            	return db.addRemoveData(query.toString());
        	} else {
        		response.status(404);
        		return false;
        	}
        });
    }
}

