package umb.app.wete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import umb.app.wete.implementation.JsonApi;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button reset;

    String URL = "http://192.168.1.31:8080/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //vytvorenie requestu
    JsonApi jsonApi = retrofit.create(JsonApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        reset = findViewById(R.id.reset);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentProblem = new Intent(MainActivity.this,ProblemActivity.class);
                startActivity(intentProblem);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });
    }

    public void Reset (){
        Call<Void> call = jsonApi.Reset();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_LONG).show();
                Log.i("Code", String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e( "onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
