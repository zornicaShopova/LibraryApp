package book.api.libplovediv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;


public class ReservationBookAdapter extends RecyclerView.Adapter<ReservationBookAdapter.BookViewHolder> {
    private Context context;
    private final List<MyBooks> bookInfoList;

    public ReservationBookAdapter(Context context, List<MyBooks> bookInfoList) {
        this.context = context;
        this.bookInfoList = bookInfoList;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.reservation_book_info,parent,false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        MyBooks book = bookInfoList.get(position);
        holder.authorB.setText(book.getAuthorBook());
        holder.titleB.setText(book.getTitleBook());
    }

    @Override
    public int getItemCount() {
        return bookInfoList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView titleB,authorB;
        public BookViewHolder(View itemView) {
            super(itemView);
            titleB = itemView.findViewById(R.id.titleB);
            authorB = itemView.findViewById(R.id.authorB);


        }
    }


}
