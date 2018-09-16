package news.agoda.com.sample;

import org.json.JSONException;
import org.junit.Test;

import java.util.List;

import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.parsers.news_parser.NewsParser;

import static junit.framework.Assert.assertEquals;

public class ParsersTest {
    private NewsParser mNewsParser = new NewsParser();

    @Test
    public void verifyEmptyOutputWithNullInput() throws JSONException {
        List<ResNewsEntity> output = mNewsParser.parse(null);
        assertEquals(0, output.size());
    }

    @Test
    public void verifyEmptyOutputWithEmptyInput() throws JSONException {
        String inputJson = "";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);
        assertEquals(0, output.size());
    }

    @Test
    public void verifyEmptyOutputWithEmptyResultKeyInInput() throws JSONException {
        String inputJson = "{ \"results\":[] }";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);
        assertEquals(0, output.size());
    }

    @Test
    public void verifyEmptyOutputWithMissingResultKeyInInput() throws JSONException {
        String inputJson = "{ }";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);
        assertEquals(0, output.size());
    }

    @Test
    public void verifyEmptyOutputWithInvalidResultsKeyInInput() throws JSONException {
        String inputJson = "{ \"results\":\" \" }";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);

        assertEquals(0, output.size());
    }

    @Test
    public void verifyEmptyMediaEntityForInvalidMultimediaKeyInInput() throws JSONException {
        String inputJson = "{\"results\":[  { \"multimedia\": \" \" }]}";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);

        assertEquals(1, output.size());
        assertEquals(0, output.get(0).getMediaEntity().size());
    }

    @Test
    public void verifyDefaultValuesForMissingKeysInInput() throws JSONException {
        String inputJson = "{\"results\":[  { \"multimedia\": [] }]}";
        List<ResNewsEntity> output = mNewsParser.parse(inputJson);

        final String DEFAULT_VALUE = "";
        assertEquals(1, output.size());
        assertEquals(DEFAULT_VALUE, output.get(0).getTitle());
        assertEquals(DEFAULT_VALUE, output.get(0).getArticleUrl());
        assertEquals(DEFAULT_VALUE, output.get(0).getSummary());
        assertEquals(DEFAULT_VALUE, output.get(0).getByline());
        assertEquals(DEFAULT_VALUE, output.get(0).getPublishedDate());
        assertEquals(0, output.get(0).getMediaEntity().size());
    }

    @Test
    public void verifyFullyParsedOutputWithValidInput() throws JSONException {
        final String ARTICLE_URL = "Custom ARTICLE_URL";
        final String TITLE = "Custom TITLE";
        final String SUMMARY = "Custom SUMMARY";
        final String BY_LINE = "Custom BY_LINE";
        final String PUBLISHED_DATE = "Custom PUBLISHED_DATE";
        final String MULTIMEDIA_URL = "Custom MULTIMEDIA_URL";
        final String MULTIMEDIA_FORMAT = "Custom MULTIMEDIA_URL";
        final int MULTIMEDIA_HEIGHT = 10;
        final int MULTIMEDIA_WIDTH = 20;
        final String MULTIMEDIA_TYPE = "Custom MULTIMEDIA_TYPE";
        final String MULTIMEDIA_SUBTYPE = "Custom MULTIMEDIA_SUBTYPE";
        final String MULTIMEDIA_CAPTION = "Custom MULTIMEDIA_CAPTION";
        final String MULTIMEDIA_COPYRIGHT = "Custom MULTIMEDIA_COPYRIGHT";

        String inputJson = "{  \n" +
                "   \"results\":[  \n" +
                "      {  \n" +
                "         \"title\":\"" + TITLE + "\",\n" +
                "         \"abstract\":\"" + SUMMARY + "\",\n" +
                "         \"url\":\"" + ARTICLE_URL + "\",\n" +
                "         \"byline\":\"" + BY_LINE + "\",\n" +
                "         \"published_date\":\"" + PUBLISHED_DATE + "\"," +
                "         \"multimedia\":[  \n" +
                "            {  \n" +
                "               \"url\":\"" + MULTIMEDIA_URL + "\",\n" +
                "               \"format\":\"" + MULTIMEDIA_FORMAT + "\",\n" +
                "               \"height\":" + MULTIMEDIA_HEIGHT + ",\n" +
                "               \"width\":" + MULTIMEDIA_WIDTH + ",\n" +
                "               \"type\":\"" + MULTIMEDIA_TYPE + "\",\n" +
                "               \"subtype\":\"" + MULTIMEDIA_SUBTYPE + "\",\n" +
                "               \"caption\":\"" + MULTIMEDIA_CAPTION + "\",\n" +
                "               \"copyright\":\"" + MULTIMEDIA_COPYRIGHT + "\"" +
                "            }]" +
                "         }] " +
                "     }";

        List<ResNewsEntity> output = mNewsParser.parse(inputJson);

        assertEquals(TITLE, output.get(0).getTitle());
        assertEquals(ARTICLE_URL, output.get(0).getArticleUrl());
        assertEquals(SUMMARY, output.get(0).getSummary());
        assertEquals(BY_LINE, output.get(0).getByline());
        assertEquals(PUBLISHED_DATE, output.get(0).getPublishedDate());
        assertEquals(MULTIMEDIA_URL, output.get(0).getMediaEntity().get(0).getUrl());
        assertEquals(MULTIMEDIA_FORMAT, output.get(0).getMediaEntity().get(0).getFormat());
        assertEquals(MULTIMEDIA_HEIGHT, output.get(0).getMediaEntity().get(0).getHeight());
        assertEquals(MULTIMEDIA_WIDTH, output.get(0).getMediaEntity().get(0).getWidth());
        assertEquals(MULTIMEDIA_TYPE, output.get(0).getMediaEntity().get(0).getType());
        assertEquals(MULTIMEDIA_SUBTYPE, output.get(0).getMediaEntity().get(0).getSubType());
        assertEquals(MULTIMEDIA_CAPTION, output.get(0).getMediaEntity().get(0).getCaption());
        assertEquals(MULTIMEDIA_COPYRIGHT, output.get(0).getMediaEntity().get(0).getCopyright());
    }
}