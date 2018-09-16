package news.agoda.com.sample;

import news.agoda.com.sample.base.usecase.UseCaseHandler;
import news.agoda.com.sample.domain.NewsRepository;
import news.agoda.com.sample.domain.remote.NewsRemoteDataSource;
import news.agoda.com.sample.news_list.usecase.GetNews;

/**
 * Created by anmolsehgal on 27-03-2018.
 * <p>
 * Injects various components throughout the application.
 */
public class Injection {

    /**
     * Provides the News repository which can manage the Data retrieval(Local or Remote).
     * It is its responsibility to provide the mechanism to get the Data, from which ever source,
     * and in which ever manner.
     *
     * @return The News Repository.
     */
    private static NewsRepository provideGetNewsRepository() {
        return NewsRepository.getInstance(NewsRemoteDataSource.getInstance());
    }

    /**
     * Provides the handler which can handle various Use cases.
     * Its the responsibility of this Executor to execute the Use case in which ever manner.
     *
     * @return THe Use case Executor.
     */
    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    /**
     * Provides the use case to Get the News.
     * Its the responsibility of the Use case to manage how to get the news.
     *
     * @return The use case to get the news.
     */
    public static GetNews provideGetNews() {
        return new GetNews(provideGetNewsRepository());
    }
}