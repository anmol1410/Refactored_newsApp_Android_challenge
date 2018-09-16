package news.agoda.com.sample.news_list.presenters;

import java.util.List;

import news.agoda.com.sample.base.BasePresenter;
import news.agoda.com.sample.base.BaseView;
import news.agoda.com.sample.news_list.model.NewsEntityModel;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * This specifies the contract between the view and the presenter.
 */
public interface NewsContract {

    interface View extends BaseView<NewsPresenter> {

        /**
         * To show/hide the loader, used to represent Ongoing Network calls.
         *
         * @param toShow True to show the loader, false to hide it.
         */
        void setLoadingIndicator(boolean toShow);

        /**
         * Show the News as fetched from the server Network call.
         *
         * @param news News to show on UI.
         */
        void showNews(List<NewsEntityModel> news);

        /**
         * Checks if the View to handle the callback is still active or not.
         *
         * @return True if View is still active, false otherwise.
         */
        boolean isActive();

        /**
         * Checks if the Error message. should be shown or hidden.
         *
         * @param toShowError True to show the error message. false to hide it.
         */
        void showLoadingNewsError(boolean toShowError);
    }

    interface NewsPresenter extends BasePresenter {

        void fetchNews();
    }
}
