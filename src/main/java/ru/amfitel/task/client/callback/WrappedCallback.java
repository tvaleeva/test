package ru.amfitel.task.client.callback;

import com.google.gwt.user.client.rpc.AsyncCallback;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

/**
 * Created by Bublik on 07.04.2016.
 */
public class WrappedCallback<T> extends ru.amfitel.task.client.callback.AsyncCallback<T> {

    private List<AsyncCallback<T>> wrapped;

    public WrappedCallback(List<AsyncCallback<T>> callbacks){
        this.wrapped = callbacks;
    }



    @Override
    public void onFailure(Throwable throwable) {
        super.onFailure(throwable);
        for (AsyncCallback<T> asyncCallback : wrapped){
            asyncCallback.onFailure(throwable);
        }
    }

    @Override
    public void onSuccess(T t) {
        for (AsyncCallback<T> asyncCallback : wrapped){
            asyncCallback.onSuccess(t);
        }
    }

    @Override
    protected void onConstractViolation(Set<ConstraintViolation<?>> violations) {
        super.onConstractViolation(violations);
    }

}
