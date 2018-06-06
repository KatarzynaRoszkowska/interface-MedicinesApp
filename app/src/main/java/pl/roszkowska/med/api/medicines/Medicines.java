package pl.roszkowska.med.api.medicines;

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

    public Medicines(String medicinesName, String speciality, String composition, String formOfTheDrag) {
        this.medicinesName = medicinesName;
        this.speciality = speciality;
        this.composition = composition;
        this.formOfTheDrag = formOfTheDrag;
    }

    public Medicines() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicinesName() {
        return medicinesName;
    }

    public void setMedicinesName(String medicinesName) {
        this.medicinesName = medicinesName;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getFormOfTheDrag() {
        return formOfTheDrag;
    }

    public void setFormOfTheDrag(String formOfTheDrag) {
        this.formOfTheDrag = formOfTheDrag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getWayOfGiving() {
        return wayOfGiving;
    }

    public void setWayOfGiving(String wayOfGiving) {
        this.wayOfGiving = wayOfGiving;
    }

    public String getPossibleSideEffect() {
        return possibleSideEffect;
    }

    public void setPossibleSideEffect(String possibleSideEffect) {
        this.possibleSideEffect = possibleSideEffect;
    }

    public String getTellTheDoctor() {
        return tellTheDoctor;
    }

    public void setTellTheDoctor(String tellTheDoctor) {
        this.tellTheDoctor = tellTheDoctor;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIsPrescription() {
        return isPrescription;
    }

    public void setIsPrescription(String isPrescription) {
        this.isPrescription = isPrescription;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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
