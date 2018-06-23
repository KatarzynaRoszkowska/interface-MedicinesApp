package pl.roszkowska.med.api.producers;

/**
 * A class that has all fields from the Medicines table from the database. It also provides setters and getters
 */


public class Producers {
    String id;
    String producerName;
    String country;
    String town;
    String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Producers{" +
                "id='" + id + '\'' +
                ", producerName='" + producerName + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}