package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tvaleeva
 * @since 31.03.2016
 */
public abstract class FailureIgnoreCallback<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Throwable throwable) {

    }

    @Override
    public abstract void onSuccess(T t);
}
