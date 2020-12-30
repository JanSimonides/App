package umb.app.wete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import umb.app.wete.implementation.JsonApi;
import umb.app.wete.implementation.RecyclerViewAdapter;
import umb.app.wete.model.Problem;

public class ProblemActivity extends AppCompatActivity {

    //String URL = "http://10.0.2.2:8080/";
    String URL = "http://192.168.1.31:8080/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //vytvorenie requestu
    JsonApi jsonApi = retrofit.create(JsonApi.class);

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Problem> problemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);

        problemsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(ProblemActivity.this, problemsList);
        recyclerView.setAdapter(recyclerViewAdapter);
        GetAllProblems();
    }

    public void GetAllProblems(){

        Call<List<Problem>> call = jsonApi.GetProblems();

        //pre vytvorenie na novom threade na pozadi
        call.enqueue(new Callback<List<Problem>>() {
            @Override
            public void onResponse(Call<List<Problem>> call, Response<List<Problem>> response) {
                List<Problem> problems;
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                }
                else {
                    problems = response.body();
                    problemsList.addAll(problems);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Problem>> call, Throwable t) {
                //textView.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
