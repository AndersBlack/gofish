package com.example.demo;

import java.time.ZonedDateTime;

public class Weather{

  private double temp1;
  private double temp2;
  private double feelslike1;
  private double feelslike2;
  private double precip1;
  private double precip2;
  private double precipprob1;
  private double precipprob2;
  private String sunrise1;
  private String sunrise2;
  private String sunset1;
  private String sunset2;
  private String[] preciptype1;
  private String[] preciptype2;
  private String day1;
  private String day2;

  public Weather(){
    this.temp1 = 0;
    this.temp2 = 0;
    this.feelslike1 = 0;
    this.feelslike2 = 0;
    this.precip1 = 0;
    this.precip2 = 0;
    this.precipprob1 = 0;
    this.precipprob2 = 0;
    this.sunrise1 = null;
    this.sunrise2 = null;
    this.sunset1 = null;
    this.sunset2 = null;
    this.preciptype1 = null;
    this.preciptype2 = null;
    this.day1 = null;
    this.day2 = null;
  }

  //Constructor for a weather object with weather info
  public Weather(double temp1, double temp2, double feelslike1, double feelslike2, double precip1, double precip2, double precipprob1, double precipprob2, String sunrise1, String sunrise2, String sunset1, String sunset2, String[] preciptype1, String[] preciptype2, String day1, String day2){
    this.temp1 = temp1;
    this.temp2 = temp2;
    this.feelslike1 = feelslike1;
    this.feelslike2 = feelslike2;
    this.precip1 = precip1;
    this.precip2 = precip2;
    this.precipprob1 = precipprob1;
    this.precipprob2 = precipprob2;
    this.sunrise1 = sunrise1;
    this.sunrise2 = sunrise2;
    this.sunset1 = sunset1;
    this.sunset2 = sunset2;
    this.preciptype1 = preciptype1;
    this.preciptype2 = preciptype2;
    this.day1 = day1;
    this.day2 = day2;
  }

  // Getters
  public static String getDate1(Weather weather){
    return weather.day1;
  }

  public static String getDate2(Weather weather){
    return weather.day2;
  }

  public static double getTemp1(Weather weather){
    return weather.temp1;
  }

  public static double getTemp2(Weather weather){
    return weather.temp2;
  }

  public static double getFeelslike1(Weather weather){
    return weather.feelslike1;
  }

  public static double getFeelslike2(Weather weather){
    return weather.feelslike2;
  }

  public static double getPrecip1(Weather weather){
    return weather.precip1;
  }

  public static double getPrecip2(Weather weather){
    return weather.precip2;
  }

  public static double getPrecipprob1(Weather weather){
    return weather.precipprob1;
  }

  public static double getPrecipprob2(Weather weather){
    return weather.precipprob2;
  }

  public static String[] getPreciptype1(Weather weather){
    String[] result = new String[weather.preciptype1.length];
    for(int i = 0; i < weather.preciptype1.length; i++){
      result[i] = weather.preciptype1[i];
    }
    return result;
  }

  public static String getSunrise1(){
    return weather.sunrise1;
  }

  public static String getSunrise2(){
    return weather.sunrise2;
  }

  public static String getSunset1(){
    return weather.sunset1;
  }

  public static String getSunset2(){
    return weather.sunset2;
  }

  public static String[] getPreciptype2(Weather weather){
    String[] result = new String[weather.preciptype2.length];
    for(int i = 0; i < weather.preciptype2.length; i++){
      result[i] = weather.preciptype2[i];
    }
    return result;
  }
}

//"cloudcover":81.4,"visibility":12.6,"solarradiation":16.6,"solarenergy":0.6,"uvindex":0.0,"severerisk":10.0,"sunrise":"05:29:47","sunriseEpoch":1638912587,"sunset":"17:32:04","sunsetEpoch":1638955924,"moonphase":0.12,"conditions":"Partially cloudy","description":"Partly cloudy throughout the day.","icon":"partly-cloudy-day","stations":["WAMM"],"source":"comb"
