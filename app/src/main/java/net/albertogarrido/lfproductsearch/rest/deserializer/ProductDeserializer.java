package net.albertogarrido.lfproductsearch.rest.deserializer;

import android.graphics.PointF;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.albertogarrido.lfproductsearch.model.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductDeserializer implements JsonDeserializer {

    private static final String PRODUCTS_PROPERTY = "products";
    private static final String ID_PROPERTY = "_id";
    private static final String NAME_PROPERTY = "name";
    private static final String INDEXABLE_NAME_PROPERTY = "indexable_name";
    private static final String DESCRIPTION_PROPERTY = "description";
    private static final String LOCATION_PROPERTY = "location";
    private static final String IMAGES_PROPERTY = "images";

    private static final int FIRST_IMAGE = 0;

    private static final String FULL_IMAGE_PROPERTY = "image";
    private static final String LARGE_IMAGE_PROPERTY = "large";
    private static final String MEDIUM_IMAGE_PROPERTY = "medium";
    private static final String THUMB_IMAGE_PROPERTY = "thumb";

    @Override
    public ArrayList<Product> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        ArrayList<Product> productResponse = new ArrayList<>();

        JsonObject result = json.getAsJsonObject();

        JsonArray jsonProducts = result.get(PRODUCTS_PROPERTY).getAsJsonArray();

        for(int productIndex = 0; productIndex < jsonProducts.size(); productIndex++){
            Product product = new Product();

            JsonObject jsonProduct = jsonProducts.get(productIndex).getAsJsonObject();
            product.setId(jsonProduct.get(ID_PROPERTY).getAsString());
            product.setName(jsonProduct.get(NAME_PROPERTY).getAsString());
            product.setIndexableName(jsonProduct.get(INDEXABLE_NAME_PROPERTY).getAsString());
            product.setDescription(jsonProduct.get(DESCRIPTION_PROPERTY).getAsString());

            //getting locations
            JsonArray locationsArray = jsonProduct.get(LOCATION_PROPERTY).getAsJsonArray();
            ArrayList<PointF> productLocations = new ArrayList<>();
            for(int locationIndex = 0; locationIndex < locationsArray.size(); locationIndex++ ){
                JsonArray theLocation = locationsArray.get(locationIndex).getAsJsonArray();
                PointF point = new PointF();
                point.set(theLocation.get(0).getAsFloat(), theLocation.get(1).getAsFloat());
                productLocations.add(point);
            }
            product.setLocation(productLocations);

            // Getting images. For this project we'll get the first image only.
            JsonArray imagesArray = jsonProduct.get(IMAGES_PROPERTY).getAsJsonArray();
            HashMap<String,String> map = new HashMap<>();
            if(imagesArray.size() > 0){
                JsonObject objectImage = imagesArray.get(FIRST_IMAGE).getAsJsonObject();
                map.put(FULL_IMAGE_PROPERTY, objectImage.get(FULL_IMAGE_PROPERTY).getAsString());
                map.put(LARGE_IMAGE_PROPERTY, objectImage.get(LARGE_IMAGE_PROPERTY).getAsString());
                map.put(MEDIUM_IMAGE_PROPERTY, objectImage.get(MEDIUM_IMAGE_PROPERTY).getAsString());
                map.put(THUMB_IMAGE_PROPERTY, objectImage.get(THUMB_IMAGE_PROPERTY).getAsString());
            }
            product.setImages(map);

            productResponse.add(product);
        }
        return productResponse;
    }
}
