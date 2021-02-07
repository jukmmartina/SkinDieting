package martinamagdalenajukic.ferit.skindieting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ingredients")
    @Expose
    private String ingredients;
    @SerializedName("instructions")
    @Expose
    private String instructions;

    public int getId(){
        return id;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getName(){
        return name;
    }
    public String getIngredients(){
        return ingredients;
    }
    public String getInstructions(){
        return instructions;
    }
}
