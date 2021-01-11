package com.leanhquan.notemanagementsystem.UI;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.leanhquan.notemanagementsystem.Model.Category;
import com.leanhquan.notemanagementsystem.Model.Piority;
import com.leanhquan.notemanagementsystem.Model.Status;
import com.leanhquan.notemanagementsystem.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NoteFragment extends Fragment {

    private CounterFab          fabAddNote;
    private MaterialEditText    edtNameNewNote;
    private MaterialSpinner     categorySpinner,
                                pioritySpinner,
                                statusSpinner;
    private Button              btnAdd,
                                btnCancel,
                                btnPlanDate;


    private FirebaseDatabase    database;
    private DatabaseReference   reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        database = FirebaseDatabase.getInstance();

        fabAddNote = view.findViewById(R.id.fabNote);
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddnewNote();
            }
        });
    }

    private void showDialogAddnewNote() {
        final AlertDialog optionDialog = new AlertDialog.Builder(getActivity()).create();
        optionDialog.setTitle("Add new note");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View addNote = inflater.inflate(R.layout.layout_create_new_note,null, false);
        edtNameNewNote = addNote.findViewById(R.id.edtNamenewNote);
        statusSpinner = addNote.findViewById(R.id.StatusSpinner);
        categorySpinner = addNote.findViewById(R.id.CategorySpinner);
        pioritySpinner = addNote.findViewById(R.id.PioritySpinner);
        btnPlanDate = addNote.findViewById(R.id.bntPlanDate);
        btnAdd = addNote.findViewById(R.id.btnAddnewNote);
        btnCancel = addNote.findViewById(R.id.btnCancelNote);

        optionDialog.setView(addNote);
        optionDialog.setIcon(R.drawable.ic_note);

        final Calendar c = Calendar.getInstance();
        final DatePickerDialog  StartTime = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Toast.makeText(getContext(), ""+ newDate.getTime(), Toast.LENGTH_SHORT).show();
            }

        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));


        reference = database.getReference().child("categories");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> nameCate = new ArrayList<>();
                for (DataSnapshot cate : snapshot.getChildren()){
                    String category = cate.child("name").getValue(String.class);
                    nameCate.add(category);
                }
                final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, nameCate);
                dataAdapter.notifyDataSetChanged();
                categorySpinner.setAdapter(dataAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference = database.getReference().child("piorities");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> namePiority = new ArrayList<>();
                for (DataSnapshot pio : snapshot.getChildren()){
                    String category = pio.child("name").getValue(String.class);
                    namePiority.add(category);
                }
                final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, namePiority);
                dataAdapter.notifyDataSetChanged();
                pioritySpinner.setAdapter(dataAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference = database.getReference().child("status");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final List<String> nameStatus = new ArrayList<>();
                for (DataSnapshot status : snapshot.getChildren()){
                    String category = status.child("name").getValue(String.class);
                    nameStatus.add(category);
                }
                final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, nameStatus);
                dataAdapter.notifyDataSetChanged();
                statusSpinner.setAdapter(dataAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnPlanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime.show();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: handle create note
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionDialog.dismiss();
            }
        });

        optionDialog.show();
    }



}
