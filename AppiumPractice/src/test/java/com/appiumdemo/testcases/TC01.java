
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/**
 * 
 */

/**
 * @author Seshadri V
 *
 * 
 */
public class TC01 {
	 public static WebDriver driver;

	public static String createName = "SESHADRIV";
	public static String updateName = "VISHWANATHAM SESHADRI";
	public static String emp_id = "";

	public static void getemployeeData() {

		System.out.println("Fetching the emp data");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employee";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/" + emp_id);

		String responseBody = response.getBody().asString();

		System.out.println(responseBody);

		JsonPath json = new JsonPath(responseBody);

		String id = json.getString("id");

		emp_id = id;

		System.out.println("The employee 'id' is : " + emp_id);

	}


	public static void createData() {

		System.out.println("Create the emp data : ");

        // Given Base URI

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		// create request specification to get the data

		RequestSpecification request = RestAssured.given();

		// Adding data, Usig with JSON object

		JSONObject json = new JSONObject();
		json.put("name", createName);
		json.put("salary", "90000");
		json.put("age", "28");

		request.header("Content-Type", "application/json");

		request.body(json.toJSONString());

		// Send data to server

		Response createresponse = request.request(Method.POST, "/create");

		// get the body

		String responseBody = createresponse.getBody().asString();

		System.out.println("Getting the data from Server :"+responseBody);

		System.out.println("The status code of 'Created' data is : " + createresponse.getStatusCode());

		// Get the emp id
		
		JsonPath json1 = new JsonPath(responseBody);

		String id = json1.getString("id");

		emp_id = id;

		System.out.println("The employee 'id' is : " + emp_id);

	}

	public static void updateData() {

		System.out.println("Update the emp data");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/update";

		RequestSpecification httprequpdate = RestAssured.given();

		JSONObject jobjectupdate = new JSONObject();

		jobjectupdate.put("name", updateName);
		jobjectupdate.put("salary", "12345");
		jobjectupdate.put("age", "28");

		httprequpdate.header("Content-Type", "application/json");

		httprequpdate.body(jobjectupdate.toJSONString());

		Response resupdate = httprequpdate.request(Method.PUT, emp_id);
		
		String responseBody = resupdate.getBody().asString();
		
		System.out.println("The Updated data is :"+ responseBody);

		int statusUpdate = resupdate.getStatusCode();

		System.out.println("The status code of 'Updated' data is : " + statusUpdate);

		int expectedStatus = 200;

		AssertJUnit.assertEquals(statusUpdate, expectedStatus);

	}

	public static void deleteData() {

		System.out.println("Delete the emp data");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		RequestSpecification httpdelete = RestAssured.given();

		Response respdelete = httpdelete.request(Method.GET, "/employees");

		JsonPath alldata = respdelete.jsonPath();

		String empid = alldata.get("[0].id");

		System.out.println("All data is " + empid);

		Response resssdel = httpdelete.request(Method.DELETE, "/delete/" + empid);

		int statusUpdate = resssdel.getStatusCode();

		int expectedStatus = 201;

		System.out.println("The status code of 'Deleted' data is : " + statusUpdate);

		AssertJUnit.assertEquals(statusUpdate, expectedStatus);

	}
	@DataProvider(name = "demoData")
	public static  String [][] inputdata(){
		return new String[][] {
			{"adelaide","Australia"},
            {"detroit","USA"},
            {"george","South Africa"}
		};
		
		
	}
	
	String firstName = "sesh";
	 String lastName ="V";
	 
	@Test
	@Parameters("name")
	public static void demoparam(String name) {
		System.out.println(name);
		
	}
	
	@Test(dataProvider = "demoData")
	public static void demoData(String Id, String Name) {
		System.out.println(Id + " "+ Name );
		
	}
	
	
	public static void example() {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
		RequestSpecification request = RestAssured.given();
	//	Response response = 	
	}

	public static void main(String[] args) {
		createData();
		updateData();
		getemployeeData();
		
		// deleteData();

		System.out.println("========= Done ===========");
		
		File srcf = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(srcf, new File(System.getProperty("user.dir")+"/Users/etgm3/eclipse-workspace1/EventsNow_web/src/test/resources/screenShots/1.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}	

}
