package com.example.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class CallWeatherAPI{

  //main method to get out the wanted information from our weather API
  public static Weather getWeather(String location) throws Exception{
    String apiEndPoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    String apiKey = "5CK8M2PR75WQ693V4MUGXBVEW";
    String unitGroup = "metric";

    StringBuilder requestBuilder = new StringBuilder(apiEndPoint);
    requestBuilder.append(URLEncoder.encode(location, StandardCharsets.UTF_8.toString()));

    URIBuilder builder = new URIBuilder(requestBuilder.toString());
    builder.setParameter("unitGroup",unitGroup)
           .setParameter("key",apiKey)
           .setParameter("include","days");

    HttpGet get = new HttpGet(builder.build());

    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = httpClient.execute(get);

    String rawResult = null;
    try {
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.out.printf("Bad response status code:%d%n", response.getStatusLine().getStatusCode());
				return new Weather();
			}

			HttpEntity entity = response.getEntity();
		  if (entity != null) {
		    rawResult=EntityUtils.toString(entity, Charset.forName("utf-8"));
		  }


		} finally {
			response.close();
		}
    Weather weather = parseWeatherInfoJson(rawResult);
    return weather;


  }

  // Parse the data from our raw results and mine the data we need for our app
  private static Weather parseWeatherInfoJson(String rawResult) {
    if(rawResult == null || rawResult.isEmpty()){
      System.out.printf("No raw data%n");
			return new Weather();
		}

		JSONObject weatherInfo = new JSONObject(rawResult);

		ZoneId zoneId=ZoneId.of(weatherInfo.getString("timezone"));

    String[] preciptype1 = null;


		JSONArray values=weatherInfo.getJSONArray("days");
    JSONObject day1 = values.getJSONObject(0);
    String strday1 = day1.toString();

    double temp1 = day1.getDouble("temp");

    double feelslike1 = day1.getDouble("feelslike");

    double precip1 = day1.getDouble("precip");

    double precipprob1 = day1.getDouble("precipprob");

    String sunrise1 = day1.getString("sunrise");

    String sunset1 = day1.getString("sunset");

    Object day1JSON = day1.get("preciptype");
    if(day1JSON instanceof JSONArray){
      JSONArray precipJSON1 = day1.getJSONArray("preciptype");
      preciptype1 = new String[precipJSON1.length()];
      for(int i = 0; i < precipJSON1.length(); i++){
        preciptype1[i] = precipJSON1.getString(i);
      }
    } else {
      preciptype1 = new String[1];
      preciptype1[0] = "Unknown";
    }

    String date1 = ZonedDateTime.ofInstant(Instant.ofEpochSecond(day1.getLong("datetimeEpoch")), zoneId).format(DateTimeFormatter.ISO_LOCAL_DATE);

    return new Weather(temp1,feelslike1,precip1,precipprob1,sunrise1,sunset1,preciptype1,date1);

	}

}
