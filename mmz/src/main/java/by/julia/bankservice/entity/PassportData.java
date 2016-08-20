package by.julia.bankservice.entity;

import by.julia.bankservice.dbutil.property.DBProperty;
import by.julia.bankservice.dbutil.property.DBSimplePropertyItem;

import java.util.Date;

/**
 * Created by julia on 03.04.2016.
 */
public class PassportData extends Entity {
    private int id;
    private String series;
    private String no;
    private String placeOfIssue;
    private Date dateOfIssue;
    private String identificationNo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIdentificationNo() {
        return identificationNo;
    }

    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassportData)) return false;

        PassportData that = (PassportData) o;

        if (id != that.id) return false;
        if (!series.equals(that.series)) return false;
        if (!no.equals(that.no)) return false;
        if (!placeOfIssue.equals(that.placeOfIssue)) return false;
        if (!dateOfIssue.equals(that.dateOfIssue)) return false;
        return identificationNo.equals(that.identificationNo);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + series.hashCode();
        result = 31 * result + no.hashCode();
        result = 31 * result + placeOfIssue.hashCode();
        result = 31 * result + dateOfIssue.hashCode();
        result = 31 * result + identificationNo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PassportData{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", no='" + no + '\'' +
                ", placeOfIssue='" + placeOfIssue + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", identificationNo='" + identificationNo + '\'' +
                '}';
    }

    @Override
    public DBProperty getDbProperty() {
        DBProperty dbProperty = new DBProperty();
        dbProperty.setClassName(getClass());
        dbProperty.setNameTable("passport");
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("id", "ID_PASSPORT", Integer.TYPE));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("series", "SERIES", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("no", "PASSNO", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("placeOfIssue", "PLACE_OF_ISSUE", String.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("dateOfIssue", "DATE_OF_ISSUE", Date.class));
        dbProperty.getDbSimplePropertyItems().add(new DBSimplePropertyItem("identificationNo", "IDENTIFICATION_NO", String.class));
        return dbProperty;
    }
}