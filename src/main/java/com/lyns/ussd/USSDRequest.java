
package com.lyns.ussd;

public class USSDRequest {
private String UserID;
private String Msisdn;
private  boolean NewSession;

    public boolean IsNewSession() {
        return NewSession;
    }

    public void setNewSession(boolean isNewSession) {
        this.NewSession = isNewSession;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setMsisdn(String Msisdn) {
        this.Msisdn = Msisdn;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getUserData() {
        return UserData;
    }

    public void setUserData(String UserData) {
        this.UserData = UserData;
    }

    public boolean getSessionID() {
        return SessionID;
    }

    public void setSessionID(boolean SessionID) {
        this.SessionID = SessionID;
    }
   private String UserData;
   private boolean  SessionID;
   
}
