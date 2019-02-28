package deu.csc.lecture.homework;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdaptor extends ArrayAdapter {


    Activity context;
    ArrayList<item> list;
    private ArrayList<item> arraylist;
    View raw;
    TextView tv1,tv2,tv3;
    public MyAdaptor(Activity context,int raw, ArrayList<item> list) {
        super(context,raw,list);
        this.context=context;
        this.list=list;
        this.arraylist = new ArrayList<item>();
        this.arraylist.addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        raw=context.getLayoutInflater().inflate(R.layout.row,parent,false);
        tv1=(TextView)raw.findViewById(R.id.tvYemek);
        tv2=(TextView)raw.findViewById(R.id.tvKisi);
        tv3=(TextView)raw.findViewById(R.id.tvSure);
        tv1.setText(list.get(position).getYemekAdı());
        tv2.setText(list.get(position).getKisiSayısı());
        tv3.setText(list.get(position).getZaman());
        return raw;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arraylist);
        } else {
            for (item wp : arraylist) {
                if (wp.getYemekAdı().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
