# Citrine Informatics
Citrine Informatics Challenge Project

###
A readme for the Citrine informatics Scientifici Software Engineer challenge project. below are some notes to keep in mind when working with this project:

- This is a Maven project so it needs to be imported as an 'Existing Maven Project'. The pom.xml file is included int the project.

- I use a MySQL java driver to connect to a MySQL Database so the driver must be added to the build path.

- In the MySQLDatabase Class, username, password & connectionURL fields are set to my MYSQL database on my laptop, so to test on your machine those fields must to be changed to your machine preference. 

- I've changed the first line of the csv file that was originally given for this project:
  before: Chemical formula, Property 1 name, Property 1 value, Property 2 name, Property 2 value
  AFTER: Chemical_formula, Property_1_name, Property_1_value, Property_2_name, Property_2_value
  
- The two posts and delete HTTP endpoints I've created take in a JSON object for request.body() that look similiar like so:
// The values can be different as this is just a template
{
	"Chemical_formula": "value",
	"Property_1_name" : "value",
	"Property_1_value" : value,
	"Property_2_name" : "value",
	"Property_2_value" : "value"
}

- All the source code is located in the 'src/main/java' folder, and the Main.java file in the 'spark' package runs the entire project.




###
Should you need any clarification please do not hestitate to contect me!
