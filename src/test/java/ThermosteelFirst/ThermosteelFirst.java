package ThermosteelFirst;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ThermosteelFirst {

	@Test
	public void MyFirstGet() {
		//System.out.println("Jai Ganan Maharaj");
		
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
	       param("location","-33.8670522,151.1957362").
	       param("radius","500").
	       param("key","AIzaSyDIQgAh0B4p0SdyYkyW8tlG-y0yJMfss5Y").
	       when().
	       get("/maps/api/place/nearbysearch/json").
	       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	     //  body("results[0].geometry.location.lat",equalTo("-33.86882"));
	       body("results[0].name",equalTo("Sydney")).and().
	       header("Server", "scaffolding on HTTPServer2");
	      // .and().contentType(ContentType.JSON).and().
				/*
				 * body("results[0].name",equalTo("Sydney")).and(). body("results[0].place_id",
				 * equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and(). header("Server","pablo");*/
	}
}
