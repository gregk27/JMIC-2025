package ca.triagebot.triagebot;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VitalsPage extends WizardPage {

    private VitalsPageViewModel mViewModel;

    public static VitalsPage newInstance() {
        return new VitalsPage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(VitalsPageViewModel.class);

        return inflater.inflate(R.layout.fragment_vitals_page, container, false);
    }

    @Override
    public boolean getNavigationVisibility() {
        return true;
    }

    @Override
    public boolean getNextEnabled() {
        return true;
    }

    @Override
    public Class<? extends Fragment> saveAndStep(WizardData data) {
        return EndPage.class;
    }
}