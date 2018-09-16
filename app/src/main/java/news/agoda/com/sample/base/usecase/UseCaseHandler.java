package news.agoda.com.sample.base.usecase;

/**
 * Created by anmolsehgal on 31-08-2018.
 * Use case manager. It can execute various use cases, so acts as the Wrapper for all use cases.
 */
public class UseCaseHandler {

    /**
     * Singleton Instance.
     */
    private static UseCaseHandler INSTANCE;

    private final UseCaseScheduler mUseCaseScheduler;

    private UseCaseHandler(UseCaseScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }

    /**
     * Execute the Use-case.
     *
     * @param useCase  Use case to execute.
     * @param values   request values for the use case.
     * @param callback Callback which receives the result of the Use case execution.
     * @param <T>      Type of the Request values.
     * @param <R>      Type of the Response which Callback receives.
     */
    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            final UseCase<T, R> useCase, T values, UseCase.UseCaseCallback<R> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper(callback, this));

        // Execute the use case.
        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    /**
     * Notify That the USE case was executed successfully.
     *
     * @param response        Result of Use case execution.
     * @param useCaseCallback Callback which receives the result of the Use case execution.
     * @param <V>             Type of the Response which Callback receives.
     */
    public <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                                 final UseCase.UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    /**
     * To notify that some error occurred while USE case execution.
     *
     * @param useCaseCallback Callback which receives the result of the Use case execution.
     * @param <V>             Type of the Response which Callback receives.
     */
    private <V extends UseCase.ResponseValue> void notifyError(
            final UseCase.UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.onError(useCaseCallback);
    }

    /**
     * Callback Wrapper which receives the callback for Use case execution.
     *
     * @param <V> Type of the Response which Callback receives.
     */
    private static final class UiCallbackWrapper<V extends UseCase.ResponseValue> implements
            UseCase.UseCaseCallback<V> {
        private final UseCase.UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        UiCallbackWrapper(UseCase.UseCaseCallback<V> callback,
                          UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError() {
            mUseCaseHandler.notifyError(mCallback);
        }
    }
}
