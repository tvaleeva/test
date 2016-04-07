package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.rpc.AsyncCallback;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by Bublik on 07.04.2016.
 */
public abstract class WrappedCallback<T> extends ru.amfitel.task.client.callback.AsyncCallback<T> {

    private AsyncCallback<T> wrapped;

    public WrappedCallback(AsyncCallback<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void onFailure(Throwable throwable) {
        actionOnFailure(throwable);
        wrapped.onFailure(throwable);
    }

    @Override
    public void onSuccess(T t) {
        actionOnSuccess(t);
        wrapped.onSuccess(t);
    }

    @Override
    protected void onConstractViolation(Set<ConstraintViolation<?>> violations) {
        onConstracintViolation(violations);
        super.onConstractViolation(violations);
    }

    public abstract void onConstracintViolation(Set<ConstraintViolation<?>> violations);

    public abstract void actionOnSuccess(T t);

    public abstract void actionOnFailure(Throwable throwable);
}
