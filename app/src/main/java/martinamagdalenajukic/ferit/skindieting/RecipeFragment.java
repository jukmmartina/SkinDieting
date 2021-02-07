package martinamagdalenajukic.ferit.skindieting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment {

    private ImageView ivRecipe;
    private TextView tvName, tvIngredients, tvInstructions;
    private Button btnNext;
    private static final String BUNDLE_KEY="randomID";
    private Call<Recipe> apiCall;
    private NextClickListener listener;
    private Random rand=new Random();
    int id;

    public static RecipeFragment newInstance(int id){
        RecipeFragment fragment=new RecipeFragment();
        Bundle arg=new Bundle();
        arg.putInt(BUNDLE_KEY, id);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivRecipe=view.findViewById(R.id.ivRecipe);
        tvName=view.findViewById(R.id.tvName);
        tvIngredients=view.findViewById(R.id.tvIngredients);
        tvInstructions=view.findViewById(R.id.tvInstructions);
        btnNext=view.findViewById(R.id.btnNext);
        if(getArguments()!=null){
            int argID=getArguments().getInt(BUNDLE_KEY);
             id=argID;
        }
        setUpView(id);
        setUpListener(id);
    }

    private void setUpListener(int id) {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                if(id>=0&&id<3) { listener.onNextListener(rand.nextInt(2-0+1)+0);}
                else if(id>=3&&id<6) { listener.onNextListener(rand.nextInt(5-3+1)+3);}
                else if(id>=6&&id<9) { listener.onNextListener(rand.nextInt(8-6+1)+6);}
            }
            }
        });
    }

    private void setUpView(int id) {
        apiCall=NetworkUtils.getApiInterface().getRecipe(id);
        apiCall.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    tvName.setText(response.body().getName().toString());
                    tvIngredients.setText(response.body().getIngredients().toString());
                    tvInstructions.setText(response.body().getInstructions().toString());
                    Glide.with(RecipeFragment.this).load(response.body().getImageUrl().toString()).apply(RequestOptions.centerCropTransform()).into(ivRecipe);
                    Log.d("json", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(apiCall!=null) apiCall.cancel();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NextClickListener) listener=(NextClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}