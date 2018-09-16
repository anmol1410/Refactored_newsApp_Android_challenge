package news.agoda.com.sample.news_list.views;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import news.agoda.com.sample.R;
import news.agoda.com.sample.base.BaseActivity;
import news.agoda.com.sample.news_list.listeners.NewsInteractionListener;
import news.agoda.com.sample.news_list.model.NewsEntityModel;

public class NewsActivity extends BaseActivity implements NewsInteractionListener {
    private ProgressBar mPbLoader;
    private TextView mTvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        setupUi();
        // Load the particular Fragment for the UI.
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_container, NewsListFragment.newInstance())
                .commit();
    }

    /**
     * Initialize the UI components.
     */
    private void setupUi() {
        mTvError = (TextView) findViewById(R.id.tv_error);
        mPbLoader = (ProgressBar) findViewById(R.id.pb_loader);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        // Show/Hide the loader.
        mPbLoader.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoadingNewsError(boolean showError) {
        // Show/hide the error message.
        mTvError.setVisibility(showError ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onNewsClicked(NewsEntityModel newsEntityModel) {
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();

        if (findViewById(R.id.fl_news_details) == null) {
            // Case for Phone. Launch another fragment for details view, and add to the back stack.
            fm.add(R.id.fl_container, NewsDetailsFragment.newInstance(newsEntityModel))
                    .addToBackStack(null);
        } else {
            // Case for Tablets. Set the Details in the adjacent Fragment on the same screen.
            fm.replace(R.id.fl_news_details, NewsDetailsFragment.newInstance(newsEntityModel));
        }
        fm.commit();
    }
}
