package net.albertogarrido.lfproductsearch.views.adapters;

import android.widget.Filter;
import android.widget.Toast;

import net.albertogarrido.lfproductsearch.R;
import net.albertogarrido.lfproductsearch.controllers.search.IProductSearchController;
import net.albertogarrido.lfproductsearch.controllers.search.ProductSearchController;
import net.albertogarrido.lfproductsearch.model.Product;
import net.albertogarrido.lfproductsearch.utils.Utils;

import java.util.ArrayList;

public class ProductSuggestionFilter extends Filter {

    private final ProductSuggestionAdapter adapter;
    private IProductSearchController productSearchController;

    public ProductSuggestionFilter(ProductSuggestionAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence term) {
        if(!Utils.hasConnection(this.adapter.getContext())){
            Toast toast = Toast.makeText(this.adapter.getContext(), this.adapter.getContext().getResources().getText(R.string.toast_no_network), Toast.LENGTH_SHORT);
            toast.show();
        }
        FilterResults filterResults = new FilterResults();
        if(term != null) {

            productSearchController = new ProductSearchController();
            ArrayList<Product> resultList = productSearchController.getFilteredProducts(term.toString());

            filterResults.values = resultList;
            filterResults.count = (resultList != null) ? resultList.size() : 0;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setSuggestionList((ArrayList<Product>) results.values);

        adapter.notifyChanges(!(results != null && results.count > 0));
    }


}
