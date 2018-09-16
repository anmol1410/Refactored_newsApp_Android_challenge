package news.agoda.com.sample.news_list.listeners;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Triggered when te News is clicked or the like button is clicked.
 */
public interface NewsItemClickListener {

    /**
     * To listen to the new click, so that details page can be launched.
     *
     * @param newsPos Pos of the News clicked.
     */
    void onNewsClicked(int newsPos);

}
