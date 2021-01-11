package com.leanhquan.notemanagementsystem.UI;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leanhquan.notemanagementsystem.Common.Common;
import com.leanhquan.notemanagementsystem.Model.User;
import com.leanhquan.notemanagementsystem.R;
import com.leanhquan.notemanagementsystem.RegisterActivity;


public class ProfileFragment extends Fragment {

    private Button btnSignup;
    private EditText edtEmail,
            edtUsername,
            edtPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        edtUsername.setText(Common.currentUser.getUsername());
        edtEmail.setText(Common.currentUser.getEmail());
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = edtUsername.getText().toString();
                String pass = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();

                Toast.makeText(getContext(), "waitinggg", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validateEmail() {
        String valueEmai = edtEmail.getText().toString();
        if (valueEmai.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(valueEmai).matches()) {
            return false;
        } else {
            return true;
        }
    }

    private void init(View view) {
        btnSignup = view.findViewById(R.id.btnProfile);
        edtEmail = view.findViewById(R.id.edtEmailProfile);
        edtUsername = view.findViewById(R.id.edtUsernameProfile);
    }

}
