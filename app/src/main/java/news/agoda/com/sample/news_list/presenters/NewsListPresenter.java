package news.agoda.com.sample.news_list.presenters;

import android.support.annotation.Nullable;

import java.util.List;

import news.agoda.com.sample.base.usecase.UseCase;
import news.agoda.com.sample.base.usecase.UseCaseHandler;
import news.agoda.com.sample.news_list.usecase.GetNews;
import news.agoda.com.sample.news_list.model.NewsEntityModel;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.news_list.views.NewsActivity;
import news.agoda.com.sample.utility.ValidationUtils;
import news.agoda.com.sample.utility.converter.news_converter.NewsResponseConverter;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Listens to user actions from the UI ({@link NewsActivity}), retrieves the data and updates the
 * UI as required.
 */
public class NewsListPresenter implements NewsContract.NewsPresenter {

    private UseCaseHandler mUseCaseHandler;
    private GetNews mGetNews;
    private NewsContract.View mView;

    public NewsListPresenter(UseCaseHandler useCaseHandler,
                             GetNews getNews,
                             NewsContract.View view) {
        mUseCaseHandler = useCaseHandler;
        mGetNews = getNews;
        mView = ValidationUtils.checkNotNull(view, "view can not be null");
        mView.setPresenter(this);

        start();
    }

    @Override
    public void start() {
        fetchNews();
    }

    @Override
    public void fetchNews() {

        // Show loading dialog before the Network call is made.
        mView.setLoadingIndicator(true);

        // Just to get the news from server, requires Input as the parameters etc.
        // But for this example, no parameter is required.
        // THe GetNews.RequestValues can be used to pass such Params if required.
        GetNews.RequestValues requestValue = new GetNews.RequestValues();

        // Execute the use case, and receive the results in the callback.
        mUseCaseHandler.execute(mGetNews, requestValue,
                new UseCase.UseCaseCallback<GetNews.ResponseValue>() {
                    @Override
                    public void onSuccess(GetNews.ResponseValue response) {
                        // The view may not be able to handle UI updates anymore
                        if (!mView.isActive()) {
                            return;
                        }
                        processNews(response.getNews());
                        mView.setLoadingIndicator(false);
                    }

                    @Override
                    public void onError() {
                        // The view may not be able to handle UI updates anymore
                        if (!mView.isActive()) {
                            return;
                        }
                        mView.setLoadingIndicator(false);
                        mView.showLoadingNewsError(true);
                    }
                });
    }

    /**
     * Process newsList i.e. convert the Server response to the response which UI can render.
     *
     * @param newsList server response.
     */
    private void processNews(@Nullable List<ResNewsEntity> newsList) {
        if (newsList == null || newsList.isEmpty()) {
            // Show error.
            mView.showLoadingNewsError(true);
            return;
        }

        List<NewsEntityModel> newsListForUi = new NewsResponseConverter().convert(newsList);
        if (newsListForUi == null || newsListForUi.isEmpty()) {
            mView.showLoadingNewsError(true);
            return;
        }
        mView.showNews(newsListForUi);
        mView.showLoadingNewsError(false);
    }
}