package ca.triagebot.triagebot;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.UUID;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

public class WizardView implements View.OnClickListener, FragmentManager.OnBackStackChangedListener {

    private Context context;
    private FragmentManager fragmentManager;
    private View view;
    private View navigation;
    private Button nextButton, backButton;

    private WizardPage currentPage;

    public WizardView(Context context, FragmentManager fm, View view, Class<? extends Fragment> initialPage){
        this.context = context;
        this.fragmentManager = fm;
        this.view = view;

        navigation = view.findViewById(R.id.navView);

        nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);


        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager
                .beginTransaction()
                .add(R.id.pager, initialPage, null, "Start Page")
                .addToBackStack("Start Page")
                .commit();

        navigation.setVisibility(View.GONE);
    }

    public void Step(Class<? extends Fragment> page){
        String tag = "Page_" + UUID.randomUUID();
        fragmentManager
                .beginTransaction()
                .replace(R.id.pager, page, null, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void stepBack(){
        fragmentManager.popBackStack();
    }

    @Override
    public void onBackStackChanged() {
        String tag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment frag = fragmentManager.findFragmentByTag(tag);
        WizardPage page = (WizardPage) frag;

        currentPage = page;

        if (page.getNavigationVisibility())
            navigation.setVisibility(View.VISIBLE);
        else
            navigation.setVisibility(View.GONE);
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
