package net.albertogarrido.lfproductsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.presenters.IProductSearchPresenter;
import net.albertogarrido.lfproductsearch.presenters.ProductSearchPresenter;
import net.albertogarrido.lfproductsearch.views.IProductSearchView;

import java.util.ArrayList;


public  class       ProductSearchActivity
        extends     BaseSearchActivity
        implements  IProductSearchView{

    private TextView locationsCountText;
    private IProductSearchPresenter productSearchPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_search);
        locationsCountText = (TextView) findViewById(R.id.count_locations);

        productSearchPresenter = new ProductSearchPresenter(this);

        Intent intent = getIntent();
        ArrayList<Product> productList =  intent.getParcelableArrayListExtra(BaseSearchActivity.PRODUCT_LIST);
        if(productList != null && productList.size() > 0){
            showLocationsCount(productSearchPresenter.getLocationsFiltered(productList));
        }
        String currentTerm = intent.getStringExtra(BaseSearchActivity.CURRENT_TERM);
        if(currentTerm != null && !"".equals(currentTerm)){
            super.setSearchTerm(currentTerm);
        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLocationsCount(int locationsCount) {
        String text = String.format(getResources().getString(R.string.count_locations), locationsCount);
        this.locationsCountText.setText(text);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        productSearchPresenter.showProductDetails(product, getCustomAutoCompleteTextView().getText().toString());
        super.dismissKeyboard();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        super.dismissKeyboard();
        ArrayList<Product> filteredProducts = new ArrayList<>();
        for(int i = 0; i < super.getSuggestionAdapter().getCount(); i++){
            filteredProducts.add( (Product) super.getSuggestionAdapter().getItem(i));
        }
        int locationsCount = productSearchPresenter.getLocationsFiltered(filteredProducts);
        showLocationsCount(locationsCount);
        super.getCustomAutoCompleteTextView().dismissDropDown();
        return false;
    }
}
