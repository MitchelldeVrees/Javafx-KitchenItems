package comparators;

import models.Product;

import java.util.Comparator;

public class DateComparator implements Comparator<Product> {

    // Sorteren op Aankoopdatum.
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getHoudbaarheidsDatum().compareTo(o2.getHoudbaarheidsDatum());
    }


}
