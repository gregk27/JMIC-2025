package ca.triagebot.triagebot;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class StartPage extends WizardPage {

    private StartPageViewModel mViewModel;

    public static StartPage newInstance() { return new StartPage(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);

        view.findViewById(R.id.startButton).setOnClickListener((View v) -> {
            ((MainActivity) getActivity()).wizard.step(UserInfoPage.class);
        });

        // Only touch the language switch on a new instance of start page
        // The way this is handled for now is definitely a memory leak, but does the job
        if(savedInstanceState == null){
            LocaleListCompat locales = AppCompatDelegate.getApplicationLocales();
            final boolean initState = locales.get(0).getLanguage().equals("fr");

            Switch languageSwitch = view.findViewById(R.id.languageSwitch);
            languageSwitch.setOnCheckedChangeListener(null);
            languageSwitch.setChecked(initState);

            languageSwitch.setOnCheckedChangeListener((v, state) -> {
                String langStr = state ? "fr" : "en";
                if(!locales.get(0).getLanguage().equals(langStr)){
                    LocaleListCompat newLocale = LocaleListCompat.forLanguageTags(langStr);
                    AppCompatDelegate.setApplicationLocales(newLocale);
                }
            });
        }

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

    @Override
    public boolean getNextEnabled() {
        return true;
    }

    @Override
    public Class<? extends Fragment> saveAndStep(WizardData data) {
        // Step to user info page
        return UserInfoPage.class;
    }
}