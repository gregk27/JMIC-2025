package ca.triagebot.triagebot;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ca.triagebot.triagebot.databinding.FragmentUserInfoPageBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserInfoPage extends WizardPage {

    private UserInfoPageViewModel viewModel;

    public static UserInfoPage newInstance() {
        return new UserInfoPage();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_info_page, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UserInfoPageViewModel.class);
        FragmentUserInfoPageBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_user_info_page);
        binding.setViewmodel(viewModel);
    }

    @Override
    public boolean getNavigationVisibility() {
        return true;
    }
}