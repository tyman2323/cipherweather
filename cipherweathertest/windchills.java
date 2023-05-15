import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
public class windchills{
   private ArrayList<String> dates = new ArrayList<String>();
   private ArrayList<Integer> drybulb = new ArrayList<Integer>();
   private String date = "";
   private String path;
   public windchills(String a, String b){
      path = a;
      date = b;
      windy();
   }
   public void windy(){
      SimpleDateFormat inputdate = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat outputdate = new SimpleDateFormat("M-d-yy");
      try{
         Date olddate = inputdate.parse(date);
         date = outputdate.format(olddate);
      }
      catch(Exception e){ 
         System.out.println("Invalid date, please follow, YYYY-MM-dd");
         System.exit(0);
      }
      date = date.replace('-','/');
      try{
         
         BufferedReader br = new BufferedReader(new FileReader(path));
         String line = "";
         while((line = br.readLine())!=null){
            String[] col = line.split(",");
            Pattern pattern = Pattern.compile(date);
            Matcher matcher = pattern.matcher(col[5]);
            if(matcher.find()){
               dates.add(col[17]);
               try{
                  drybulb.add(Integer.parseInt(col[10]));
               }
            catch(Exception e){
            String temp = col[10];
            temp = temp.substring(0,temp.length()-1);
            drybulb.add(Integer.parseInt(temp));
            }
            }
         }
      }
      catch(Exception e){
         System.out.println("File not found");
      }
      int counter = 0;
      for(int x = 0; x<drybulb.size();x++){
         double t = drybulb.get(x);
         double s = Integer.parseInt(dates.get(x));
         double chill = 35.74 + (0.6215 * t) - (35.75 * Math.pow(s, 0.16)) + (0.4275 * t * Math.pow(s, 0.16));
         int chilly = (int)Math.round(chill);
         drybulb.set(counter,chilly);
      }
      for(int x = 0; x<drybulb.size();x++){
         int temp = drybulb.get(x);
         if(temp>40){
            drybulb.remove(x);
            x--;
         }
      }
      drybulb.trimToSize();
      for(int x = 0; x<drybulb.size();x++){
         System.out.println(drybulb.get(x));
      }
   }
}