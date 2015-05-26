package net.albertogarrido.lfproductsearch.controllers.search;

import net.albertogarrido.lfproductsearch.model.Product;

import java.util.ArrayList;

public interface IProductSearchController {

    ArrayList<Product> getFilteredProducts(String term);

}
