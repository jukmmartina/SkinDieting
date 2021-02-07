package martinamagdalenajukic.ferit.skindieting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class IntroductionFragment extends Fragment {

    private TextView tvTitle;
    private TextView tvMain;
    private ImageView ivMain;
    private TextView tvFooter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvMain=view.findViewById(R.id.tvMain);
        ivMain=view.findViewById(R.id.ivMain);
        tvFooter=view.findViewById(R.id.tvFooter);
    }
}