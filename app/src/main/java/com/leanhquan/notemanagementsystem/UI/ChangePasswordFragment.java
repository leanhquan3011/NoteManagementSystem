package com.leanhquan.notemanagementsystem.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leanhquan.notemanagementsystem.Common.Common;
import com.leanhquan.notemanagementsystem.MainActivity;
import com.leanhquan.notemanagementsystem.Model.User;
import com.leanhquan.notemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;


public class ChangePasswordFragment extends Fragment {

    private DatabaseReference ref;
    private EditText CurPass;
    private EditText NewPass;
    private EditText ConfirmPass;
    private Button save;
    private Button exit;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_change_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CurPass = view.findViewById(R.id.current);
        NewPass = view.findViewById(R.id.newpassword);
        ConfirmPass =view.findViewById(R.id.confirmPass);
        save = view.findViewById(R.id.BtnSave);
        exit = view.findViewById(R.id.BtnExit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference UserRef = ref.child("user").child(Common.currentUser.getUsername()).child("password");
                if (CurPass.getText().toString().equals(Common.currentUser.getPassword())) {
                    UserRef.setValue(NewPass.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                           Common.currentUser.setPassword(NewPass.getText().toString());
                           UserRef.setValue(NewPass.getText().toString());
                        }
                    });
                }
            }
        });
    }
}
