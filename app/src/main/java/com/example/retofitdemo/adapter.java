package com.example.retofitdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{
    private static final String TAG="adapter";

    private Context mcontext;
    private ArrayList<String> msubreddit=new ArrayList<>();
    private ArrayList<String> mtitle=new ArrayList<>();

    public adapter(Context mcontext, ArrayList<String> subreddit, ArrayList<String> title) {
        this.mcontext = mcontext;
        this.msubreddit = subreddit;
        this.mtitle = title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simpleview,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called");
        viewHolder.sub.setText(msubreddit.get(i));
        viewHolder.t.setText(mtitle.get(i));
    }

    @Override
    public int getItemCount() {
        return msubreddit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView sub;
        TextView t;

        public ViewHolder (View itemvi){
            super(itemvi);
            this.sub=itemvi.findViewById(R.id.subreddit);
            this.t=itemvi.findViewById(R.id.titl);
        }
    }
}
