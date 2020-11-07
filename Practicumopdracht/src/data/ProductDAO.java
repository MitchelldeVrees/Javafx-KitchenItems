package data;

import models.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductDAO implements DAO<Product>{

    public static final int START_ID = 0;
    protected List<Product> productList;
    private int hoogsteID = START_ID;

    public ProductDAO() {
        productList = new ArrayList<>();
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public Product get(int id) {
        return null;
    }

    @Override
    public void addOrUpdate(Product product) {
        if (product.getId() > 0) {
            for (int i = 0; i < productList.size(); i++) {
                if (product.getId() == productList.get(i).getId()) {
                    remove(productList.get(i));
                    productList.add(product);
                }
            }
        } else if (product.getId() == 0) {
            product = new Product(
                    getUniqueId(), product.getNaamProduct(), product.getMerkProduct(),
                    product.getHoudbaarheidsDatum(), product.getAankoopDatum(), product.getSoortVoedsel(),
                    product.getToevoeging()
            );
            productList.add(product);
        }
    }

    @Override
    public void remove(Product product) {
        productList.remove(product);
    }
    @Override
    public abstract boolean save();


    @Override
    public abstract boolean load();

    private int getUniqueId() {
        for (Product product : productList) {
            if (product.getId() > hoogsteID) {
                hoogsteID = product.getId();
            }
        }
        return hoogsteID + 1;
    }

}




