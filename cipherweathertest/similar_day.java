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
public class similar_day{
      private  ArrayList<String> dates = new ArrayList<String>();
      private  ArrayList<String> dates2 = new ArrayList<String>();
      private ArrayList<Integer> drybulb = new ArrayList<Integer>();
      private  HashMap<String,Integer> set1 = new HashMap<String,Integer>();
      private  HashMap<String,Integer> set2 = new HashMap<String,Integer>();
      private String path1 = "";
      private String path2 = "";
      public similar_day(String a, String b){
      path1 = a;
      path2 = b;
      similar();
      }
      public void similar(){
      try{
         BufferedReader br1 = new BufferedReader(new FileReader(path1));
         BufferedReader br2 = new BufferedReader(new FileReader(path2));
         String line = "";
         for(int x = 0; x<2;x++){
            line = br1.readLine();
            String[] col = line.split(",");
            dates.add(col[5]);
            line = br2.readLine();
            col = line.split(",");
            dates2.add(col[5]);
         }
         dates.remove(0);
         dates2.remove(0);
         SimpleDateFormat sdformat = new SimpleDateFormat("M/d/yy");
         String tex = dates.get(0);
         String ga = dates2.get(0);
         dates.clear();
         dates2.clear();
         tex = tex.substring(0,tex.indexOf(" "));
         ga = ga.substring(0,ga.indexOf(" "));
         Date d1 = sdformat.parse(tex);
         Date d2 = sdformat.parse(ga);
         if(d1.compareTo(d2) > 0) {
            while(((line = br1.readLine())!=null)) {
               String[] col = line.split(",");
               dates.add(line);
          }
          while(((line = br2.readLine())!=null)) {
               String[] col = line.split(",");
               String temp = col[5];
               temp = temp.substring(0,temp.indexOf(" "));
               d2 = sdformat.parse(temp);
               if(d2.compareTo(d1)>=0){
                  dates2.add(line);
               }
               
          }
         } 
         
         else if(d1.compareTo(d2) < 0) {
            while(((line = br2.readLine())!=null)) {
               String[] col = line.split(",");
               dates2.add(line);
          }
          while(((line = br1.readLine())!=null)) {
               String[] col = line.split(",");
               String temp = col[5];
               temp = temp.substring(0,temp.indexOf(" "));
               d1 = sdformat.parse(temp);
               if(d1.compareTo(d2)>=0){
                  dates.add(line);
               }
               
          }
         } 
         for(int x = 0; x< dates2.size();x++){
            try{
            int f = 0;
            line = dates2.get(x);
            String[] temp = line.split(",");
            String date = temp[5];
            date = date.substring(0,date.indexOf(" "));
            String report = temp[6];
            if(report.equals("SOD")){
               report = temp[28];
               f = Integer.parseInt(report);
                  report = temp[37];
                  if(report.equals("")){
                     f++;
                  }
                  else{
                     String[] type = report.split(" ");
                     for(int xx = 0; xx<type.length;xx++){
                        f+=Integer.parseInt(type[xx].substring(type[xx].indexOf(":")+1));
                     }
                  }
            }
            set2.put(date,f);
            }
            catch(Exception e){
               System.out.println("Dataset 2 is not formatted properly, make sure there is SOD and the SOD row houses daily data");
               System.exit(0);
            }
         }
         for(int x = 0; x< dates.size();x++){
            try{
            int f = 0;
            line = dates.get(x);
            String[] temp = line.split(",");
            String date = temp[5];
            date = date.substring(0,date.indexOf(" "));
            String report = temp[6];
            if(report.equals("SOD")){
               report = temp[28];
               f = Integer.parseInt(report);
                  report = temp[37];
                  if(report.equals("")){
                     f++;
                  }
                  else{
                     String[] type = report.split(" ");
                     for(int xx = 0; xx<type.length;xx++){
                        f+=Integer.parseInt(type[xx].substring(type[xx].indexOf(":")+1));
                     }
                  }
            }
            set1.put(date,f);
            }
            catch(Exception e){
               System.out.println("Dataset 1 is not formatted properly, make sure there is SOD and the SOD row houses daily data");
               System.exit(0);
            }
         }
         int difference = 8789;
         String winner = "";
         if(set1.size()<set2.size()){
            for (String name: set1.keySet()) {
            String key = name;
            int value1 = set1.get(name);
            int value2 = set2.get(name);
            int differ = value1-value2;
            differ = Math.abs(differ);
            if(differ<difference){
               difference = differ;
               winner = key;
            }
            }
         }
         else{
            for (String name: set2.keySet()) {
            String key = name;
            int value1 = set2.get(name);
            int value2 = set1.get(name);
            int differ = value1-value2;
            differ = Math.abs(differ);
            if(differ<difference){
               difference = differ;
               winner = key;
            }
            }
         }
         
        System.out.println(winner); 
      }
      catch(Exception e){
         System.out.println("File not found");
         System.exit(0);
      }
      }
}