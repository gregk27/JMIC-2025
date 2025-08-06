package ca.triagebot.triagebot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserInfoPageViewModel extends ViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<String> age;

    private MediatorLiveData<Boolean> valid;

    public MutableLiveData<String> getName(){
        return name;
    }

    public MutableLiveData<String> getAge(){
        return age;
    }

    public LiveData<Boolean> getValid() {
        return valid;
    }

    public UserInfoPageViewModel(){
        name = new MutableLiveData<>("");
        age = new MutableLiveData<>("");

        valid = new MediatorLiveData<>(false);
        valid.addSource(name, this::validate);
        valid.addSource(age, this::validate);
    }

    private void validate(Object input){
        valid.setValue(
                !name.getValue().isEmpty() &&
                !age.getValue().isEmpty()
        );
    }
}