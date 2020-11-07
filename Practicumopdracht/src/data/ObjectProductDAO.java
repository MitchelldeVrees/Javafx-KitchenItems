package data;
import models.Product;

import java.io.*;

public class ObjectProductDAO extends ProductDAO{

    private static String FILENAME = "resources/productObject.dat";
    private File file;



    @Override
    public boolean save() {
        file = new File(FILENAME);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Product product : productList) {

               out.writeObject(new Product(product.getId(), product.getNaamProduct(), product.getMerkProduct(),
                        product.getHoudbaarheidsDatum(), product.getAankoopDatum(), product.getSoortVoedsel(),
                        product.getToevoeging()
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load() {
        try (ObjectInputStream inp = new ObjectInputStream(new FileInputStream(FILENAME))) {

            while (true) {
                Product product = (Product) inp.readObject();
                productList.add(product);
            }

        } catch (IOException | ClassNotFoundException ignored) {
        }
        return false;
    }

}
