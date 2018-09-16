package news.agoda.com.sample.base;

/**
 * Created by anmolsehgal on 31-08-2018.
 *
 * @param <T> Type of View.
 */
public interface BaseView<T> {

    /**
     * Let it be the responsibility of the Presenter itself, to chose the presenter for its View.     *
     *
     * @param presenter Presenter for the View.
     */
    void setPresenter(T presenter);
}