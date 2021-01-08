package com.leanhquan.notemanagementsystem.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanhquan.notemanagementsystem.R;

public class CategoryFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //todo: create mode Category, create view holder, import firebase, research getday, show recyCategory
        //todo: create Contextmenu -> delete and update / create layout for dialog update and delete category

        return inflater.inflate(R.layout.fragment_category, container, false);
    }
}
