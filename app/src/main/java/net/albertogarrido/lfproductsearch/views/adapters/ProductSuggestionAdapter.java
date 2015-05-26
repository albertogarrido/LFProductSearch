package net.albertogarrido.lfproductsearch.views.adapters;


import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

public class ProductSuggestionAdapter<Product>
        extends ArrayAdapter<Product>
        implements Filterable {

    private ArrayList<Product> suggestionList;

    public ProductSuggestionAdapter(Context context, int resource) {
        super(context, resource);
        suggestionList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return (suggestionList != null) ? suggestionList.size() : 0;
    }

    @Override
    public Product getItem(int index) {
        return suggestionList.get(index);
    }

    @Override
    public Filter getFilter() {
        return new ProductSuggestionFilter(this);
    }

    public void setSuggestionList(ArrayList<Product> list) {
        this.suggestionList = list;
    }

    public void notifyChanges(boolean invalidated) {
        if (invalidated) {
            this.notifyDataSetInvalidated();
        } else {
            this.notifyDataSetChanged();
        }
    }
}
