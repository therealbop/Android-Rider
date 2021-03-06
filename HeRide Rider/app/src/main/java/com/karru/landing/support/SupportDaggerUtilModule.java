package com.karru.landing.support;

import com.karru.dagger.ActivityScoped;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import static com.karru.utility.Constants.SUPPORT;

/**
 * <h1>SupportDaggerUtilModule</h1>
 * used to provide models to dagger
 * @author 3Embed
 * @since on 21-03-2018.
 */
@Module
public class SupportDaggerUtilModule
{
    @Provides
    @ActivityScoped
    @Named(SUPPORT)
    CompositeDisposable provideCompositeDisposable()
    {
        return new CompositeDisposable();
    }
}
