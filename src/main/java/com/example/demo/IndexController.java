package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class IndexController {

    @GetMapping("/")
    public String homePage(Model model) {

        List <Fish> fishList = GetFish.getAllFishInView("12.345","1.56788");

        Fish[] fishArray = new Fish[fishList.size()];
        fishList.toArray(fishArray);  //fill the array

        model.addAttribute("fishlist", fishArray);

        return "index.html";
    }

    @GetMapping("/getWeather")
    @ResponseBody
    public String testme(Model model, @RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude, RedirectAttributes attrs){

       Weather weatherData = null;
       String[] weatherDataOut = new String[8];
       String res = "";

       System.out.println("Recieved a click here on latitude: " + latitude + " and longitude: " + longitude);

       // Lav dine test her min dude.
       try{
         weatherData = CallWeatherAPI.getWeather(latitude + "," + longitude);

         weatherDataOut[0] = Weather.getDate1(weatherData);

         weatherDataOut[1] = "" + Weather.getTemp1(weatherData);

         weatherDataOut[2] = "" + Weather.getFeelslike1(weatherData);

         weatherDataOut[3] = "" + Weather.getPrecip1(weatherData);

         weatherDataOut[4] = "" + Weather.getPrecipprob1(weatherData);

         if(Weather.getPreciptype1(weatherData).length != 0){
          res = Weather.getPreciptype1(weatherData)[0];
          for(int i = 1; i < Weather.getPreciptype1(weatherData).length; i++){
            res = res + "," + Weather.getPreciptype1(weatherData)[i];
          }
         }
         else {
           res = "";
         }
         weatherDataOut[5] = res;

         weatherDataOut[6] = Weather.getSunset1(weatherData);

         weatherDataOut[7] = Weather.getSunrise1(weatherData);

       } catch (Exception e){
         System.out.println("Error: " + e);
       }

      String medHtml = "<div class='wrapperDiv'>"+
                       "<h3>Weather:</h3>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Date: </h4>" + weatherDataOut[0] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Temperature: </h4>" + weatherDataOut[1] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Feels like: </h4>" + weatherDataOut[2] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Precipitation: </h4>" + weatherDataOut[3] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Precip Prob: </h4>" + weatherDataOut[4] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Precip Type: </h4>" + weatherDataOut[5] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Sunset: </h4>" + weatherDataOut[6] + "</div>" +
                       "<div class='dataLine dataLineHeader'><h4 style='display:inline'>Sunrise: </h4>" + weatherDataOut[7] + "</div>" +
                       "</div>";

      if (weatherDataOut[0] != null){
        return medHtml;
      } else {
        return "<h3>Weather:</h3><div>Unable to fetch weather data for this location</div>";
      }
    }

}
