package martinamagdalenajukic.ferit.skindieting;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("c58b46b0f8a5ae4a63de/{id}")
    Call<Recipe> getRecipe(@Path("id") int id);
}
