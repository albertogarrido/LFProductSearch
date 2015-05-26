package net.albertogarrido.lfproductsearch.presenters;

import android.content.Intent;

import net.albertogarrido.lfproductsearch.ProductDetailActivity;
import net.albertogarrido.lfproductsearch.ProductSearchActivity;
import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.views.ISearchView;

import java.util.ArrayList;

public class ProductDetailPresenter implements IProductDetailPresenter {

    ISearchView productSearchView;

    public ProductDetailPresenter(ISearchView productDetailActivity) {
        this.productSearchView = productDetailActivity;
    }

    @Override
    public int countProductAvailability(Product product) {
        return product.getLocation() != null ? product.getLocation().size() : 0;

    }

    @Override
    public void showProductSearch(ArrayList<Product> filteredProducts) {
        Intent intent = new Intent(productSearchView.getContext(), ProductSearchActivity.class);
        intent.putParcelableArrayListExtra(ProductDetailActivity.PRODUCT_LIST, filteredProducts);
        intent.putExtra(ProductDetailActivity.CURRENT_TERM, productSearchView.getCustomAutoCompleteTextView().getText().toString());
        productSearchView.getContext().startActivity(intent);
    }
}
