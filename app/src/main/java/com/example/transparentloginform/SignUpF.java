package com.example.transparentloginform;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.auth.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpF extends Fragment {
    EditText username , password , repassword ;
    Button bt ;
    FirebaseServices fbs ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpF() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpF.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpF newInstance(String param1, String param2) {
        SignUpF fragment = new SignUpF();
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
        fbs = FirebaseServices.getInstance() ;
        username = getView().findViewById(R.id.ETUsernameSignUP);
        password = getView().findViewById(R.id.ETPasswordSignUp);
        repassword = getView().findViewById(R.id.ETRePasswordSignUp);
        bt = getView().findViewById(R.id.SignUpButtonSignUp);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString() ;
                String Password = password.getText().toString() ;
                String RePassword = repassword.getText().toString() ;
                if(Username.trim().isEmpty() || Password.trim().isEmpty() || RePassword.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "fill everything pls", Toast.LENGTH_SHORT).show() ;
                    return ;
                }
                if(!isEmailValid(Username)) {
                    Toast.makeText(getActivity(), "WrongEmail", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!isValidPassword(Password))
                {
                    Toast.makeText(getActivity(), "Wrong password try again with something else", Toast.LENGTH_SHORT).show() ;
                    return ;
                }
                if(!Password.matches(RePassword))
                {
                    Toast.makeText(getActivity(), "passwords dosn't matches", Toast.LENGTH_SHORT).show() ;
                    return ;
                }
                fbs.getAuth().createUserWithEmailAndPassword(Username,Password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "welcome to the new world", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "something went wrong lol", Toast.LENGTH_SHORT).show();
                        }
                    }
                }) ;
            }
        });
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
}