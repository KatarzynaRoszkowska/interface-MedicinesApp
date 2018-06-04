package pl.roszkowska.med.api.myPharmacy;

import pl.roszkowska.med.api.medicines.Medicines;

public class MyPharmacyDB {

    Long id;
    String isTaken;
    String howMany;
    String expirationData;
    Medicines medicines;

    public Medicines getMedicines() {
        return medicines;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public MyPharmacyDB(String isTaken, String howMany, String expirationData, Medicines medicines) {
        this.isTaken = isTaken;
        this.howMany = howMany;
        this.expirationData = expirationData;
        this.medicines = medicines;
    }
}
