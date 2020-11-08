package models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Product implements java.io.Serializable{

    private int id;
    private String naamProduct;
    private String merkProduct;
    private LocalDate houdbaarheidsDatum;
    private LocalDate aankoopDatum;
    private String soortVoedsel;
    private String toevoeging;


    public Product(int id, String naamProduct, String merkProduct, LocalDate houdbaarheidsDatum, LocalDate aankoopDatum, String soortVoedsel, String toevoeging) {
        this.id = id;
        this.naamProduct = naamProduct;
        this.merkProduct = merkProduct;
        this.houdbaarheidsDatum = houdbaarheidsDatum;
        this.aankoopDatum = aankoopDatum;
        this.soortVoedsel = soortVoedsel;
        this.toevoeging = toevoeging;
    }

    public String getMerkProduct() {
        return merkProduct;
    }

    public int getId() {
        return id;
    }

    public String getNaamProduct() {
        return naamProduct;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public String getSoortVoedsel() {
        return soortVoedsel;
    }

    public LocalDate getHoudbaarheidsDatum() {
        return houdbaarheidsDatum;
    }

    public LocalDate getAankoopDatum() {
        return aankoopDatum;
    }

    @Override
    public String toString() {

        Date today = new Date();
        Calendar houdbaarCalendar = Calendar.getInstance();
        Date houdbaarDate = houdbaarCalendar.getTime();

        long difference = today.getTime()-houdbaarDate.getTime()/86400000;
        long realDifference = Math.abs(difference);


        return  "\n" +"Naam Product:         " + naamProduct + '\n' +
                "Merk Product:          " + merkProduct + '\n' +
                "Houdbaarheids Datum:   " + houdbaarheidsDatum + '\n' +
                "AankoopDatum:       " + aankoopDatum + '\n' +
                "SoortVoedsel:          " + soortVoedsel + '\n' +
                "Toevoeging  :          " + toevoeging + '\n' +
                "Dagen tot over de datum" + realDifference;


    }
}
