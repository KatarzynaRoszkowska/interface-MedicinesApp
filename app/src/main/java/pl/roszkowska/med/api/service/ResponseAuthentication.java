package pl.roszkowska.med.api.service;

/**
 * This class stores the authorization key
 */
public class ResponseAuthentication {
    String tokenID;
    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }
}
