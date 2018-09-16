package news.agoda.com.sample.news_list.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;

import news.agoda.com.sample.R;
import news.agoda.com.sample.news_list.model.NewsEntityModel;

public class NewsDetailsFragment extends Fragment {
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String STORY_URL_KEY = "STORY_URL_KEY";
    private static final String SUMMARY_URL_KEY = "SUMMARY_URL_KEY";
    private static final String IMAGE_URL_KEY = "IMAGE_URL_KEY";

    private String mStoryURL;
    private String mNewsTitle;
    private String mNewsSummary;
    private String mImageURL;

    public static NewsDetailsFragment newInstance(NewsEntityModel newsEntityModel) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, newsEntityModel.getNewsTitle());
        args.putString(STORY_URL_KEY, newsEntityModel.getArticleUrl());
        args.putString(SUMMARY_URL_KEY, newsEntityModel.getSummary());
        args.putString(IMAGE_URL_KEY, newsEntityModel.getNewsImageUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsTitle = getArguments().getString(TITLE_KEY);
            mStoryURL = getArguments().getString(STORY_URL_KEY);
            mNewsSummary = getArguments().getString(SUMMARY_URL_KEY);
            mImageURL = getArguments().getString(IMAGE_URL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleView = (TextView) view.findViewById(R.id.title);
        DraweeView imageView = (DraweeView) view.findViewById(R.id.news_image);
        TextView summaryView = (TextView) view.findViewById(R.id.summary_content);
        view.findViewById(R.id.btn_view_full_Story).setOnClickListener(new ButtonClickListener());

        titleView.setText(mNewsTitle);
        summaryView.setText(mNewsSummary);

        if (mImageURL != null) {
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(Uri.parse(mImageURL)))
                    .setOldController(imageView.getController()).build();
            imageView.setController(draweeController);
        }
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_view_full_Story:
                    if (mStoryURL == null || mStoryURL.isEmpty()) {
                        Toast.makeText(getContext(), R.string.err_full_story_invalid_uri, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mStoryURL));
                    startActivity(intent);
            }
        }
    }
}
