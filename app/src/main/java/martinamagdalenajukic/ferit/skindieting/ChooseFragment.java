package martinamagdalenajukic.ferit.skindieting;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ChooseFragment extends Fragment {

    private TextView tvChoose;
    private Button btnOption1, btnOption2, btnOption3;
    private ChooseClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvChoose=view.findViewById(R.id.tvChoose);
        btnOption1=view.findViewById(R.id.btnOption1);
        btnOption2=view.findViewById(R.id.btnOption2);
        btnOption3=view.findViewById(R.id.btnOption3);
        setUpListeners();
    }

    private void setUpListeners() {
        btnOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.onChoose(1);
            }
        });
        btnOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onChoose(2);
            }
        });
        btnOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) listener.onChoose(3);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ChooseClickListener) listener=(ChooseClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}