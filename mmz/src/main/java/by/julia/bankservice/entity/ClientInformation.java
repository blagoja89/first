package by.julia.bankservice.entity;

import by.julia.bankservice.dbutil.property.DBManyToOnePropertyItem;
import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

import java.util.Date;

/**
 * Created by julia on 04.05.2016.
 */
public class ClientInformation extends Entity {
    private int id;
    private Date birthday;
    private PassportData passportData;
    private ClientAdditionalInformation clientAdditionalInformation;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public ClientAdditionalInformation getClientAdditionalInformation() {
        return clientAdditionalInformation;
    }

    public void setClientAdditionalInformation(ClientAdditionalInformation clientAdditionalInformation) {
        this.clientAdditionalInformation = clientAdditionalInformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientInformation)) return false;

        ClientInformation that = (ClientInformation) o;

        if (id != that.id) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (passportData != null ? !passportData.equals(that.passportData) : that.passportData != null) return false;
        return !(clientAdditionalInformation != null ? !clientAdditionalInformation.equals(that.clientAdditionalInformation) : that.clientAdditionalInformation != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (passportData != null ? passportData.hashCode() : 0);
        result = 31 * result + (clientAdditionalInformation != null ? clientAdditionalInformation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClientInformation{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", passportData=" + passportData +
                ", clientAdditionalInformation=" + clientAdditionalInformation +
                '}';
    }

    @Override
    public DBProperty getDbProperty() {
        DBProperty dbProperty = new DBProperty();
        dbProperty.setClassName(getClass());
        dbProperty.setNameTable("client_information");
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_CLIENT_INFORMATION", Integer.TYPE));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("birthday", "BIRTHDAY", Date.class));
        dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("passportData", "PASSPORT_ID",
                PassportData.class,"id", "ID_PASSPORT", Integer.TYPE, false));
        dbProperty.getDbManyToOnePropertyItems().add(new DBManyToOnePropertyItem("clientAdditionalInformation",
                "CLIENT_ADDITIONAL_INF_ID", ClientAdditionalInformation.class,"id", "ID_CLIENT_INF", Integer.TYPE, false));
        return dbProperty;
    }
}
