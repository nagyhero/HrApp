package com.supportingonline.hrapp.Activites.Home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.supportingonline.hrapp.Model.HeadCardModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    public ArrayList<HeadCardModel> headCardList=new ArrayList<>();

    private MutableLiveData<ArrayList<HeadCardModel>> liveData=new MutableLiveData<>();

    public MutableLiveData<ArrayList<HeadCardModel>> getInitHeadCards(){
        liveData.setValue(headCardList);
        return liveData;
    }

    public void addNewCard(HeadCardModel model){
        headCardList.add(model);
        liveData.setValue(headCardList);
    }

}
