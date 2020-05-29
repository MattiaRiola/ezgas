package it.polito.ezgas.restapi;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polito.ezgas.dto.GasStationDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class GasStationControllerTest {
    @Test
    public void testGetAllGasStation() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assert(response.getStatusLine().getStatusCode() == 200);
        String jsonResponse = EntityUtils.toString(response.getEntity());
        assert (jsonResponse.contains("Enjoy"));

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GasStationDto[] gasStationDtos = mapper.readValue(jsonResponse, GasStationDto[].class);
        assert(gasStationDtos.length == 2);
    }

    @Test
    public void testSaveGasStation() throws ClientProtocolException, IOException {
        // Via Amba Aradam really exists in Padua LOL
        GasStationDto gasStation = new GasStationDto(1, "Gas n' Roll",
                "Via Amba Aradam 10", true, true, true, true,
                true, "A CarSharing Company Inc.", 1.0, 2.0, 1.0, 1.1,
                1.2, 1.3, 1.4, 1, LocalDateTime.now().toString(), 75.0);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gasStation);
        HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
        StringEntity entity = new StringEntity(json);
        request.addHeader("content-type","application/json");
        request.setEntity(entity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        assert(response.getStatusLine().getStatusCode() == 200);
    }
}
