package net.albertogarrido.lfproductsearch.presenters;


import net.albertogarrido.lfproductsearch.model.Product;

import java.util.ArrayList;

public interface IProductSearchPresenter {

    int getLocationsFiltered(ArrayList<Product> list);

    void showProductDetails(Product product, String currentTerm);
}
