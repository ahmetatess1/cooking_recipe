package deu.csc.lecture.homework;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetayActivity extends Activity implements MyGoster{
    TextView tvYemek,tvKisi,tvSure,tvMalzeme,tvTarif;
    ScrollView sv;
    item detay;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        sv=(ScrollView)findViewById(R.id.scrolview1);

        tvYemek=(TextView)findViewById(R.id.tvDetayYemek);
        tvKisi=(TextView)findViewById(R.id.tvDetayKisi);
        tvSure=(TextView)findViewById(R.id.tvDetaySure);
        tvMalzeme=(TextView)findViewById(R.id.tvDetayMalzeme);
        tvTarif=(TextView)findViewById(R.id.tvDetayTarif);

        Intent intent=getIntent();
        String yid=intent.getStringExtra("id");
        goster(yid);

    }
    @Override
    public void goster(String yid){
        myDatabase=new MyDatabase(this);
        detay=myDatabase.QueryMeal(Integer.valueOf(yid));
        tvYemek.setText(detay.getYemekAdı().toString());
        tvKisi.setText(detay.getKisiSayısı().toString());
        tvSure.setText(detay.getZaman().toString());
        tvMalzeme.setText("\n"+detay.getMalzeme().toString());
        tvTarif.setText("\n"+detay.getTarif().toString());
    }

    //menuye eklediğimiz item'ı activitymize bağladık ve rengini ayarladık.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        //icon rengi
        icon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.tarifiDuzenle:
                    if(sv.getVisibility()==View.VISIBLE){
                    sv.setVisibility(View.GONE);
                    DuzenleFragment d1=new DuzenleFragment();
                    d1.guncelle(Integer.valueOf(detay.getYemekId().toString()), detay.getYemekAdı().toString()
                            , detay.getKisiSayısı().toString(), detay.getZaman().toString()
                            , detay.getMalzeme().toString(), detay.getTarif().toString());
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.frame1, d1);
                    ft.commit();}
                    else if(sv.getVisibility()==View.GONE)
                    {
                        sv.setVisibility(View.VISIBLE);
                    }
                    return true;
            }
        }
        catch (Exception ex)
        {
            Log.e("ERRORDuzenle:", "onOptionsItemSelected: ", ex);
        }
        return super.onOptionsItemSelected(item);
    }
}
