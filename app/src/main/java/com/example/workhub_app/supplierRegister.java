package com.example.workhub_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class supplierRegister extends AppCompatActivity {

    EditText cname, oname, addname, mail, pw,phone;
    Button btnSign;
    FirebaseAuth fdbAuth;
    Supplier sup;
    DatabaseReference dbDetails;

    private void clearControlls() {
        cname.setText("");
        oname.setText("");
        addname.setText("");
        mail.setText("");
        pw.setText("");
        phone.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_register);

        cname = findViewById(R.id.comname);
        oname = findViewById(R.id.ownername);
        addname = findViewById(R.id.add);
        mail = findViewById(R.id.un);
        pw = findViewById(R.id.pwenter);
        phone = findViewById(R.id.phn);

        btnSign = findViewById(R.id.btnlogin1);
        fdbAuth = FirebaseAuth.getInstance();

        sup = new Supplier();

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = mail.getText().toString().trim();
                String pw1 = pw.getText().toString().trim();
                final String comname = cname.getText().toString().trim();
                final String owner = oname.getText().toString().trim();
                final String add = addname.getText().toString().trim();
                final String phone1 = phone.getText().toString().trim();

                if (TextUtils.isEmpty(mail.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter an E-mail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pw.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please Enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(cname.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter a Company Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(oname.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter an Owner Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(addname.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter the Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please Enter the Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pw1.length() < 6) {
                    pw.setError("Password must be >= 6 characters");
                    return;
                }
                if (phone1.length() < 10) {
                    pw.setError("Enter a valid number");
                    return;
                }
                //register user in firebase
                fdbAuth.createUserWithEmailAndPassword(email, pw1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checks whether registration is successful or nor
                        if (task.isSuccessful()) {
                            //display a toast msg if login credentials are created
                            Toast.makeText(getApplicationContext(),"Login credentials are created",Toast.LENGTH_SHORT).show();
                            //save supplier details
                            dbDetails = FirebaseDatabase.getInstance().getReference().child("Supplier_Details");
                            sup.setCompanyName(comname);
                            sup.setOwnerName(owner);
                            sup.setAddress(add);
                            sup.setEmail(email);
                            sup.setPhone(phone1);
                            //insert to db
                            dbDetails.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(sup).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        successMsg();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Error...!" + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }}
                            });
                            clearControlls();
                        } else {
                            Toast.makeText(getApplicationContext(), "Something is went wrong.Please check all the fields & try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });









            }
        });


    }


    Button btnlog, btnsign, btnlg;

    @Override
    protected void onResume() {
        super.onResume();

        btnlog = findViewById(R.id.login);
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(supplierRegister.this, login.class);
                startActivity(intent);

            }
        });
    }

    protected void successMsg() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.success_msgbox);

        dialog.setTitle("Success Message");

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = dialog.findViewById(R.id.textView5);
        TextView textView1 = dialog.findViewById(R.id.textView6);
        Button btnsucesslogin = dialog.findViewById(R.id.btnsucceslog);

        dialog.show();

        btnsucesslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(supplierRegister.this, login.class);
                startActivity(intent);
            }
        });

    }
}
