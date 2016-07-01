package cn.lxw.simpleviewpagerindicator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 猿代码: Lxw
 * 时轴: 2016/7/1 10:19
 * 伊妹儿: China2021@126.com
 * _________________________
 * Ps:
 */
public class SpaceFragment extends Fragment {

    private View fragment_space;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_space = inflater.inflate(R.layout.fragment_space, null);
        return fragment_space;
    }
}
