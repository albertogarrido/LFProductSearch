package net.albertogarrido.lfproductsearch.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.rest.deserializer.ProductDeserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;


public class RestClient {

    private static final String API_URL = "http://codeyourapp.net/";
    private ProductAPI productAPI;

    public RestClient() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
        gsonBuilder.registerTypeAdapter(listType, new ProductDeserializer());

        Gson gson = gsonBuilder.create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setEndpoint(API_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        productAPI = restAdapter.create(ProductAPI.class);
    }

    public ProductAPI getProductAPI() {
        return productAPI;
    }

}
