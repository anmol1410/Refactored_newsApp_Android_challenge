package news.agoda.com.sample.utility.converter.news_converter;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import news.agoda.com.sample.news_list.model.NewsEntityModel;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.converter.ModelConverter;

/**
 * Converts List of News received from Server Network Call, to the List  of News which can be rendered on UI.
 */
public class NewsResponseConverter implements ModelConverter<List<ResNewsEntity>, List<NewsEntityModel>> {

    @Nullable
    @Override
    public List<NewsEntityModel> convert(@Nullable List<ResNewsEntity> source) {
        if (source == null) {
            return null;
        }
        List<NewsEntityModel> list = new ArrayList<>();
        final NewsResToNewsUiConverter modelConverter = new NewsResToNewsUiConverter();
        for (ResNewsEntity resNews : source) {
            list.add(modelConverter.convert(resNews));
        }
        return list;
    }
}