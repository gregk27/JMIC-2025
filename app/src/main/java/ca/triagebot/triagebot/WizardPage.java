package ca.triagebot.triagebot;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class WizardPage extends Fragment {

    protected OnStateChangeListener changeListener;

    public abstract boolean getNavigationVisibility();
    public abstract boolean getNextEnabled();
    public abstract Class<? extends Fragment> saveAndStep(WizardData data);

    protected void onStateChanged(){
        if(changeListener != null){
            changeListener.OnStateChanged(this, getNextEnabled());
        }
    }

    public void setStateChangeListener(OnStateChangeListener listener){
        changeListener = listener;
        onStateChanged();
    }

    public interface OnStateChangeListener {
        void OnStateChanged(WizardPage sender, boolean nextEnabled);
    }

    // Auto keyboard functionality
    protected View getAutoKeyboardView(View root){
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        View autoView = getAutoKeyboardView(view);

        // Open the keyboard automatically if view provided
        if (autoView != null){
            InputMethodManager imm = getContext().getSystemService(InputMethodManager.class);
            if (imm != null) {
                autoView.requestFocus();
                imm.showSoftInput(autoView, InputMethodManager.SHOW_IMPLICIT);
            }
        }

        super.onViewCreated(view, savedInstanceState);
    }
}
