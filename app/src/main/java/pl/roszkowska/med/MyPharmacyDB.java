package pl.roszkowska.med;

import java.util.Date;

public class MyPharmacyDB {

    String name;
    boolean isTaken;
    String validateDate;
    int howMany;

    public MyPharmacyDB(String name,boolean isTaken, String validateDate, int howMany) {
        this.name = name;
        this.isTaken = isTaken;
        this.validateDate = validateDate;
        this.howMany = howMany;
    }

    public String getName() {
        return name;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public int getHowMany() {
        return howMany;
    }
}
