package com.myapplication.mainstay.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.myapplication.mainstay.R;
import com.myapplication.mainstay.SoreActivity;

public class ViewPagerAdapterPT extends PagerAdapter {

    String judul[];
    String keteranganarab[];
    String keteranganindo[];
    SoreActivity context;

    LayoutInflater inflater;

    public ViewPagerAdapterPT(SoreActivity soreActivity, String[] judul, String[] keteranganarab, String[] keteranganindo) {

        this.context = soreActivity;
        this.judul = judul;
        this.keteranganarab = keteranganarab;
        this.keteranganindo = keteranganindo;

    }

    @Override
    public int getCount() {
        return judul.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.itempetang, container, false);

        TextView jdl = view.findViewById(R.id.judul);
        TextView ktrng = view.findViewById(R.id.keteranganarab);
        TextView ktrngindo = view.findViewById(R.id.keteranganindo);

        jdl.setText(judul[position]);
        ktrng.setText(keteranganarab[position]);
        ktrngindo.setText(keteranganindo[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
