package ca.triagebot.triagebot;

import androidx.annotation.NonNull;

public class WizardData implements Cloneable {

    @NonNull
    @Override
    public WizardData clone() {
        try {
            return (WizardData) super.clone();
        } catch (Exception e){
            return null;
        }
    }
}
