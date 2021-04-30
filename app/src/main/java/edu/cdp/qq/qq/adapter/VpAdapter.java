package edu.cdp.qq.qq.adapter;

        import android.util.Log;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.annotation.NonNull;
        import androidx.viewpager.widget.PagerAdapter;
        import androidx.viewpager.widget.ViewPager;

        import java.util.ArrayList;

        import edu.cdp.qq.qq.Activity.MainActivity;

public class VpAdapter extends PagerAdapter{

    ArrayList<View> views = new ArrayList<View>();

    public VpAdapter(ArrayList<View> views) {
        this.views = views;
    }


    //获取管理的内容数量
    @Override
    public int getCount() {
        return views.size();
    }

    //
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }



    //滑动时显示对应即将进入的view
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }


    //移除view
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView(views.get(position));
    }
}


