package pl.roszkowska.med.api.medicines;

/**
 * A class that has all fields from the Medicines table from the database. It also provides setters and getters
 */
public class Medicines {
    Long id;
    String medicinesName;
    String composition;
    String formOfTheDrag;
    String category;
    String speciality;
    String activity;
    String indications;
    String wayOfGiving;
    String possibleSideEffect;
    String tellTheDoctor;
    String dose;
    String quantity;
    String isPrescription;
    String ean;
    String serialNumber;

    /**
     * Medicines class constructor. It adopts four parameters.
     * @param medicinesName is name of the drug
     * @param speciality for what purpose the medicine is used
     * @param composition is composition of the drug
     * @param formOfTheDrag in which form the given drug is present
     */
    public Medicines(String medicinesName, String speciality, String composition, String formOfTheDrag) {
        this.medicinesName = medicinesName;
        this.speciality = speciality;
        this.composition = composition;
        this.formOfTheDrag = formOfTheDrag;
    }

    public Medicines() {

    }

    /**
     *
     * @return medicine id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id set the id of drug
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the name of medicine
     */
    public String getMedicinesName() {
        return medicinesName;
    }

    /**
     *
     * @param medicinesName set the name of medicine
     */
    public void setMedicinesName(String medicinesName) {
        this.medicinesName = medicinesName;
    }

    /**
     *
     * @return composition of the drug
     */
    public String getComposition() {
        return composition;
    }

    /**
     *
     * @param composition set the composition of the drug
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     *
     * @return in which form the given drug is present
     */
    public String getFormOfTheDrag() {
        return formOfTheDrag;
    }

    /**
     *
     * @param formOfTheDrag set in which form the given drug is present
     */
    public void setFormOfTheDrag(String formOfTheDrag) {
        this.formOfTheDrag = formOfTheDrag;
    }

    /**
     *
     * @return the category of the drug
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category set the category of the drug
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return the speciality of the drug
     */
    public String getSpeciality() {
        return speciality;
    }

    /**
     *
     * @param speciality set the category of the drug
     */
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     *
     * @return the activity of the drug
     */
    public String getActivity() {
        return activity;
    }

    /**
     *
     * @param activity set the activity of the drug
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     *
     * @return the symptoms in which the medicine should be used
     */
    public String getIndications() {
        return indications;
    }

    /**
     *
     * @param indications set the symptoms in which the medicine should be used
     */
    public void setIndications(String indications) {
        this.indications = indications;
    }

    /**
     *
     * @return way of taking the drug
     */
    public String getWayOfGiving() {
        return wayOfGiving;
    }

    /**
     *
     * @param wayOfGiving set way of taking the drug
     */
    public void setWayOfGiving(String wayOfGiving) {
        this.wayOfGiving = wayOfGiving;
    }

    /**
     *
     * @return the possible side effect
     */
    public String getPossibleSideEffect() {
        return possibleSideEffect;
    }

    /**
     *
     * @param possibleSideEffect set the possible side effect
     */
    public void setPossibleSideEffect(String possibleSideEffect) {
        this.possibleSideEffect = possibleSideEffect;
    }

    /**
     *
     * @return a message if you should contact your doctor
     */
    public String getTellTheDoctor() {
        return tellTheDoctor;
    }

    /**
     *
     * @param tellTheDoctor set a message if you should contact your doctor
     */
    public void setTellTheDoctor(String tellTheDoctor) {
        this.tellTheDoctor = tellTheDoctor;
    }

    /**
     *
     * @return the dose of the drug
     */
    public String getDose() {
        return dose;
    }

    /**
     *
     * @param dose set the dose of the drug
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     *
     * @return the quantity od the drug
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity set the quantity od the drug
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return the message if the drug is prescription
     */
    public String getIsPrescription() {
        return isPrescription;
    }

    /**
     *
     * @param isPrescription set a message if the drug is prescription
     */
    public void setIsPrescription(String isPrescription) {
        this.isPrescription = isPrescription;
    }

    /**
     *
     * @return the EAN code
     */
    public String getEan() {
        return ean;
    }

    /**
     *
     * @param ean set the EAN code
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     *
     * @return the serial number of the drug
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber set the serial number of the drug
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return  method override toString() with custom string
     */
    @Override
    public String toString() {
        return "Medicines{" +
                "id='" + id + '\'' +
                ", medicinesName='" + medicinesName + '\'' +
                ", composition='" + composition + '\'' +
                ", formOfTheDrag='" + formOfTheDrag + '\'' +
                ", category='" + category + '\'' +
                ", speciality='" + speciality + '\'' +
                ", activity='" + activity + '\'' +
                ", indications='" + indications + '\'' +
                ", wayOfGiving='" + wayOfGiving + '\'' +
                ", possibleSideEffect='" + possibleSideEffect + '\'' +
                ", tellTheDoctor='" + tellTheDoctor + '\'' +
                ", dose='" + dose + '\'' +
                ", quantity='" + quantity + '\'' +
                ", isPrescription='" + isPrescription + '\'' +
                ", ean='" + ean + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
