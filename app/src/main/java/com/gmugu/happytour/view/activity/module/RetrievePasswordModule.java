package com.gmugu.happytour.view.activity.module;

import com.gmugu.happytour.data.api.IApiService;
import com.gmugu.happytour.presenter.IRetrievePasswordPresenter;
import com.gmugu.happytour.presenter.impl.RetrievePasswordPresenterImpl;
import com.gmugu.happytour.view.IRetrievePasswordView;
import com.gmugu.happytour.view.activity.RetrievePasswordActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mugu on 16-4-9 下午2:41.
 */
@Module
public class RetrievePasswordModule {

    private RetrievePasswordActivity retrievePasswordActivity;

    public RetrievePasswordModule(RetrievePasswordActivity retrievePasswordActivity) {
        this.retrievePasswordActivity = retrievePasswordActivity;
    }

    @Provides
    public IRetrievePasswordView provideView() {
        return retrievePasswordActivity;
    }

    @Provides
    public IRetrievePasswordPresenter providePresenter(IRetrievePasswordView view, IApiService apiService) {
        return new RetrievePasswordPresenterImpl(view, apiService);
    }
}
