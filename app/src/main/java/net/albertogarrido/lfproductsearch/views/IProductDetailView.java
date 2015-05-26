package net.albertogarrido.lfproductsearch.views;

import net.albertogarrido.lfproductsearch.model.Product;

public interface IProductDetailView extends IView{
    void populateProduct(Product product);
}
