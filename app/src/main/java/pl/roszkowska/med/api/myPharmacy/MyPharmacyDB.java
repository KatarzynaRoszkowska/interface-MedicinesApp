package pl.roszkowska.med.api.myPharmacy;

import pl.roszkowska.med.api.medicines.Medicines;

/**
 * A class that has all fields from the MyPharmacy table from the database. It also provides setters and getters
 */


public class MyPharmacyDB {

    String id;
    String isTaken;
    String howMany;
    String expirationData;
    Medicines medicines;
    String nazwaLeku;

    /**
     *
     * @return the object Medicines
     */
    public Medicines getMedicines() {
        return medicines;
    }

    /**
     *
     * @return the id of the drug in myPharmacy
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id set the id of the drug in myPharmacy
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return the message if the drug is taken or not
     */
    public String getIsTaken() {
        return isTaken;
    }

    /**
     *
     * @param isTaken set the message if the drug is taken or not
     */
    public void setIsTaken(String isTaken) {
        this.isTaken = isTaken;
    }

    /**
     *
     * @return the number of capsules / tablets
     */
    public String getHowMany() {
        return howMany;
    }

    /**
     *
     * @param howMany set the number of capsules / tablets
     */
    public void setHowMany(String howMany) {
        this.howMany = howMany;
    }

    /**
     *
     * @return the expiration date
     */
    public String getExpirationData() {
        return expirationData;
    }

    /**
     *
     * @param expirationData set the expiration date
     */
    public void setExpirationData(String expirationData) {
        this.expirationData = expirationData;
    }

    /**
     * The class constructor that accepts four parameters
     * @param isTaken
     * @param howMany
     * @param expirationData
     * @param nazwaLeku
     */
    public MyPharmacyDB(String isTaken, String howMany, String expirationData, String nazwaLeku) {
        this.isTaken = isTaken;
        this.howMany = howMany;
        this.expirationData = expirationData;
        this.nazwaLeku = nazwaLeku;
    }

    /**
     *
     * @return the override method toString customized to this class parameters
     */
    @Override
    public String toString() {
        return "MyPharmacyDB{" +

                "id='" + id + '\'' +
                ", isTaken='" + isTaken + '\'' +
                ", howMany='" + howMany + '\'' +
                ", expirationData='" + expirationData + '\'' +
                '}';
    }

    /**
     * The class constructor that accepts four parameters
     * @param isTaken
     * @param howMany
     * @param expirationData
     * @param medicines
     */
    public MyPharmacyDB(String isTaken, String howMany, String expirationData, Medicines medicines) {
        this.isTaken = isTaken;
        this.howMany = howMany;
        this.expirationData = expirationData;
        this.medicines = medicines;
    }
}
