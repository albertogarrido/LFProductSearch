package net.albertogarrido.lfproductsearch.presenters;

import net.albertogarrido.lfproductsearch.model.Product;

import java.util.ArrayList;

public interface IProductDetailPresenter {

    int countProductAvailability(Product product);

    void showProductSearch(ArrayList<Product> filteredProducts);
}
