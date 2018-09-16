package news.agoda.com.sample.base.usecase;

/**
 * Created by anmolsehgal on 31-08-2018.
 * The basic implementation which every Use Case Executor must have.
 */
interface UseCaseScheduler {

    /**
     * Every Use Case Executor must execute the use case.
     *
     * @param runnable Runnable to run.
     */
    void execute(Runnable runnable);

    /**
     * Every Use Case Executor must send the Success response back to the caller.
     *
     * @param response        Result to send back.
     * @param useCaseCallback Callback which will receive the response.
     * @param <V>             Type of the Response which Callback receives.
     */
    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    /**
     * Every Use Case Executor must send the Error response back to the caller.
     *
     * @param useCaseCallback Callback which will receive the response.
     * @param <V>             Type of the Response which Callback receives.
     */
    <V extends UseCase.ResponseValue> void onError(
            final UseCase.UseCaseCallback<V> useCaseCallback);
}
