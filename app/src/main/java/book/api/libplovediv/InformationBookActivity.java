package book.api.libplovediv;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class InformationBookActivity extends AppCompatActivity {
    ImageView coverBookDetailsIV;
    TextView titleDetailsTV, authorDetailsTV, yearDetailsTV, rateDetailsTV;
    Button reserveBookBtn;

    private FirebaseDatabase db;
    DatabaseReference ref;
    long maxid=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_book);

        coverBookDetailsIV = findViewById(R.id.coverDetailsBook);
        titleDetailsTV = findViewById(R.id.titleDetailsTV);
        authorDetailsTV = findViewById(R.id.authorDetailsTV);
        yearDetailsTV = findViewById(R.id.yearDetailsTV);
        rateDetailsTV = findViewById(R.id.rateDetailsTV);
        reserveBookBtn = findViewById(R.id.reserveBookButton);

        titleDetailsTV.setText(getIntent().getStringExtra("title"));
        authorDetailsTV.setText(getIntent().getStringExtra("author"));
        yearDetailsTV.setText(getIntent().getStringExtra("year"));
        rateDetailsTV.setText(getIntent().getStringExtra("rate"));

        Glide.with(this).load(getIntent().getStringExtra("imageCover")).into(coverBookDetailsIV);

        //save data to UserReservation database
        db = FirebaseDatabase.getInstance();
        ref = db.getReference().child("UserReservations");
        ref.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //maxid=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        //make reservation of a book
        reserveBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String author = authorDetailsTV.getText().toString();
                    String title = titleDetailsTV.getText().toString();
                    MyBooks reservations = new MyBooks(author,title);
                    ref.child(title).setValue(reservations);
                    reserveBookBtn.setEnabled(false);

                    Toast.makeText(InformationBookActivity.this,"Title: "+title+" author: " + author,Toast.LENGTH_SHORT).show();

            }
        });
    }
}