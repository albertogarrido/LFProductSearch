package net.albertogarrido.lfproductsearch.controllers.search;

import android.widget.Toast;

import net.albertogarrido.lfproductsearch.R;
import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.rest.RestClient;
import net.albertogarrido.lfproductsearch.utils.Utils;

import java.util.ArrayList;

public class ProductSearchController implements IProductSearchController{

    private ArrayList<Product> allProducts;

    public ProductSearchController(){
        RestClient rc = new RestClient();
        this.allProducts = rc.getProductAPI().getProducts();
    }

    @Override
    public ArrayList<Product> getFilteredProducts(String term) {
        ArrayList<Product> results  = new ArrayList<>();
        if(this.allProducts.size() <= 0) return results;

        for (Product product : this.allProducts){
            if(contains(product, term)){
                results.add(product);
            }
        }
        return results;
    }

    private boolean contains(Product product, String term){
        boolean productMatch = false;
        if(product == null || term == null) return productMatch;
        if(product.getIndexableName() != null && !"".equals(product.getIndexableName())){
            productMatch = product.getIndexableName().toLowerCase().contains(term.toLowerCase());
        } else if(product.getDescription() != null && !"".equals(product.getDescription())){
            productMatch = product.getDescription().toLowerCase().contains(term.toLowerCase());
        }
        return productMatch;
    }
}
