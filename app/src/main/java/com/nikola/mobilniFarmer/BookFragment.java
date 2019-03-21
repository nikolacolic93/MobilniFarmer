package com.nikola.mobilniFarmer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookFragment extends Fragment  {

    ArrayList<Integer> images;
    ArrayList<String> names;
    ListView listViewBook;
    TextView note;
    OnBookRecordListener bookRecordListener;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        images = new ArrayList<>();
        Bundle bundle = getArguments();

        if(bundle!=null){
            images = bundle.getIntegerArrayList("images");
            names = bundle.getStringArrayList("names");

        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book,container,false);

        listViewBook = (ListView) v.findViewById(R.id.listViewBook);
        note = (TextView) v.findViewById(R.id.bookNote);
        CustomAdapter adapter = new CustomAdapter();
        listViewBook.setAdapter(adapter);

        return v;
    }

    public interface OnBookRecordListener{
        void recordCulture(String culture);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBookRecordListener) {
            bookRecordListener = (OnBookRecordListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBookRecordListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        bookRecordListener = null;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.fragment_book_element, null);

            ImageView elemImg = (ImageView) v.findViewById(R.id.bookElementImg);
            TextView elemTxt = (TextView) v.findViewById(R.id.bookElementName);

            if(getCount() != 0 ){
                note.setVisibility(View.GONE);
                elemImg.setImageResource(images.get(position));
                elemTxt.setText(names.get(position));
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(bookRecordListener != null){
                            bookRecordListener.recordCulture(names.get(position));
                        }
                    }
                });
            }
            return v;
        }
    }
}
