package martinamagdalenajukic.ferit.skindieting;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mfragmentList;

    public PagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        mfragmentList=fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    public CharSequence getPageTitle(int position){
        return null;
    }
}
