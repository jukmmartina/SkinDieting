package martinamagdalenajukic.ferit.skindieting;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static  ApiInterface apiInterface;
    private static String BASE_URL="https://api.npoint.io/"; //https://api.npoint.io/c58b46b0f8a5ae4a63de

    public static ApiInterface getApiInterface(){
        if (apiInterface==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface=retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
}
