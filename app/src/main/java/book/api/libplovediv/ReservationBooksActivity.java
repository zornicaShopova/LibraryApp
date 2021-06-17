package book.api.libplovediv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReservationBooksActivity extends AppCompatActivity {
    ReservationBookAdapter adapter;
    List<MyBooks> bookInfoList;
    RecyclerView reservationBookList;
    DatabaseReference ref;
    public static TextView scanResultTV;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_books);
        reservationBookList = findViewById(R.id.reservationList);

        scanResultTV = findViewById(R.id.scanResultTV);
        scanResultTV.setText("Code");
        click = findViewById(R.id.click);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(ReservationBooksActivity.this,scanResultTV.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        //firebase
        ref = FirebaseDatabase.getInstance().getReference("MyBooks");
        reservationBookList.setHasFixedSize(true);
        reservationBookList.setLayoutManager(new LinearLayoutManager(this));

        bookInfoList = new ArrayList<>();
        adapter = new ReservationBookAdapter(ReservationBooksActivity.this, bookInfoList);
        reservationBookList.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookInfoList.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    MyBooks book = data.getValue(MyBooks.class);
                    bookInfoList.add(book);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}