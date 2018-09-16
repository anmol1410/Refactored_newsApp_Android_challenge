package news.agoda.com.sample.news_list.listeners;

import news.agoda.com.sample.news_list.model.NewsEntityModel;

/**
 * Triggered when there needs to be some interaction between the Activity/Fragment.
 */
public interface NewsInteractionListener {

    /**
     * To Show/Hide the Loader.
     *
     * @param active True to show, or to hide otherwise.
     */
    void setLoadingIndicator(boolean active);

    /**
     * To show the particular error.
     *
     * @param showError Error to show.
     */
    void showLoadingNewsError(boolean showError);

    /**
     * Triggered when any News is clicked.
     *
     * @param newsEntityModel News details which was clicked.
     */
    void onNewsClicked(NewsEntityModel newsEntityModel);
}
