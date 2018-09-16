package news.agoda.com.sample.domain;

import android.support.annotation.NonNull;

import java.util.List;

import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.ValidationUtils;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Implementation to load News from Concerned Data source.
 */
public class NewsRepository implements NewsDataSource {

    private static NewsRepository INSTANCE = null;

    /**
     * Data source to fetch the result from(Local/Remote).
     */
    private final NewsDataSource mNewsRemoteDataSource;

    // Prevent direct instantiation.
    private NewsRepository(@NonNull NewsDataSource newsRemoteDataSource) {
        mNewsRemoteDataSource = ValidationUtils.checkNotNull(newsRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param newsRemoteDataSource the backend data source
     * @return the {@link NewsRepository} instance
     */
    public static NewsRepository getInstance(NewsDataSource newsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NewsRepository(newsRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Gets News from the Concerned Data Source.
     * <p>
     * Note: {@link FetchNewsCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getNews(@NonNull FetchNewsCallback callback) {
        ValidationUtils.checkNotNull(callback);
        getNewsFromRemoteDataSource(callback);
    }

    private void getNewsFromRemoteDataSource(@NonNull final FetchNewsCallback callback) {
        mNewsRemoteDataSource.getNews(new FetchNewsCallback() {
            @Override
            public void onNewsRetrieved(List<ResNewsEntity> newsResults) {
                callback.onNewsRetrieved(newsResults);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}