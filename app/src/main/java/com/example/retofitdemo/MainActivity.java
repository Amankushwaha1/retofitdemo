package com.example.retofitdemo;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.retofitdemo.model.Feed;
import com.example.retofitdemo.model.children.Children;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView=findViewById(R.id.recycler);

    private static  final String TAG="MainActivity";
    private  static  final String BASE_URL="https://www.reddit.com/";

    ArrayList<String> subreddit=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnget = (Button) findViewById(R.id.btnget);


        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewit();
            }
        });

    }

    public void viewit(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RedditAPI redditapi=retrofit.create(RedditAPI.class);
        Call<Feed> call=redditapi.getData();
        call.enqueue(new Callback<Feed>() {


            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Log.d(TAG, "onResponse: server response"+ response.toString());
                Log.d(TAG, "onResponse: recieved information"+response.body().toString());

                ArrayList<Children> childlist=response.body().getData().getChildren();
                for (int i=0;i<childlist.size();i++){
                    Log.d(TAG, "onResponse: \n"+
                            "kind:"+childlist.get(i).getKind()+"\n"+
                            "subreddit:"+childlist.get(i).getData().getSubreddit()+"\n"+
                            "saved:"+childlist.get(i).getData().isSaved()+"\n"+
                            "title:"+childlist.get(i).getData().getTitle()+"\n"+
                            "-------------------------------------------------------------------\n\n");
                     subreddit.add("subreddit:"+childlist.get(i).getData().getSubreddit());
                    title.add("title:"+childlist.get(i).getData().getTitle());
                   } attaching();
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: somethng went wrong"+ t.getMessage() );
                Toast.makeText(MainActivity.this,"something went wrong", Toast.LENGTH_SHORT).show();
            }
        });}

        public void attaching(){
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            adapter adapter=new adapter(this,subreddit, title);
            recyclerView.setAdapter(adapter);

        }
}
