package it.polito.ezgas.controllertests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;

public class GasStationControllerTest {
    //Constants
    private static final int NOT_FOUND = -10;
    private static final int HTTP_OK = 200;
	
	
	private GasStationDto station;
	
	@Before @Test 
	public void setUpDataBase() throws ClientProtocolException, IOException{
		//Delete all the gas station.
		TestDelateGasStation();
	
		//Save a GasStation as setup.
        this.station = new GasStationDto(2, "MyTestStation",
                "Via Bligny 11", true, true, true, true,
                true,true, "Enjoy", 1.3, 2.0, 1.1, 1.1,
                1.2, 1.3, 1.4,1.5, 1, LocalDateTime.now().toString(), 75.0);
        //Save the gas station.
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this.station);
        HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type","application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert(response.getStatusLine().getStatusCode() == 200);
	}//EndSetUp.
	
	@After @Test //After the test suite has been executed delete the data stored in the database.
	public void TestDelateGasStation() throws ClientProtocolException, IOException{
		//Get all station list.
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assert(response.getStatusLine().getStatusCode() == HTTP_OK);
        String jsonResponse = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GasStationDto[] gasStationDtos = mapper.readValue(jsonResponse, GasStationDto[].class);
        //Scroll the list and delete.
        for (int i=0;i<gasStationDtos.length; i++) {
        	 //Delete the test stations.
        	 if (gasStationDtos[i].getGasStationName().contains("Test")||gasStationDtos[i].getGasStationName().contains("test")) {
		         HttpUriRequest delateRequest = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/"+gasStationDtos[i].getGasStationId().toString());
		         HttpResponse deleteResponse = HttpClientBuilder.create().build().execute(delateRequest);
		         assert(deleteResponse.getStatusLine().getStatusCode() == HTTP_OK);
        	 }
        }
	}//EndTest.
	
    @Test
    public void TestGetAllGasStation() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assert(response.getStatusLine().getStatusCode() == HTTP_OK);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        assert (jsonResponse.contains("Enjoy"));
        
    }//EndTest.

    @Test
    public void TestSaveGasStation() throws ClientProtocolException, IOException {
        GasStationDto gasStation = new GasStationDto(1, "Gas n' Roll Test",
                "Via Amba Aradam 10", true, true, true, true,
                true,true, "A CarSharing Company Inc.", 1.0, 2.0, 1.0, 1.1,
                1.2, 1.3, 1.4,1.5, 1, LocalDateTime.now().toString(), 75.0);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gasStation);
        HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type","application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert(response.getStatusLine().getStatusCode() == HTTP_OK);
    }//EndTest.
    
    @Test
    public void TestGetGasStationById() throws ClientProtocolException, IOException {
    	HttpUriRequest getAllRequest = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
    	HttpResponse getAllResponse = HttpClientBuilder.create().build().execute(getAllRequest);

        String jsonGetAllResponse = EntityUtils.toString(getAllResponse.getEntity());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GasStationDto[] gasStationDtos = mapper.readValue(jsonGetAllResponse, GasStationDto[].class);
        
        for (int i=0; i<gasStationDtos.length; i++) {
        	if (gasStationDtos[i].getGasStationName().equals(this.station.getGasStationName())) {
        		//Find gas station by id request.
        		HttpUriRequest requestId = new HttpGet("http://localhost:8080/gasstation/getGasStation/"+gasStationDtos[i].getGasStationId().toString());
        		HttpResponse responseId = HttpClientBuilder.create().build().execute(requestId);
        		assert(responseId.getStatusLine().getStatusCode() == HTTP_OK);
        		
        		String jsonResponseId = EntityUtils.toString(responseId.getEntity());
        		assert (jsonResponseId.contains(this.station.getGasStationAddress()));
        	}
        }//EndFor.
    }//EndTest.
    
    @Test
    public void TestGetGasStationsByGasolineType() throws ClientProtocolException, IOException {
    	HttpUriRequest getGasoline = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/Diesel");
    	HttpResponse getGasolineResponse = HttpClientBuilder.create().build().execute(getGasoline);
    	
    	String jsonGetGasolineResponse = EntityUtils.toString(getGasolineResponse.getEntity());
    	
    	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GasStationDto[] gasStationDtos = mapper.readValue(jsonGetGasolineResponse, GasStationDto[].class);
        
        for (int i=0; i<gasStationDtos.length; i++) {
        	assert(gasStationDtos[i].getHasDiesel() == true);
        }
    }//EndTest.
    
    @Test 
    public void TestGetGasStationsByProximity() throws ClientProtocolException, IOException {
    	HttpUriRequest getProx = new HttpGet("http://localhost:8080/gasstation/searchGasStationByProximity/"+this.station.getLat()+"/"+ this.station.getLon()+"/0/");
    	HttpResponse getProxResponse = HttpClientBuilder.create().build().execute(getProx);
    	
    	String jsonGetProxResponse = EntityUtils.toString(getProxResponse.getEntity());
    	System.out.println (jsonGetProxResponse);
    	assert (jsonGetProxResponse.contains(this.station.getGasStationName()));
    }//EndTest.
    
    @Test
    public void TestGetGasStationsWithCoordinates() throws ClientProtocolException, IOException {
    	HttpUriRequest getCoord = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/"+this.station.getLat()+"/"+ this.station.getLon()+"/0/Diesel/"+this.station.getCarSharing());
    	HttpResponse getCoordResponse = HttpClientBuilder.create().build().execute(getCoord);
    	
    	String jsonGetCoordResponse = EntityUtils.toString(getCoordResponse.getEntity());
    	assert (jsonGetCoordResponse.contains(this.station.getGasStationName()));
    }//EndTest.
    
    @Test 
    public void TestSetReport() throws ClientProtocolException, IOException {
    	//Get gas station Id.
    	HttpUriRequest getAllRequest = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
    	HttpResponse getAllResponse = HttpClientBuilder.create().build().execute(getAllRequest);

        String jsonGetAllResponse = EntityUtils.toString(getAllResponse.getEntity());

        ObjectMapper mapperStation = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GasStationDto[] gasStationDtos = mapperStation.readValue(jsonGetAllResponse, GasStationDto[].class);
        Integer gasId = null;
        for (int i=0; i<gasStationDtos.length; i++) {
        	if (gasStationDtos[i].getGasStationName().equals(this.station.getGasStationName())) {
        		gasId = gasStationDtos[i].getGasStationId();
        	}
        }
        //Create an User.
        UserDto user = new UserDto((Integer) 1, "TestUserName", "TestPassword", "Test@email.com", (Integer) 0);
        ObjectMapper mapperUser = new ObjectMapper();
        String json = mapperUser.writeValueAsString(user);
        HttpPost request = new HttpPost("http://localhost:8080/user/saveUser");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        //Get the user id.
        HttpUriRequest getAllUserReq = new HttpGet("http://localhost:8080/user/getAllUsers");
    	HttpResponse getAllUserResponse = HttpClientBuilder.create().build().execute(getAllUserReq);
    	String jsonGetAllUserResponse = EntityUtils.toString(getAllUserResponse.getEntity());
    	ObjectMapper mapperAllUser = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserDto[] userDtos = mapperAllUser.readValue(jsonGetAllUserResponse, UserDto[].class);
        Integer userId = null;
        for (int i=0; i<userDtos.length; i++) {
        	if (userDtos[i].getUserName().equals("TestUserName")) {
        		userId = userDtos[i].getUserId();
        	}
        }
        //Write Report request using gasId and userId.
        HttpUriRequest reportRequest = new HttpPost("http://localhost:8080/gasstation/setGasStationReport/"+gasId.toString()+"/5.0/5.0/5.0/5.0/5.0/5.0/"+userId.toString());
    	HttpResponse reportResponse = HttpClientBuilder.create().build().execute(reportRequest);
    	assert (reportResponse.getStatusLine().getStatusCode() == HTTP_OK);
    	//Check the report result.
    	HttpUriRequest requestGetById = new HttpGet("http://localhost:8080/gasstation/getGasStation/"+gasId.toString());
		HttpResponse responseGetById = HttpClientBuilder.create().build().execute(requestGetById);
		
		String jsonGetByIdResponse = EntityUtils.toString(responseGetById.getEntity());
		assert(jsonGetByIdResponse.contains(this.station.getGasStationName()));
		assert(jsonGetByIdResponse.contains("5.0"));
		
		//Deate test user from database.
		HttpUriRequest requestUserDelete = new HttpDelete("http://localhost:8080/user/deleteUser/"+userId.toString());
	    HttpClientBuilder.create().build().execute(requestUserDelete);
    }//EndTest.
}//EndClass.
