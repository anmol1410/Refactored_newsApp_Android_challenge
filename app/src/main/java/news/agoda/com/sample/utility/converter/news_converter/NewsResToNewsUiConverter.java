package news.agoda.com.sample.utility.converter.news_converter;

import android.support.annotation.Nullable;

import java.util.List;

import news.agoda.com.sample.news_list.model.NewsEntityModel;
import news.agoda.com.sample.news_list.model.ResMediaEntity;
import news.agoda.com.sample.news_list.model.ResNewsEntity;
import news.agoda.com.sample.utility.converter.ModelConverter;

/**
 * Converts the News received from Server Network Call, to the News which can be rendered on UI.
 */
class NewsResToNewsUiConverter implements ModelConverter<ResNewsEntity, NewsEntityModel> {

    @Nullable
    @Override
    public NewsEntityModel convert(@Nullable ResNewsEntity source) {

        if (source == null) {
            return null;
        }

        List<ResMediaEntity> resMediaEntityList = source.getMediaEntity();
        String imageUrl = "";

        if (resMediaEntityList != null && !resMediaEntityList.isEmpty()) {
            imageUrl = resMediaEntityList.get(0).getUrl();
        }

        // Construct the News Model for the UI render.
        return NewsEntityModel.newBuilder()
                .withArticleUrl(source.getArticleUrl())
                .withNewsImageUrl(imageUrl)
                .withNewsTitle(source.getTitle())
                .withSummary(source.getSummary())
                .build();
    }
}