/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marys
 */
package project;
import java.net.*;
import java.util.Base64;
import java.io.*;


public class SmsSender {
     public static void sms(String to,String message)
     {
         try
         {
             String myURI = "https://api.bulksms.com/v1/messages";
             String myUsername = "cms2024";
             String myPassword = "Complaint24";
             String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";
             URL url = new URL(myURI);
             HttpURLConnection request = (HttpURLConnection) url.openConnection();
             request.setDoOutput(true);
             String authStr = myUsername + ":" + myPassword;
             String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
             request.setRequestProperty("Authorization", "Basic " + authEncoded);
             request.setRequestMethod("POST");
             request.setRequestProperty( "Content-Type", "application/json");
             OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
             out.write(myData);
             out.close();
             try {
                 InputStream response = request.getInputStream();
                 BufferedReader in = new BufferedReader(new InputStreamReader(response));
                 String replyText;
                 while ((replyText = in.readLine()) != null) {
                     System.out.println(replyText);
                 }
                 in.close();
             }
             catch (IOException ex)
             {
                 System.out.println("An error occurred:" + ex.getMessage());
                 BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
                 String replyText;
                 while ((replyText = in.readLine()) != null) {
                     System.out.println(replyText);
                 }
                 in.close();
             }
             request.disconnect();
         }
         catch(Exception e)
         {
             System.out.println(e);  
         }
     }
    /* public static void main(String[] args)
     {
         String message="An job has been assigned to your account.\n Kindly resolve and verfiy it.";
         SmsSender.sms("+919500682355",message);
     }*/
}
    
// String myUsername = "netbeanssms2025";
//             String myPassword = "Netbeans2025";