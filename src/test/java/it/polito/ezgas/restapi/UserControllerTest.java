package it.polito.ezgas.restapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.InvalidUserException;
import it.polito.ezgas.dto.IdPw;
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


public class UserControllerTest {
    //Constants
    private static final int USER_NOT_FOUND = -10;
    private static final int HTTP_OK = 200;

    ///number of users saved in the db at the start of the application
//    private int userNumStart = 3;

    //User used in the test
    private int userTestId0;
    private int userTestId2;
    //*  watch the creation of the userTest in the setup method
    private UserDto userTest0;
    private String userTest0Email;
    private String userTest0UserName;
    private String userTest0Password;
    private int userTest0Reputation;
    private UserDto userTest2;
    private String userTest2Email;
    private String userTest2UserName;
    private String userTest2Password;
    private int userTest2Reputation;
//    private IdPw credentials;


    //TODO : Find a way to find the user Id once saved in the db
    @Before
    public void setUp() throws  IOException {
        userTest0Email = "user100@user100.user100";
        userTest0UserName = "user100";
        userTest0Password = "password100";
        userTest0Reputation = 0;
        userTest2Email = "user2@user2.user2";
        userTest2UserName = "user2";
        userTest2Password = "password2";
        userTest2Reputation = 0;
        userTest2 = new UserDto(200, userTest2UserName, userTest2Password, userTest2Email, userTest2Reputation);
        userTest0 = new UserDto(100, userTest0UserName, userTest0Password, userTest0Email, userTest0Reputation);


        testSaveUser(userTest0);
        userTestId0 = findUserId(userTest0Email);
    }


    @After
    public void cleanUp() throws  IOException {
        testDeleteUser(userTestId0);
    }


    @Test
    public void testGetAllUserWrapper() throws  IOException {
//        UserDto[] UserDtos =
        testGetAllUser();
        //this is to check the number of the users in the db
//        assert(UserDtos.length == userNumStart);
    }


    @Test
    public void testGetUserByIdWrapper() throws IOException {
        testGetUserById(userTestId0,userTest0Email);
    }

    @Test
    public void testSaveDeleteUserWrapper() throws  IOException {
        testSaveUser(userTest2);
        userTestId2 = findUserId(userTest2Email);
        testDeleteUser(userTestId2);
        assert(!testGetUserById(userTestId2,userTest2Email));
    }



    @Test
    public void testLoginWrapper() throws  IOException {
        IdPw credentials0 = new IdPw(userTest0Email,userTest0Password);
        testLogin(credentials0,userTest0UserName);

        IdPw credentialsWrong = new IdPw("wrong@wrong.wrong","wrong");
        assert(!testLogin(credentialsWrong,userTest0UserName));

    }
    @Test
    public void testReputationWrapper() throws  IOException {
        testIncreaseUserReputation(userTestId0,userTest0Reputation+1);
        testDecreaseUserReputation(userTestId0,userTest0Reputation);

        for(int i = 0; i <5; i++)
            testIncreaseUserReputation(userTestId0,userTest0Reputation+1+i);

        testIncreaseUserReputation(userTestId0,userTest0Reputation+5);
        testIncreaseUserReputation(userTestId0,userTest0Reputation+5);

        for(int i = 0; i <10; i++)
            testDecreaseUserReputation(userTestId0,userTest0Reputation+5-1-i);

        testDecreaseUserReputation(userTestId0,userTest0Reputation-5);
        testDecreaseUserReputation(userTestId0,userTest0Reputation-5);

    }


    private void testIncreaseUserReputation(int testUserId, int expectedReputation) throws  IOException{
        HttpUriRequest request = new HttpPost("http://localhost:8080/user/increaseUserReputation/" + testUserId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        checkReputation(userTestId0,expectedReputation);

    }

    private void testDecreaseUserReputation(int testUserId,int expectedReputation) throws  IOException{
        HttpUriRequest request = new HttpPost("http://localhost:8080/user/decreaseUserReputation/" + testUserId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        checkReputation(userTestId0,expectedReputation);
    }

    /**
     * search the user with testGetAll method (using the get) and it will check the reputation
     * @param testUserId
     * @param expectedReputation
     * @return  the reputation of the user found in the database
     *          USER_NOT_FOUND if there are no user with that id
     * @throws ClientProtocolException
     * @throws IOException
     */
    private int checkReputation(int testUserId,int expectedReputation) throws ClientProtocolException, IOException{

        UserDto[] users = testGetAllUser();
        for (UserDto u : users) {
            if (u.getUserId() ==  testUserId) {
                assert (u.getReputation() == (expectedReputation));
                return u.getReputation();
            }
        }
        return USER_NOT_FOUND;
    }

    /**
     * Test with http post sending the credentials
     * the method generate a json string with the loginDto fields
     * It fails if with a given credentials the json doesn't contain the expected userName
     * @param credentials given credentials
     * @param userName expected username
     * @return true if the expected username is inside the json
     *          false if the json is empty (= no user with the given credentials)
     * @throws ClientProtocolException
     * @throws IOException
     */
    private boolean testLogin(IdPw credentials, String userName) throws ClientProtocolException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(credentials);
        HttpPost request = new HttpPost("http://localhost:8080/user/login");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        if(jsonResponse.compareTo("")==0)
            return false;
        assert (jsonResponse.contains(userName));
        return true;
    }
    /**
     * Test the get all user with http get
     *
     * @return an array of users found by this test
     * @throws ClientProtocolException
     * @throws IOException
     */
    private UserDto[] testGetAllUser() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        assert (jsonResponse.contains(userTest0UserName));
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonResponse, UserDto[].class);
    }

    /**
     * Test the get user by id using the http get
     * !!! If there are no user with that given Id the test wont fail it will only return false!!!
     * The test fails if the http
     * @param userTestId user id that you want to find
     * @param userTestEmail expected user name
     * @return true if there is the user with that given id, false otherwise
     * @throws ClientProtocolException
     * @throws IOException
     */
    private boolean testGetUserById(int userTestId, String userTestEmail) throws ClientProtocolException, IOException {

        HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/" + userTestId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        if (!jsonResponse.contains(userTestEmail)) {
            return false;
        }
        assert (jsonResponse.contains(userTestEmail));

        return true;
    }

    /**
     * test the save user with http post
     *
     * @param user the user (dto) you want to save in the db
     * @throws ClientProtocolException
     * @throws IOException
     */
    private void testSaveUser(UserDto user) throws ClientProtocolException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        HttpPost request = new HttpPost("http://localhost:8080/user/saveUser");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
    }

    /**
     * test the delete user with http delete
     *
     * @param userId the id of the user you want delete
     * @throws ClientProtocolException
     * @throws IOException
     * @throws InvalidUserException
     */
    private void testDeleteUser(int userId) throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpDelete("http://localhost:8080/user/deleteUser/" + userId);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert (response.getStatusLine().getStatusCode() == HTTP_OK);
    }

    /**
     * search the user id using the get all user (using the http get)
     *
     * @param userEmail the email of the user that you want
     * @return the id of that user
     */
    private int findUserId(String userEmail) throws  IOException {
        UserDto[] users = testGetAllUser();
        for (UserDto u : users) {
            if (u.getEmail().compareTo(userEmail) == 0)
                return u.getUserId();
        }
        return USER_NOT_FOUND;
    }

}
