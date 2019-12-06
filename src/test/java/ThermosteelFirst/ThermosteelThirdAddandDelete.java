package ThermosteelFirst;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class ThermosteelThirdAddandDelete {
	// Add an entry and capture the response
	@Test
	public void thirdAddDele() {
		// Task 1 - Capture the response
		RestAssured.baseURI = ("http://216.10.245.166");
		Response res = given().queryParam("key", "qaclick123").and()
				.body("{" + "\"location\":{" + "\"lat\": -38.383494," + "\"lng\" : 33.427362}," + "\"accuracy\":50,"
						+ "\"name\":\"Frontline house\"," + "\"phone_number\":\"(+91) 983 893 3937\"," +

						"\"address\":\"29, side layout, cohen 09\"," +

						"\"types\":[\"shoe park\",\"shop\"]," +

						"\"website\":\"http://google.com\"," +

						"\"language\":\"French-IN\"" + "}")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).
				// Extract the response in and store it in to a variable
				extract().response();

		// Task 2 Capture the Place id from the response
		String responseString = res.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		String placeId = js.get("place_id");
		System.out.println(placeId);

		// place the place id in the Delete request
		given().queryParam("key","qaclick123").
		body("{"+
			"\"place_id\":	\""+placeId+"\""+
				"}").
		when().
		post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));

	}

}
