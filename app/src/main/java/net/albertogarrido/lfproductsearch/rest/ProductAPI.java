package net.albertogarrido.lfproductsearch.rest;

import net.albertogarrido.lfproductsearch.model.Product;

import java.util.ArrayList;

import retrofit.http.GET;

public interface ProductAPI {

    @GET("/products.json")
    public ArrayList<Product> getProducts();

}
