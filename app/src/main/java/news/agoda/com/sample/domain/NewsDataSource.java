package news.agoda.com.sample.domain;

import android.support.annotation.NonNull;

import java.util.List;

import news.agoda.com.sample.news_list.model.ResNewsEntity;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Main entry point for accessing data throughtout the app.
 * It can be Local(e.g. Shared Prefs/ DBs) or Remote(Network calls) etc.
 * <p>
 * For simplicity, only getNews() has callbacks. Consider adding callbacks to other functionality as the application gets more complex.
 */
public interface NewsDataSource {

    void getNews(@NonNull FetchNewsCallback callback);

    /**
     * Callback which receives the result of the Data fetch.
     */
    interface FetchNewsCallback {

        void onNewsRetrieved(List<ResNewsEntity> newsResults);

        void onDataNotAvailable();
    }
}