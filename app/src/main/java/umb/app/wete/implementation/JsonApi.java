package umb.app.wete.implementation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import umb.app.wete.model.Problem;

public interface JsonApi {

    @GET("problems/all")
    Call<List<Problem>> GetProblems();

    @GET("problems/reset")
    Call<Void> Reset();

    @POST("problems/update/{id}")
    Call<Void> UpdateResolved(@Path("id")int id);
}
