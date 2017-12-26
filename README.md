# Citrine Informatics
Citrine Informatics Challenge Project

###
A readme for the Citrine Informatics Scientific Software Engineer  challenge project. below are some notes to keep in mind when working with this project:

- This is a Maven project so it needs to be imported as an 'Existing Maven Project'. The pom.xml file is included int the project.

- I use a MySQL java driver to connect to a MySQL Database so the driver must be added to the build path.

- In the MySQLDatabase Class, username, password & connectionURL fields are set to my MYSQL database on my laptop, so to test on your machine those fields must to be changed to your machine preference. 

- I've changed the first line of the csv file that was originally given for this project:
  before: Chemical formula, Property 1 name, Property 1 value, Property 2 name, Property 2 value
  AFTER: Chemical_formula, Property_1_name, Property_1_value, Property_2_name, Property_2_value
  
- The default port that Spark uses is '4567'.
	- Example test on a CLI: curl localhost:4567/citrine-informatics/getAllData
	

- Each HTTP endpoint either returns a JSON array, which can be empty, or a boolean with an appropriate response type and HTTP status code.
  
- The two posts and delete HTTP endpoints I've created take in a JSON object for request.body() that should look similiar like so (the values can be different as this is just a template):

```
    {
	"Chemical_formula": "value",
	"Property_1_name" : "value",
	"Property_1_value" : value,
	"Property_2_name" : "value",
	"Property_2_value" : "value"
    }
```

	
- To the post and delete endpoints, I used a chrome extension called Postman, which allows you to not only test HTTP endpoints, but you can also send over example data for each respected HTTP verb. To do so you choose the verb with the corrrect endpoint, then click on body, click on raw and change the type to JSON (application/json), and type out the JSON data, which should look similiar to the example above. If you have a different way of testing then this shouldn't be an issue this is just how I tested.
	
- All the source code is located in the 'src/main/java' folder, and the Main.java file in the 'spark' package runs the entire project.

### Thoughts to extend the challenge
Creating HTTP endpoints are favoritue specialty of mine, and I had an idea that could extend the project. I created this API using a MySQL database and creating this API, with HTTP endpoints in Java. One way to extend this is to create an HTTP edpoint that allows a user to submit a big chunk of data through their own csv file to the database. To create this we would first need an endpoint that takes in a csv file then we would create a method in the MySQLDatabase class that reads in a csv file. We would first make sure that the file submitted be the user is a csv file. Then we would check the csv file's first line matches our fields in the database or if it doesn't have  the fields in the first line and then read each line by line to check if there's a chemical formula, property 1 name, property 1 value, property 2 name, and property 2 value. If this is correct we would insert that line into the database. We would essetnially keeping to the end of the file; however, we must also watch for some possible errors, such as, there is more data on one line, not enough data on one line, or if the data doesn't match the field type. With more time this would definitely be something to look into and create.

### Conclusion
I've created this API hopefully with enough information that any Software Engineer could build more from this or make eny edits with ease. Overall this was extremely enjoyable and should you need any clarification please do not hestitate to contact me!
