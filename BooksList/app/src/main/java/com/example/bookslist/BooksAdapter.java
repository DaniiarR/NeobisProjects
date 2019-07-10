package com.example.bookslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        // set the title
        TextView titleTextView = listItemView.findViewById(R.id.title_textView);
        titleTextView.setText(currentBook.getTitle());

        // set the author names
        TextView authorsTextView = listItemView.findViewById(R.id.authors_textView);
        String authors = Arrays.toString(currentBook.getAuthors());
        authorsTextView.setText(authors.substring(1, authors.length() - 1));

        // set the published date
        TextView publishedDateTextView = listItemView.findViewById(R.id.publishedDate_textView);
        publishedDateTextView.setText(currentBook.getPublishedDate());

        // set the pages amount
        TextView pagesTextView = listItemView.findViewById(R.id.pages_textView);
        pagesTextView.setText(currentBook.getPages() + " pages");

        // set the average rating
        TextView ratingTextView = listItemView.findViewById(R.id.rating_textView);
        double rating = currentBook.getAverageRating();
        if (rating != 0.0) {
            ratingTextView.setText(String.valueOf(currentBook.getAverageRating()));
        } else {
            ratingTextView.setText("N/A");
        }
        // set the thumbnail image
        ImageView thumbnailImageView = listItemView.findViewById(R.id.thumbnail_imageView);
        Picasso.get().load(currentBook.getThumbnailUrl()).into(thumbnailImageView);

        return listItemView;
    }
}
