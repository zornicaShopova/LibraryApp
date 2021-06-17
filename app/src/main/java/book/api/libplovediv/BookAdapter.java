package book.api.libplovediv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    Context context;
    ArrayList<BookInfo> list;

    public BookAdapter(Context context, ArrayList<BookInfo> list) {
        this.context = context;
        this.list = list;
    }
// on click event for an item in the rv
    private  OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }
    public void OnRecyclerViewClickListener( OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
        return new BookViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        BookInfo bookInfo = list.get(position);
        holder.titleBookTV.setText(bookInfo.getTitleBook());
        holder.authorBookTV.setText(bookInfo.getAuthorBook());
        holder.yearBookTV.setText(bookInfo.getPublishedDate());
        holder.rateBookTV.setText(bookInfo.getRateBook());


        //ImageView : Glide Library
        Glide.with(context)
                .load(list.get(position).getUrl())
                .into(holder.bookCover);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView titleBookTV,authorBookTV,yearBookTV,rateBookTV,urlTV;
        ImageView bookCover;

        public BookViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);

            titleBookTV = itemView.findViewById(R.id.titleBook);
            authorBookTV = itemView.findViewById(R.id.authorBook);
            yearBookTV = itemView.findViewById(R.id.yearBook);
            rateBookTV = itemView.findViewById(R.id.rateBook);
            bookCover = itemView.findViewById(R.id.bookCover);
            urlTV = itemView.findViewById(R.id.url);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null && getAdapterPosition()!= RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
