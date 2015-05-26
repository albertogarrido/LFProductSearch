package net.albertogarrido.lfproductsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.presenters.IProductDetailPresenter;
import net.albertogarrido.lfproductsearch.presenters.ProductDetailPresenter;
import net.albertogarrido.lfproductsearch.views.IProductDetailView;

import java.util.ArrayList;


public  class       ProductDetailActivity
        extends     BaseSearchActivity
        implements IProductDetailView {


    private TextView    tvProductName;
    private TextView    tvProductDescription;
    private ImageView   tvProductThumbnail;
    private TextView    tvProductAvailability;

    private IProductDetailPresenter productDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        tvProductName = (TextView) findViewById(R.id.product_name);
        tvProductDescription = (TextView) findViewById(R.id.product_description);
        tvProductThumbnail = (ImageView) findViewById(R.id.product_thumbnail);
        tvProductAvailability = (TextView) findViewById(R.id.product_location);

        productDetailPresenter = new ProductDetailPresenter(this);

        Intent intent = getIntent();
        Product product =  intent.getParcelableExtra(BaseSearchActivity.PRODUCT);
        String currentTerm =  intent.getStringExtra(BaseSearchActivity.CURRENT_TERM);

        populateProduct(product);
        super.setSearchTerm(currentTerm);
    }

    @Override
    public void populateProduct(Product product) {
        tvProductName.setText(product.getName());
        tvProductDescription.setText(product.getDescription());
        String thumbURL = product.getImages().get("large");
        Picasso.with(this).load(thumbURL).into(tvProductThumbnail);
        tvProductAvailability.setText(String.format(getResources().getString(R.string.product_availability), productDetailPresenter.countProductAvailability(product)));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        populateProduct(product);
        super.dismissKeyboard();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        super.dismissKeyboard();
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for(int i = 0; i < super.getSuggestionAdapter().getCount(); i++){
            filteredProducts.add( (Product) super.getSuggestionAdapter().getItem(i));
        }
        productDetailPresenter.showProductSearch(filteredProducts);
        return false;
    }

}
