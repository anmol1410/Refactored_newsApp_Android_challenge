package news.agoda.com.sample.utility.parsers;

import org.json.JSONException;

/**
 * Parsers to parse One Type of data into Another.
 *
 * @param <S> Source type of Data.
 * @param <T> Target/Destination type of Data.
 */
public interface IParser<S, T> {
    T parse(S source) throws JSONException;
}
