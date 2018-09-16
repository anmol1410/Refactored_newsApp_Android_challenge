package news.agoda.com.sample.news_list.model;

/**
 * News Media details as received from the Server Network Call.
 */
public class ResMediaEntity {
    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;

    private ResMediaEntity(Builder builder) {
        url = builder.url;
        format = builder.format;
        height = builder.height;
        width = builder.width;
        type = builder.type;
        subType = builder.subType;
        caption = builder.caption;
        copyright = builder.copyright;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public static final class Builder {
        private String url;
        private String format;
        private int height;
        private int width;
        private String type;
        private String subType;
        private String caption;
        private String copyright;

        private Builder() {
        }

        public Builder withUrl(String val) {
            url = val;
            return this;
        }

        public Builder withFormat(String val) {
            format = val;
            return this;
        }

        public Builder withHeight(int val) {
            height = val;
            return this;
        }

        public Builder withWidth(int val) {
            width = val;
            return this;
        }

        public Builder withType(String val) {
            type = val;
            return this;
        }

        public Builder withSubType(String val) {
            subType = val;
            return this;
        }

        public Builder withCaption(String val) {
            caption = val;
            return this;
        }

        public Builder withCopyright(String val) {
            copyright = val;
            return this;
        }

        public ResMediaEntity build() {
            return new ResMediaEntity(this);
        }
    }
}
