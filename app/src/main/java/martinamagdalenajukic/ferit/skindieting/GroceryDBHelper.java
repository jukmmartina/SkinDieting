package martinamagdalenajukic.ferit.skindieting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GroceryDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="grocerylist.db";
    public static final int DATABSE_VERSION=1;

    public GroceryDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                GroceryContract.GroceryEntry.TABLE_NAME + " (" +
                GroceryContract.GroceryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GroceryContract.GroceryEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GroceryContract.GroceryEntry.TABLE_NAME);
        onCreate(db);
    }
}
