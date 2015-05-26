package net.albertogarrido.lfproductsearch;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.crashlytics.android.Crashlytics;

import net.albertogarrido.lfproductsearch.views.ISearchView;
import net.albertogarrido.lfproductsearch.views.adapters.ProductSuggestionAdapter;
import net.albertogarrido.lfproductsearch.views.components.CustomAutoCompleteTextView;

import io.fabric.sdk.android.Fabric;


public  class       BaseSearchActivity
        extends     ActionBarActivity
        implements  ISearchView,
                    AdapterView.OnItemClickListener,
                    CustomAutoCompleteTextView.OnClearListener,
                    TextView.OnEditorActionListener {

    public static final String PRODUCT = "product_extra";
    public static final String PRODUCT_LIST = "product_list";
    public static final String CURRENT_TERM = "current_term";

    private CustomAutoCompleteTextView customAutoCompleteTextView;
    private ProductSuggestionAdapter suggestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fabric.with(this, new Crashlytics());
        loadSearchBar();
        loadAutocompleteAdapter();
    }

    @Override
    public CustomAutoCompleteTextView getCustomAutoCompleteTextView() {
        return this.customAutoCompleteTextView;
    }

    @Override
    public ProductSuggestionAdapter getSuggestionAdapter() {
        return this.suggestionAdapter;
    }

    @Override
    public void setSearchTerm(String term) {
        this.getCustomAutoCompleteTextView().setText(term);
        this.getCustomAutoCompleteTextView().onFilterComplete(0);
        this.getCustomAutoCompleteTextView().dismissDropDown();
    }

    @Override
    public void loadSearchBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.actionbar_search_field, null);
        customAutoCompleteTextView =  (CustomAutoCompleteTextView) v.findViewById(R.id.search_field);
        customAutoCompleteTextView.setOnItemClickListener(this);

        final ProgressBar loadingIndicator = (ProgressBar) v.findViewById(R.id.loading_indicator);
        customAutoCompleteTextView.setLoadingIndicator(loadingIndicator);
        loadingIndicator.setVisibility(View.INVISIBLE);

        customAutoCompleteTextView.setOnClearListener(this);
        customAutoCompleteTextView.setOnEditorActionListener(this);

        actionBar.setCustomView(v, new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void loadAutocompleteAdapter() {
        suggestionAdapter = new ProductSuggestionAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        customAutoCompleteTextView.setAdapter(suggestionAdapter);
    }

    @Override
    public void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.customAutoCompleteTextView.getWindowToken(), 0);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClear() {
        customAutoCompleteTextView.setText("");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
