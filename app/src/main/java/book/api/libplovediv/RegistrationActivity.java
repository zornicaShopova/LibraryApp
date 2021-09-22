package book.api.libplovediv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText fullnameET, emailET ,passwordET;
    Button registerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        fullnameET = findViewById(R.id.fullnameEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        registerB = findViewById(R.id.registerButton);

        //push data to the database
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullnameInput = fullnameET.getText().toString().trim();
                String emailInput = emailET.getText().toString();
                String passInput = passwordET.getText().toString();

                if (fullnameInput.isEmpty()) {
                    fullnameET.setError("Full name is required!");
                    fullnameET.requestFocus();
                    return;
                }
                if (emailInput.isEmpty()) {
                    emailET.setError("Email is required!");
                    emailET.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    emailET.setError("Please provide valid email!");
                    emailET.requestFocus();
                    return;
                }
                if (passInput.isEmpty()) {
                    passwordET.setError("Password is required!");
                    passwordET.requestFocus();
                    return;
                }
                if (passInput.length() < 6) {
                    passwordET.setError("Min password length should be 6 characters!");
                    passwordET.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailInput, passInput)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(fullnameInput, emailInput, passInput);
                                    FirebaseDatabase.getInstance().getReference("RegisteredUser")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegistrationActivity.this, "Successful registration!", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(RegistrationActivity.this, "Failed to register!Try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Failed to register!Try again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                fullnameET.setText("");
                emailET.setText("");
                passwordET.setText("");


            }
        });
    }
}