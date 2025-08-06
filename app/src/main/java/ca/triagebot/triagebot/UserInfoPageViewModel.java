package ca.triagebot.triagebot;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInfoPageViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> age;

    public MutableLiveData<String> getName(){
        return name;
    }

    public MutableLiveData<String> getAge(){
        return age;
    }

    public UserInfoPageViewModel(){
        name = new MutableLiveData<>("");
        age = new MutableLiveData<>("");
    }
}