package news.agoda.com.sample.news_list.model;

import java.util.List;

/**
 * News details as received from the Server Network Call.
 */
public class ResNewsEntity {
    private String title;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    private List<ResMediaEntity> mResMediaEntityList;

    private ResNewsEntity(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        articleUrl = builder.articleUrl;
        byline = builder.byline;
        publishedDate = builder.publishedDate;
        mResMediaEntityList = builder.mResMediaEntityList;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<ResMediaEntity> getMediaEntity() {
        return mResMediaEntityList;
    }

    public static final class Builder {
        private String title;
        private String summary;
        private String articleUrl;
        private String byline;
        private String publishedDate;
        private List<ResMediaEntity> mResMediaEntityList;

        private Builder() {
        }

        public Builder withTitle(String val) {
            title = val;
            return this;
        }

        public Builder withSummary(String val) {
            summary = val;
            return this;
        }

        public Builder withArticleUrl(String val) {
            articleUrl = val;
            return this;
        }

        public Builder withByline(String val) {
            byline = val;
            return this;
        }

        public Builder withPublishedDate(String val) {
            publishedDate = val;
            return this;
        }

        public Builder withMediaEntityList(List<ResMediaEntity> val) {
            mResMediaEntityList = val;
            return this;
        }

        public ResNewsEntity build() {
            return new ResNewsEntity(this);
        }
    }
}
