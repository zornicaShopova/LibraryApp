package book.api.libplovediv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ImageView bookCover;
    TextView urlTV;
    RecyclerView rv;
    DatabaseReference database;
    BookAdapter bookAdapter;
    ArrayList<BookInfo> list;
    Context context;
    BottomNavigationView btnView;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rv = findViewById(R.id.bookList);
        btnView = findViewById(R.id.bottomNavigationView);

        //connect to the firebase database
        database = FirebaseDatabase.getInstance().getReference("BookShelf");
        //change the size based on adapter behavior
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        bookAdapter = new BookAdapter(HomeActivity.this, list);
        rv.setAdapter(bookAdapter);

        clearAll();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // display book data from database
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BookInfo book = dataSnapshot.getValue(BookInfo.class);
                    list.add(book);

                }
                bookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bookAdapter.OnRecyclerViewClickListener(new BookAdapter.OnRecyclerViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                // Toast.makeText(HomeActivity.this, "Position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, InformationBookActivity.class);
                intent.putExtra("imageCover", list.get(position).getUrl());
                intent.putExtra("title", list.get(position).getTitleBook());
                intent.putExtra("author", list.get(position).getAuthorBook());
                intent.putExtra("year", list.get(position).getPublishedDate());
                intent.putExtra("rate", list.get(position).getRateBook());
                startActivity(intent);
            }
        });

    }

    private void clearAll() {
        if (list != null) {
            list.clear();

            if (bookAdapter != null) {
                bookAdapter.notifyDataSetChanged();
            }
        }
        btnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.scan:
                        Intent intent2 = new Intent(getApplicationContext(),ScanActivity.class);
                        startActivity(intent2);
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.searchBooks:
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Books:
                        Intent intent1 = new Intent(getApplicationContext(),ReservationBooksActivity.class);
                        startActivity(intent1);

                        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }
}