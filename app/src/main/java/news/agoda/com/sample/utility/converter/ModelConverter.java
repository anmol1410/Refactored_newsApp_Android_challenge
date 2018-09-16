package news.agoda.com.sample.utility.converter;

import android.support.annotation.Nullable;

/**
 * Created by anmolsehgal on 31-08-2018.
 * <p>
 * Converter to convert One Model into Another.
 *
 * @param <S> Source Model.
 * @param <D> Destination Model.
 */
public interface ModelConverter<S, D> {

    @Nullable
    D convert(@Nullable S source);
}