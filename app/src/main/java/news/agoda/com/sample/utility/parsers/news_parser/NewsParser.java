package news.agoda.com.sample.utility.parsers.news_parser;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.news_list.model.ResMediaEntity;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.parsers.IParser;

/**
 * Parses the Response of News as received from the Server Network Call to the Java Object.
 */
public class NewsParser implements IParser<String, List<ResNewsEntity>> {

    @Override
    public List<ResNewsEntity> parse(@Nullable String source) throws JSONException {
        List<ResNewsEntity> newsItemList = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return newsItemList;
        }
        JSONObject jsonObject = new JSONObject(source);
        final String RESULTS_KEY = "results";
        final String MULTIMEDIA_KEY = "multimedia";
        if (!jsonObject.has(RESULTS_KEY) || !(jsonObject.get(RESULTS_KEY) instanceof JSONArray)) {
            return newsItemList;
        }

        JSONArray resultArray = jsonObject.getJSONArray(RESULTS_KEY);
        for (int x = 0; x < resultArray.length(); x++) {
            JSONObject newsObject = resultArray.getJSONObject(x);

            ArrayList<ResMediaEntity> resMediaEntityList = new ArrayList<>();

            if (newsObject.has(MULTIMEDIA_KEY) && newsObject.get(MULTIMEDIA_KEY) instanceof JSONArray) {
                JSONArray mediaArray = newsObject.getJSONArray(MULTIMEDIA_KEY);
                for (int y = 0; y < mediaArray.length(); y++) {
                    JSONObject mediaObject = mediaArray.getJSONObject(y);

                    ResMediaEntity resMediaEntity = ResMediaEntity.newBuilder()
                            .withUrl(setIfExists(mediaObject, "url"))
                            .withFormat(setIfExists(mediaObject, "format"))
                            .withHeight(mediaObject.has("height") ? mediaObject.getInt("height") : 0)
                            .withWidth(mediaObject.has("width") ? mediaObject.getInt("width") : 0)
                            .withType(setIfExists(mediaObject, "type"))
                            .withSubType(setIfExists(mediaObject, "subtype"))
                            .withCaption(setIfExists(mediaObject, "caption"))
                            .withCopyright(setIfExists(mediaObject, "copyright"))
                            .build();

                    resMediaEntityList.add(resMediaEntity);
                }
            }

            ResNewsEntity resNewsEntity = ResNewsEntity.newBuilder()
                    .withTitle(setIfExists(newsObject, "title"))
                    .withArticleUrl(setIfExists(newsObject, "url"))
                    .withByline(setIfExists(newsObject, "byline"))
                    .withPublishedDate(setIfExists(newsObject, "published_date"))
                    .withSummary(setIfExists(newsObject, "abstract"))
                    .withMediaEntityList(resMediaEntityList)
                    .build();

            newsItemList.add(resNewsEntity);
        }
        return newsItemList;
    }

    private String setIfExists(JSONObject mediaObject, String key) throws JSONException {
        return mediaObject.has(key) ? mediaObject.getString(key) : "";
    }
}
