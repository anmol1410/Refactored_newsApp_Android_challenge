package news.agoda.com.sample.news_list.usecase;

import android.support.annotation.Nullable;

import java.util.List;

import news.agoda.com.sample.base.usecase.UseCase;
import news.agoda.com.sample.domain.NewsDataSource;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.ValidationUtils;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * use Case to get the news.
 */
public class GetNews extends UseCase<GetNews.RequestValues, GetNews.ResponseValue> {

    /**
     * Repository to use to get news(Local/Remote etc).
     */
    private NewsDataSource mNewsRemoteDataSource;

    public GetNews(NewsDataSource mNewsRemoteDataSource) {
        this.mNewsRemoteDataSource = mNewsRemoteDataSource;
    }

    @Override
    protected void executeUseCase(GetNews.RequestValues requestValues) {

        mNewsRemoteDataSource.getNews(new NewsDataSource.FetchNewsCallback() {

            @Override
            public void onNewsRetrieved(List<ResNewsEntity> newsResults) {
                getUseCaseCallback().onSuccess(new GetNews.ResponseValue(newsResults));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });

    }

    /**
     * Wrapper for the input values for this use case.
     */
    public static final class RequestValues implements UseCase.RequestValues {
    }

    /**
     * Wrapper for the result values of this use case.
     */
    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<ResNewsEntity> mNewsResponse;

        ResponseValue(@Nullable List<ResNewsEntity> newsResults) {
            mNewsResponse = ValidationUtils.checkNotNull(newsResults, "News JSON Response cannot be null!");
        }

        @Nullable
        public List<ResNewsEntity> getNews() {
            return mNewsResponse;
        }
    }

}
