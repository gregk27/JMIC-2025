package ca.triagebot.triagebot;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartPage extends WizardPage {

    private StartPageViewModel mViewModel;

    public static StartPage newInstance() { return new StartPage(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);

        view.findViewById(R.id.startButton).setOnClickListener((View v) -> {
            ((MainActivity) getActivity()).wizard.Step(UserInfoPage.class);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StartPageViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public boolean getNavigationVisibility() {
        return false;
    }
}