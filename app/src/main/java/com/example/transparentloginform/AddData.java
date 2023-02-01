package com.example.transparentloginform;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.GetTokenResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddData extends Fragment {
    EditText Nickname , Name , PhoneN ;
    Button bt ;
    FirebaseServices fbs ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddData.
     */
    // TODO: Rename and change types and number of parameters
    public static AddData newInstance(String param1, String param2) {
        AddData fragment = new AddData();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void onStart()
    {
        super.onStart();
        connectcomponents();
    }

    private void connectcomponents() {
        Nickname = getView().findViewById(R.id.ETNickNameAddData);
        Name = getView().findViewById(R.id.ETNameAddData);
        PhoneN = getView().findViewById(R.id.ETPhoneNumberAddData);
        fbs = FirebaseServices.getInstance();
        bt = getView().findViewById(R.id.BTNAddData);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToFireStore();
            }
        });
    }

    private void AddToFireStore() {
        String nickname = Nickname.getText().toString() ;
        String name = Name.getText().toString() ;
        String phone = PhoneN.getText().toString() ;
        if(nickname.trim().isEmpty() || name.trim().isEmpty() || phone.trim().isEmpty() )
        {
            Toast.makeText(getActivity(), "Fill everything You Stupid", Toast.LENGTH_SHORT).show();
            return;
        }
        Data data = new Data(name , nickname , phone ) ;
        fbs.getFire().collection("Data").document("LA").set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Data has been Added finally", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Something went Wrong its your problem ofc", Toast.LENGTH_SHORT).show();
                    }
                }) ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_data, container, false);
    }
}