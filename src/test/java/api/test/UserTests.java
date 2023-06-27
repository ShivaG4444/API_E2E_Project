package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	
	{
		Response response=UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void getuserdetails()
	{
		Response response=UserEndpoints.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	
	{
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response updateresponse=UserEndpoints.updateUser(userPayload.getUsername(), userPayload);
		updateresponse.then().log().all();
		Assert.assertEquals(updateresponse.getStatusCode(), 200);
		
		//verify if details are updated successfully
		
		Response newresponse=UserEndpoints.readUser(userPayload.getUsername());
		newresponse.then().log().all();
		Assert.assertEquals(newresponse.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=4)
	public void deleteuserbyusername()
	{
		Response response=UserEndpoints.deleteUser(userPayload.getUsername()) ;
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 405);
	}
	
	
	
	

}
