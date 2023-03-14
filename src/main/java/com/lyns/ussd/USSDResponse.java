/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lyns.ussd;

/**
 *
 * @author HP
 */
public class USSDResponse {
  private boolean SessionID;
private String UserID;
private String Msisdn;
private String Message;
private boolean ContinueSession;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isContinueSession() {
        return ContinueSession;
    }

    public void setContinueSession(boolean ContinueSession) {
        this.ContinueSession = ContinueSession;
    }

    public boolean getSessionID() {
        return SessionID;
    }

    public void setSessionID(boolean SessionID) {
        this.SessionID = SessionID;
    }

  

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setMsisdn(String Msisdn) {
        this.Msisdn = Msisdn;
    }

}
