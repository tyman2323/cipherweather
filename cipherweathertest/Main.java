//import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.HashMap;
public class Main{
      public static ArrayList<String> dates = new ArrayList<String>();
      public static ArrayList<String> dates2 = new ArrayList<String>();
      public static ArrayList<Integer> drybulb = new ArrayList<Integer>();
      public static HashMap<String,Integer> set1 = new HashMap<String,Integer>();
      public static HashMap<String,Integer> set2 = new HashMap<String,Integer>();
      public static int sunrise;
      public static int sunset;
   public static void main(String[] args){
      Scanner inputer = new Scanner(System.in);
      String command = inputer.nextLine();
      String[] input = command.split(" ");

      if(input.length > 3){
         System.out.println("Too many arguments for given command");
      }
      else if(input[0].equals("daylight_temp")){
         daylight_temp daylight = new daylight_temp(input[1],input[2]);
      } 
      else if(input[0].equals("windchills")){
         windchills wind = new windchills(input[1],input[2]);
      }
      else if(input[0].equals("similar-day")){
         similar_day sim = new similar_day(input[1],input[2]);
      } 
//C:\Users\Ayman\Documents\CIPHER-weather-test\CIPHER-weather-test\1089441.csv
//C:\Users\Ayman\Documents\CIPHER-weather-test\CIPHER-weather-test\1089419.csv
   }
}


























