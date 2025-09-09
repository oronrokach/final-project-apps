package com.example.finalproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase myData;
    DatabaseReference myRef;
    FirebaseAuth myAuth;

    Dialog d;


    Button singUpRegister;
    EditText emailRegister,passwordRegister,FullNameRegister,BirthDateRegister,PhoneNumberRegister;



    EditText passwordLogin,emailLogin;
    Button signUpLogin,LoginLogin;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //firebase
            myAuth=FirebaseAuth.getInstance();

            myData=FirebaseDatabase.getInstance();
            myRef=myData.getReference("Users");


            //edit text log in
            passwordLogin=findViewById(R.id.passwordLogin);
            emailLogin=findViewById(R.id.emailLogin);
            //buttons log in
            signUpLogin=findViewById(R.id.signUpLogin);
            LoginLogin=findViewById(R.id.LoginLogin);
            signUpLogin.setOnClickListener(this);
            LoginLogin.setOnClickListener(this);


            //shared prefrences
            sp=getSharedPreferences("LoginSP",0);
            String stremailLogin=sp.getString("email",null);
            String strpasswordLogin=sp.getString("password",null);
            if(stremailLogin!=null&&strpasswordLogin!=null)
            {
                emailLogin.setText(stremailLogin);
                passwordLogin.setText(strpasswordLogin);
            }

            return insets;
        });
    }
    //func on clock call all function
    @Override
    public void onClick(View v) {
        if(v==signUpLogin)
        {
            openDialog();
        }
        if(v==singUpRegister)
        {
            register();
        }
        if (v==LoginLogin)
        {
            if(!passwordLogin.getText().toString().isEmpty() && !emailLogin.getText().toString().isEmpty())
                login();
            else
                Toast.makeText(MainActivity.this, "fill text", Toast.LENGTH_SHORT).show();
        }
    }

    //log in function log you in only if user register
    private void login() {
        myAuth.signInWithEmailAndPassword(emailLogin.getText().toString(),passwordLogin.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Log In Success", Toast.LENGTH_SHORT).show();
                    //shared prefrences
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("email",emailLogin.getText().toString());
                    editor.putString("password",passwordLogin.getText().toString());
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this,HomePage.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    // func create user in firebase
    private void register() {
        d.dismiss();
        myAuth.createUserWithEmailAndPassword(emailRegister.getText().toString(),passwordRegister.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "user created", Toast.LENGTH_SHORT).show();
                    Users user=new Users(FullNameRegister.getText().toString(),emailRegister.getText().toString(),passwordRegister.getText().toString(),BirthDateRegister.getText().toString(),PhoneNumberRegister.getText().toString());
                    myRef.child(myAuth.getCurrentUser().getUid()).setValue(user);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //dialog function
    private void openDialog()
    {
        d=new Dialog(this);
        d.setContentView(R.layout.register);
        d.setTitle("register");
        FullNameRegister=d.findViewById(R.id.FullNameRegister);
        BirthDateRegister=d.findViewById(R.id.BirthDateRegister);
        PhoneNumberRegister=d.findViewById(R.id.PhoneNumberRegister);
        emailRegister=d.findViewById(R.id.emailRegister);
        passwordRegister=d.findViewById(R.id.passwordRegister);
        singUpRegister=d.findViewById(R.id.signUpRegister);
        singUpRegister.setOnClickListener(this);
        d.show();
    }
}