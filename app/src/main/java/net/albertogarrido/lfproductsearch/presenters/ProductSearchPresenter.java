package net.albertogarrido.lfproductsearch.presenters;

import android.content.Intent;

import net.albertogarrido.lfproductsearch.ProductDetailActivity;
import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.views.ISearchView;

import java.util.ArrayList;

public class ProductSearchPresenter implements IProductSearchPresenter {

    ISearchView productSearchView;

    public ProductSearchPresenter(ISearchView productSearchActivity) {
        this.productSearchView = productSearchActivity;
    }

    @Override
    public int getLocationsFiltered(ArrayList<Product> list) {
        int count = 0;
        for(Product product : list){
            count += product.getLocation() != null ? product.getLocation().size() : 0;
        }
        return count;
    }

    @Override
    public void showProductDetails(Product product,String currentTerm) {
        Intent intent = new Intent(productSearchView.getContext(), ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.PRODUCT, product);
        intent.putExtra(ProductDetailActivity.CURRENT_TERM, currentTerm);
        productSearchView.getContext().startActivity(intent);
    }
}
