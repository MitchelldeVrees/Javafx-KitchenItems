package models;

import java.time.LocalDate;

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
        return  "Naam Product:         " + naamProduct + '\n' +
                "Merk Product:          " + merkProduct + '\n' +
                "Houdbaarheids Datum:   " + houdbaarheidsDatum + '\n' +
                "AankoopDatum:       " + aankoopDatum + '\n' +
                "SoortVoedsel:          " + soortVoedsel + '\n' +
                "Toevoeging  :          " + toevoeging + '\n';


    }
}
