package news.agoda.com.sample.domain.remote;

import android.support.annotation.NonNull;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import news.agoda.com.sample.domain.NewsDataSource;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.parsers.news_parser.NewsParser;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Implementation of the data source that does Remote data fetch.
 * Data Source can be Local(e.g. Database) or Remote(Network calls).
 */
public class NewsRemoteDataSource implements NewsDataSource {

    /**
     * Singleton Instance.
     */
    private static NewsRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private NewsRemoteDataSource() {

    }

    public static NewsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsRemoteDataSource();
        }
        return INSTANCE;
    }

    private static String readStream(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        }
        return sb.toString();
    }

    @Override
    public void getNews(final @NonNull FetchNewsCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api.myjson.com/bins/nl6jh");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    String jsonStringResponse = readStream(con.getInputStream());
                    List<ResNewsEntity> newsItemList = new NewsParser().parse(jsonStringResponse);

                    // Send the success result back.
                    callback.onNewsRetrieved(newsItemList);
                } catch (IOException | JSONException e) {
                    // Send the error result back.
                    callback.onDataNotAvailable();
                }
            }
        }).start();
    }
}
