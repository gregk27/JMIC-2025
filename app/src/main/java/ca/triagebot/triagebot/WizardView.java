package ca.triagebot.triagebot;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

public class WizardView {

    private Context context;
    private FragmentManager fragmentManager;
    private FragmentContainerView view;

    public WizardView(Context context, FragmentManager fm, FragmentContainerView view, Class<? extends Fragment> initialPage){
        this.context = context;
        this.fragmentManager = fm;
        this.view = view;

        fragmentManager
                .beginTransaction()
                .replace(R.id.pager, initialPage, null)
                .addToBackStack("Start Page")
                .commit();
    }

    public void Step(Class<? extends Fragment> page){
        fragmentManager
                .beginTransaction()
                .replace(R.id.pager, page, null)
                .addToBackStack("Page")
                .commit();
    }

    public void stepBack(){
        fragmentManager.popBackStack();
    }

    @Override
    public void onClick(View view) {
        if (view == nextButton){
            Toast.makeText(context, "Next Pressed", Toast.LENGTH_SHORT).show();
        }
        else if (view == backButton){
            stepBack();
        }
    }
}
