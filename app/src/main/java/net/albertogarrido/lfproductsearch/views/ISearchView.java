package net.albertogarrido.lfproductsearch.views;

import net.albertogarrido.lfproductsearch.views.adapters.ProductSuggestionAdapter;
import net.albertogarrido.lfproductsearch.views.components.CustomAutoCompleteTextView;

public interface ISearchView extends IView {
    void loadSearchBar();
    void loadAutocompleteAdapter();

    void dismissKeyboard();
    CustomAutoCompleteTextView getCustomAutoCompleteTextView();
    ProductSuggestionAdapter getSuggestionAdapter();
    void setSearchTerm(String term);
}
