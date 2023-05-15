import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class daylight_temp{
   private ArrayList<String> dates = new ArrayList<String>();
   private ArrayList<Integer> drybulb = new ArrayList<Integer>();
   private int sunrise = 0;
   private int sunset = 0;
   private String date = "";
   private String path = "";
   public daylight_temp(String a, String b){
      path = a;
      date = b;
      tempy();
   }
   private void tempy(){
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
      daylight_temp();
   }
   public double[] daylight_temp(){
   try{
         BufferedReader br = new BufferedReader(new FileReader(path));
         String line = "";
         while((line = br.readLine())!=null){
            String[] col = line.split(",");
            Pattern pattern = Pattern.compile(date);
            Matcher matcher = pattern.matcher(col[5]);
            if(matcher.find()){
               dates.add(col[5]);
               try{
                  drybulb.add(Integer.parseInt(col[10]));
               }
            catch(Exception d){
            String temp = col[10];
            temp = temp.substring(0,temp.length()-1);
            drybulb.add(Integer.parseInt(temp));
            }
               sunrise = Integer.parseInt(col[35]);
               sunset = Integer.parseInt(col[36]);
            }
         }
      }
      catch(Exception e){
         System.out.println("File not found");
      }
      while(true){
         String temp = dates.get(0);
         int t = temp.indexOf(" ");
         temp = temp.substring(t+1);
         temp = temp.replace(":","");
         t = Integer.parseInt(temp);
         if(t<sunrise){
            dates.remove(0);
            drybulb.remove(0);
         }
         else{
            break;
         }
      }
      dates.trimToSize();
      drybulb.trimToSize();
      while(true){
         String temp = dates.get(dates.size()-1);
         int t = temp.indexOf(" ");
         temp = temp.substring(t+1);
         temp = temp.replace(":","");
         t = Integer.parseInt(temp);
         if(t>sunset){
            dates.remove(dates.size()-1);
            drybulb.remove(drybulb.size()-1);
         }
         else{
            break;
         }
      }
      dates.trimToSize();
      drybulb.trimToSize();
      double sum = 0;
      for(int x =0; x<drybulb.size();x++){
         sum = sum + drybulb.get(x);
      }
      double mean = sum/drybulb.size();
      System.out.println(mean);
      double standev = 0.0;
      for(int x =0; x<drybulb.size();x++){
         standev= standev+ Math.pow(drybulb.get(x)-mean,2);
      }
      standev = Math.sqrt(standev/drybulb.size());
      System.out.println(standev);
      double[] data = {mean,standev};
      return data;
   }
}