package pl.roszkowska.med.api.myPharmacy;

import java.util.Date;

public class MyPharmacyDB {

    String id;
    String isTaken;
    String howMany;
    String expirationData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    public String getHowMany() {
        return howMany;
    }

    public void setHowMany(String howMany) {
        this.howMany = howMany;
    }

    public String getExpirationData() {
        return expirationData;
    }

    public void setExpirationData(String expirationData) {
        this.expirationData = expirationData;
    }

    @Override
    public String toString() {
        return "MyPharmacyDB{" +
                "id='" + id + '\'' +
                ", isTaken='" + isTaken + '\'' +
                ", howMany='" + howMany + '\'' +
                ", expirationData='" + expirationData + '\'' +
                '}';
    }
}
