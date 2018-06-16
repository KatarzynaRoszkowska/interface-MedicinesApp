package pl.roszkowska.med.api.myPharmacy;

import pl.roszkowska.med.api.medicines.Medicines;

// A class that has all fields from the MyPharmacy table from the database. It also provides setters and getters

public class MyPharmacyDB {

    String id;
    String isTaken;
    String howMany;
    String expirationData;
    Medicines medicines;
    String nazwaLeku;

    public String getNazwaLeku() {
        return nazwaLeku;
    }

    public Medicines getMedicines() {
        return medicines;
    }


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

    public MyPharmacyDB(String isTaken, String howMany, String expirationData, String nazwaLeku) {
        this.isTaken = isTaken;
        this.howMany = howMany;
        this.expirationData = expirationData;
        this.nazwaLeku = nazwaLeku;
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

    public MyPharmacyDB(String isTaken, String howMany, String expirationData, Medicines medicines) {
        this.isTaken = isTaken;
        this.howMany = howMany;
        this.expirationData = expirationData;
        this.medicines = medicines;
    }
}
