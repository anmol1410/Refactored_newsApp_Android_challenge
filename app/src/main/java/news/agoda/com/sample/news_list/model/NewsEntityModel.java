package news.agoda.com.sample.news_list.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * News Details, which can be represented on the UI.
 */
public class NewsEntityModel implements Parcelable {
    public static final Creator<NewsEntityModel> CREATOR = new Creator<NewsEntityModel>() {
        @Override
        public NewsEntityModel createFromParcel(Parcel in) {
            return new NewsEntityModel(in);
        }

        @Override
        public NewsEntityModel[] newArray(int size) {
            return new NewsEntityModel[size];
        }
    };
    private String mNewsTitle;
    private String mNewsImageUrl;
    private String mArticleUrl;
    private String mSummary;

    private NewsEntityModel(Builder builder) {
        mNewsTitle = builder.mNewsTitle;
        mNewsImageUrl = builder.mNewsImageUrl;
        mArticleUrl = builder.mArticleUrl;
        mSummary = builder.mSummary;
    }

    protected NewsEntityModel(Parcel in) {
        mNewsTitle = in.readString();
        mNewsImageUrl = in.readString();
        mArticleUrl = in.readString();
        mSummary = in.readString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getNewsTitle() {
        return mNewsTitle;
    }

    public String getNewsImageUrl() {
        return mNewsImageUrl;
    }

    public String getArticleUrl() {
        return mArticleUrl;
    }

    public String getSummary() {
        return mSummary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mNewsTitle);
        dest.writeString(mNewsImageUrl);
        dest.writeString(mArticleUrl);
        dest.writeString(mSummary);
    }

    public static final class Builder {
        private String mNewsTitle;
        private String mNewsImageUrl;
        private String mArticleUrl;
        private String mSummary;

        private Builder() {
        }

        public Builder withNewsTitle(String val) {
            mNewsTitle = val;
            return this;
        }

        public Builder withNewsImageUrl(String val) {
            mNewsImageUrl = val;
            return this;
        }

        public Builder withArticleUrl(String val) {
            mArticleUrl = val;
            return this;
        }

        public Builder withSummary(String val) {
            mSummary = val;
            return this;
        }

        public NewsEntityModel build() {
            return new NewsEntityModel(this);
        }
    }
}
