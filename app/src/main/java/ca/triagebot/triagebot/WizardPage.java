package ca.triagebot.triagebot;

import androidx.fragment.app.Fragment;

public abstract class WizardPage extends Fragment {

    protected OnStateChangeListener changeListener;

    public abstract boolean getNavigationVisibility();
    public abstract boolean getNextEnabled();

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
}
