package martinamagdalenajukic.ferit.skindieting;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    private Cursor mCursor;

    public RecyclerAdapter( Cursor cursor) {
        mCursor = cursor;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        holder.tvItem.setText(" ▪ " + mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME)));
        holder.itemView.setTag(mCursor.getLong(mCursor.getColumnIndex(GroceryContract.GroceryEntry._ID)));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor!=null) mCursor.close();
        mCursor=newCursor;
        if (newCursor!=null) notifyDataSetChanged();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvItem;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }

    }
}
