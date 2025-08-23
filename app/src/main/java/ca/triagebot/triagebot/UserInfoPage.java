package ca.triagebot.triagebot;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ca.triagebot.triagebot.databinding.FragmentUserInfoPageBinding;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

public class UserInfoPage extends WizardPage {

    private UserInfoPageViewModel viewModel;

    public static UserInfoPage newInstance() {
        return new UserInfoPage();
    }

    @SuppressLint("WrongThread")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UserInfoPageViewModel.class);

        FragmentUserInfoPageBinding binding = FragmentUserInfoPageBinding.inflate(inflater);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.getValid().observe(getViewLifecycleOwner(), valid -> {
            onStateChanged();
        });

        binding.phoneInput.addTextChangedListener(new PhoneNumberFormattingTextWatcher("US"));

        return binding.getRoot();
    }

    @Override
    protected View getAutoKeyboardView(View root){
        return root.findViewById(R.id.nameInput);
    }

    @Override
    public boolean getNavigationVisibility() {
        return true;
    }

    @Override
    public boolean getNextEnabled() {
        return viewModel.getValid().getValue();
    }

    @Override
    public Class<? extends Fragment> saveAndStep(WizardData data) {
        // TODO: Save
        // Always step to vitals page
        return VitalsPage.class;
    }
}