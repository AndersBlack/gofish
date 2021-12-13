package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GetFish{

  public static List<Fish> getAllFishInView(String lat, String longitude){
    List<Fish> fishList = new ArrayList<Fish>();
    fishList = readFishFromCSV("fish.csv");

    return fishList;
  }

  public static List<Fish> readFishFromCSV(String fileName){
      List<Fish> fishes = new ArrayList<Fish>();
      Path pathToFile = Paths.get(fileName);

      try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

        String line = br.readLine();

        while (line != null) {
            String[] attributes = line.split(",");
            Fish fish = createFish(attributes);



            fishes.add(fish);
            line = br.readLine();
        }
      } catch (IOException ioe) {
          ioe.printStackTrace();
      }

      return fishes;
  }

  private static Fish createFish(String[] metadata) {
        String name = metadata[0];
        String country = metadata[1];
        String area = metadata[2];
        String habitat = metadata[3];
        String waterType = metadata[4];
        String depth = metadata[5];
        String abundance = metadata[6];
        String latitude = metadata[7];
        String longitude = metadata[8];

        return new Fish(name, country, area, habitat, waterType, depth, abundance, latitude, longitude);
  }
}

class Fish{
  public String name;
  public String country;
  public String area;
  public String habitat;
  public String waterType;
  public String depth;
  public String abundance;
  public String latitude;
  public String longitude;

  public Fish(String name, String country, String area, String habitat, String waterType, String depth, String abundance, String latitude, String longitude){
    this.name = name;
    this.country = country;
    this.area = area;
    this.habitat = habitat;
    this.waterType = waterType;
    this.depth = depth;
    this.abundance = abundance;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getLat(){ return this.latitude; }
  public String getLong(){ return this.longitude; }
}
