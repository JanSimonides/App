package umb.app.wete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
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

                String jsonString = "[{\"id\":319,\"question\":\"Zistite 1 % z celku, ak viete, že 15 % z celku je 975 ?\",\"solution\":\"65\",\"name\":\"Otázka 1\",\"resolved\":false},{\"id\":320,\"question\":\"Vypočítajte, koľko je 7 % z 3920 ?\",\"solution\":\"274,4\",\"name\":\"Otázka 2\",\"resolved\":false},{\"id\":321,\"question\":\"Vypočítajte celok, ak 1 % z neho je 58 ?\",\"solution\":\"5800\",\"name\":\"Otázka 3\",\"resolved\":false},{\"id\":322,\"question\":\"Koľko percent predstavuje 203 z 580 ?\",\"solution\":\"35\",\"name\":\"Otázka 4\",\"resolved\":false},{\"id\":323,\"question\":\"Po zdražení o 20% stojí blúzka 18 eur. Koľko eur stála blúzka pred zdražením ?\",\"solution\":\"15\",\"name\":\"Otázka 5\",\"resolved\":false},{\"id\":324,\"question\":\"Pán Milan si uložil do banky 1600 eur s úrokom 2,2 % p. a. Koľko peňazí bude mať po roku ?\",\"solution\":\"1635,2\",\"name\":\"Otázka 6\",\"resolved\":false},{\"id\":325,\"question\":\"Kamil váži 56 kg a Janka 46 kg. O koľko percent je Kamil ťažší ?\",\"solution\":\"21,74\",\"name\":\"Otázka 7\",\"resolved\":false},{\"id\":326,\"question\":\"Topánky najprv zlacneli o 15%, ale neskôr opäť o 15% zdraželi. Koľko budú stáť topánky, ak pôvodne stáli 65 eur? Pozor, cena nebude rovnaká ?\",\"solution\":\"63,54\",\"name\":\"Otázka 8\",\"resolved\":false},{\"id\":327,\"question\":\"Koľko peňazí zostalo Katke, ak mala uzavretú dohodu o vykonaní práce na 68 eur ? (Daň z príjmu je 20%)\",\"solution\":\"54,40\",\"name\":\"Otázka 9\",\"resolved\":false},{\"id\":328,\"question\":\"Vypočítajte celok, ak 15 % z neho je 230 ?\",\"solution\":\"1533,33\",\"name\":\"Otázka 10\",\"resolved\":false},{\"id\":329,\"question\":\"Nakreslite si do zošita štvorec rozdelený na 25 rovnakých častí a vyfarbite v ňom 20 %. Koľko percent štvorca zostane nevyfarbených? ?\",\"solution\":\"80\",\"name\":\"Otázka 11\",\"resolved\":false},{\"id\":330,\"question\":\"Z úseku cesty postavili zatiaľ 52%, čo predstavuje 4992 m. Aký je dlhý úsek danej cesty v metroch ?\",\"solution\":\"9600\",\"name\":\"Otázka 12\",\"resolved\":false},{\"id\":331,\"question\":\"Zatiaľ postavili 34% z 85-kilometrového úseku cesty. Koľko kilometrov cesty z daného úseku už postavili ?\",\"solution\":\"28,9\",\"name\":\"Otázka 13\",\"resolved\":false},{\"id\":332,\"question\":\"Mama prispela Jankovi na bicykel sumou 117 eur. Bicykel stál 260 eur. Koľko percent z ceny bicykla prispela mama Jankovi ?\",\"solution\":\"45\",\"name\":\"Otázka 14\",\"resolved\":false},{\"id\":333,\"question\":\"Koľko budú stáť šaty s cenou 28 eur, ak v priebehu mesiaca dvakrát zlacnejú o polovicu? Pozor, šaty nebudú zadarmo.\",\"solution\":\"7\",\"name\":\"Otázka 15\",\"resolved\":false},{\"id\":334,\"question\":\"Koľko predstavuje 20 % daň, ktorú zaplatí zákazník, ak cena nákupu bez DPH je 30 eur?\",\"solution\":\"6\",\"name\":\"Otázka 16\",\"resolved\":false},{\"id\":335,\"question\":\"Kamilovi prišlo na účet po vykonaní práce 96 eur. Na akú sumu mal Kamil uzavretú dohodu o vykonaní práce?\",\"solution\":\"120\",\"name\":\"Otázka 17\",\"resolved\":false},{\"id\":336,\"question\":\"Mirke pripísali po roku úrok 54 eur, pričom mala 1,8 % úrokovú mieru. Akú istinu vložila pôvodne do banky?\",\"solution\":\"3000\",\"name\":\"Otázka 18\",\"resolved\":false},{\"id\":337,\"question\":\"Michal mal na účte po roku 156 eur. Pôvodne vložil 150 eur. Akú mu dala banka úrokovú mieru?\",\"solution\":\"4\",\"name\":\"Otázka 19\",\"resolved\":false},{\"id\":338,\"question\":\"Firma dáva svoje peniaze do banky pri ročnej úrokovej miere 12 %. Koľko peňazí bude mať celkovo firma za 9 mesiacov pri vklade 15 000 eur?\",\"solution\":\"16350\",\"name\":\"Otázka 20\",\"resolved\":false}]";
                Gson gson = new Gson();
                Type type = new TypeToken<List<Problem>>(){}.getType();
                List<Problem> list = gson.fromJson(jsonString, type);
                problemsList.addAll(list);
                System.out.println("pocet :" + problemsList.size());
                recyclerViewAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
