package by.julia.bankservice.entity;

import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

import java.util.Date;

/**
 * Created by julia on 03.04.2016.
 */
public class ClientAdditionalInformation extends Entity {
    private int id;
    private String placeOfBirth;
    private String city;
    private String address;
    private String homePhone;
    private String mobilePhone;
    private String email;
    private String job;
    private String position;
    private String familyStatus;
    private String nationality;
    private String disability;
    private boolean pensioner;
    private String income;
    private boolean reservist;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }public String getPosition() {return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public boolean isPensioner() {
        return pensioner;
    }

    public void setPensioner(boolean pensioner) {
        this.pensioner = pensioner;
    }

    public boolean isReservist() {
        return reservist;
    }

    public void setReservist(boolean reservist) {
        this.reservist = reservist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAdditionalInformation)) return false;

        ClientAdditionalInformation that = (ClientAdditionalInformation) o;

        if (id != that.id) return false;
        if (pensioner != that.pensioner) return false;
        if (reservist != that.reservist) return false;
        if (!placeOfBirth.equals(that.placeOfBirth)) return false;
        if (!city.equals(that.city)) return false;
        if (!address.equals(that.address)) return false;
        if (!homePhone.equals(that.homePhone)) return false;
        if (!mobilePhone.equals(that.mobilePhone)) return false;
        if (!email.equals(that.email)) return false;
        if (!job.equals(that.job)) return false;
        if (!position.equals(that.position)) return false;
        if (!familyStatus.equals(that.familyStatus)) return false;
        if (!nationality.equals(that.nationality)) return false;
        if (!disability.equals(that.disability)) return false;
        return income.equals(that.income);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + placeOfBirth.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + homePhone.hashCode();
        result = 31 * result + mobilePhone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + job.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + familyStatus.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + disability.hashCode();
        result = 31 * result + (pensioner ? 1 : 0);
        result = 31 * result + income.hashCode();
        result = 31 * result + (reservist ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientAdditionalInformation{" +
                "idInformation=" + id +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", job='" + job + '\'' +
                ", position='" + position + '\'' +
                ", familyStatus='" + familyStatus + '\'' +
                ", nationality='" + nationality + '\'' +
                ", disability='" + disability + '\'' +
                ", pensioner=" + pensioner +
                ", income='" + income + '\'' +
                ", isReservist=" + reservist +
                '}';
    }


    @Override
    public DBProperty getDbProperty() {
        DBProperty dbProperty = new DBProperty();
        dbProperty.setClassName(getClass());
        dbProperty.setNameTable("client_additional_inf");
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_CLIENT_INF", Integer.TYPE));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("placeOfBirth", "PLACE_OF_BIRTH", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("city", "CITY", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("address", "ADDRESS", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("homePhone", "HOME_PHONE", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("email", "EMAIL", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("job", "JOB", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("position", "POSITION", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("familyStatus", "FAMILYSTATUS", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("nationality", "NATIONALITY", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("disability", "DISABILITY", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("mobilePhone", "MOBILE_PHONE", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("pensioner", "PENSIONER", boolean.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("income", "INCOME", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("reservist", "RESERVIST", boolean.class));
        return dbProperty;
    }
}
