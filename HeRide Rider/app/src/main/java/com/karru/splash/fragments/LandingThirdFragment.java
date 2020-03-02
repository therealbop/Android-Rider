package com.karru.splash.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.heride.rider.R;
import com.karru.util.AppTypeface;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

public class LandingThirdFragment extends DaggerFragment
{
    @BindView(R.id.tv_landing_label3) TextView tv_landing_label3;

    @Inject AppTypeface appTypeface;
    @Inject public LandingThirdFragment() {}

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing_third, container, false);
        unbinder = ButterKnife.bind(this,view);
        tv_landing_label3.setTypeface(appTypeface.getPro_News());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
