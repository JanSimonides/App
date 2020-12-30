package umb.app.wete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import umb.app.wete.implementation.JsonApi;
import umb.app.wete.model.Problem;

public class SolvingActivity extends AppCompatActivity {
    private Button confirm;
    private Button back;
    private TextView name;
    private TextView question;
    private TextView result;
    private EditText respone;

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
        setContentView(R.layout.activity_solving);

        confirm = findViewById(R.id.check);
        back = findViewById(R.id.back);
        name = findViewById(R.id.name);
        question = findViewById(R.id.question);
        respone = findViewById(R.id.response);
        result = findViewById(R.id.result);

        final Problem problem = new Problem();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            problem.setName(bundle.getString("name"));
            problem.setQuestion(bundle.getString("question"));
            problem.setResolved(bundle.getBoolean("resolved"));
            problem.setSolution(bundle.getString("solution"));
            problem.setId(bundle.getInt("id"));
        }

        question.setText(problem.getQuestion());
        name.setText(problem.getName());

            confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problem.setResolved(true);
                UpdateResolved(problem.getId());
                finish();
                Intent main = new Intent(getApplicationContext(), ProblemActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
    }

    public void UpdateResolved (int id){
        Call<Void> call = jsonApi.UpdateResolved(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Code", String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e( "onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
