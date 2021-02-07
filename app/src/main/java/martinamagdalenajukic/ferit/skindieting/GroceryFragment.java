package martinamagdalenajukic.ferit.skindieting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GroceryFragment extends Fragment {

    private TextView tvGrocery;
    private Button btnDelete;
    private RecyclerView recycler;
    private EditText etGrocery;
    private GroceryDBHelper dbHelper;
    private SQLiteDatabase mDatabase;
    private RemoveSwipeListener listener;
    private RemoveAllListner allListener;
    private InsertClickListener insertListener;
    private RecyclerAdapter adapter;

    public GroceryFragment(GroceryDBHelper dbHelper){
        this.dbHelper=dbHelper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grocery, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvGrocery=view.findViewById(R.id.tvGrocery);
        btnDelete=view.findViewById(R.id.btnDelete);
        etGrocery=view.findViewById(R.id.etGrocery);

        mDatabase=dbHelper.getWritableDatabase();

        recycler=view.findViewById(R.id.recycler);
        setUpRecyclerView();
        setUpListeners();

    }

    private void setUpListeners() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allListener!=null) allListener.onRemoveAll();
            }
        });
        etGrocery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (before==0&&count==1&&s.charAt(start)=='\n'&&etGrocery.getText().toString().trim().length()!=0){
                    if (insertListener!=null) {
                        insertListener.onInsertClick(etGrocery.getText().replace(start, start + 1, "").toString());
                        etGrocery.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (listener!=null) listener.oneSwipeRemoved((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recycler);
    }

    private void setUpRecyclerView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new RecyclerAdapter(getAllItems());
        recycler.setAdapter(adapter);
    }

    public void addNewItem(String item){
        ContentValues cv=new ContentValues();
        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, item);
        mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null, cv);
        adapter.swapCursor(getAllItems());
    }

    public void removeItem(long id){
        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME,
                GroceryContract.GroceryEntry._ID+"="+id, null);
        adapter.swapCursor(getAllItems());
    }

    public void removeAll(){
        mDatabase.delete(GroceryContract.GroceryEntry.TABLE_NAME, null, null);
        adapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                GroceryContract.GroceryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GroceryContract.GroceryEntry.COLUMN_NAME
        );
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof RemoveAllListner && context instanceof RemoveSwipeListener){
            listener=(RemoveSwipeListener) context;
            allListener=(RemoveAllListner) context;
            insertListener=(InsertClickListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
        allListener=null;
        insertListener=null;
    }


}