package com.lyns.ussd;
import com.google.gson.Gson;

import static spark.Spark.port;
import static spark.Spark.post;

public class USSD {
    public  void  ussdMessge() {
      Gson gson = new Gson();
      // Start embedded server at this port
      port(8080);

      // POST - ENDPOINT URL PROVIDED DURING SETUP
      post("/endpointURL", (request, response) -> {
      USSDRequest ussdRequest = gson.fromJson(request.body(), USSDRequest.class);
      USSDResponse ussdResponse = new USSDResponse();
      ussdResponse.setSessionID(ussdRequest.getSessionID());
      ussdResponse.setUserID(ussdRequest.getUserID());
      ussdResponse.setMsisdn(ussdRequest.getMsisdn());

      if (ussdRequest.IsNewSession() && ussdRequest.getUserData().equals("*928*92#")) {
        ussdResponse.setMessage("Welcome to"
                + " Arkesel Bank\n1. Check Balance\n2. Buy Bundle");
        ussdResponse.setContinueSession(true);
       }else if ( !ussdRequest.IsNewSession()&& ussdRequest.getUserData().equals("1")) {
        // Implement Check balance functionality for 1
        ussdResponse.setMessage("Your account balance is GHc 2.00");
        ussdResponse.setContinueSession(false);
      }else if (!ussdRequest.IsNewSession() && ussdRequest.getUserData().equals("2")){
        // Implement Check balance functionality for 2
        ussdResponse.setMessage("No packages available for subscription");
        ussdResponse.setContinueSession(false);
      }else {
        ussdResponse.setMessage("Invalid input");
        ussdResponse.setContinueSession(false);
      }
        response.header("Content-Type","application/json");
        return gson.toJson(ussdResponse);
      });
     }
    }
// DEPENDENCIES
  