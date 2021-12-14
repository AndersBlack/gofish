package com.example.demo;

import java.time.ZonedDateTime;

public class Weather{

  private double temp1;
  private double feelslike1;
  private double precip1;
  private double precipprob1;
  private String sunrise1;
  private String sunset1;
  private String[] preciptype1;
  private String day1;

  public Weather(){
    this.temp1 = 0;
    this.feelslike1 = 0;
    this.precip1 = 0;
    this.precipprob1 = 0;
    this.sunrise1 = "Unknown";
    this.sunset1 = "Unknown";
    this.preciptype1 = new String[0];
    this.day1 = "Unknown";
  }

  //Constructor for a weather object with weather info
  public Weather(double temp1, double feelslike1, double precip1, double precipprob1, String sunrise1, String sunset1, String[] preciptype1, String day1){
    this.temp1 = temp1;
    this.feelslike1 = feelslike1;
    this.precip1 = precip1;
    this.precipprob1 = precipprob1;
    this.sunrise1 = sunrise1;
    this.sunset1 = sunset1;
    this.preciptype1 = preciptype1;
    this.day1 = day1;
  }

  // Getters
  public static String getDate1(Weather weather){
    return weather.day1;
  }

  public static double getTemp1(Weather weather){
    return weather.temp1;
  }

  public static double getFeelslike1(Weather weather){
    return weather.feelslike1;
  }

  public static double getPrecip1(Weather weather){
    return weather.precip1;
  }

  public static double getPrecipprob1(Weather weather){
    return weather.precipprob1;
  }

  public static String[] getPreciptype1(Weather weather){
    String[] result = new String[weather.preciptype1.length];
    for(int i = 0; i < weather.preciptype1.length; i++){
      result[i] = weather.preciptype1[i];
    }
    return result;
  }

  public static String getSunrise1(Weather weather){
    return weather.sunrise1;
  }

  public static String getSunset1(Weather weather){
    return weather.sunset1;
  }

}

//"cloudcover":81.4,"visibility":12.6,"solarradiation":16.6,"solarenergy":0.6,"uvindex":0.0,"severerisk":10.0,"sunrise":"05:29:47","sunriseEpoch":1638912587,"sunset":"17:32:04","sunsetEpoch":1638955924,"moonphase":0.12,"conditions":"Partially cloudy","description":"Partly cloudy throughout the day.","icon":"partly-cloudy-day","stations":["WAMM"],"source":"comb"
