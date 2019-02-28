package deu.csc.lecture.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

public class DuzenleFragment extends Fragment {
    EditText etYemek,etKisi,etSure,etMalzeme,etTarif;
    String Yemek,Kisi,Sure,Malzeme,Tarif;
    Button btDuzenle;
    MyDatabase myDatabase;
    ScrollView sv;
    String yid;
    public DuzenleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duzenle, container, false);
        sv=(ScrollView)getActivity().findViewById(R.id.scrolview1);
        etYemek=(EditText)view.findViewById(R.id.etDuzenleYemek);
        etKisi=(EditText)view.findViewById(R.id.etDuzenleKisi);
        etSure=(EditText)view.findViewById(R.id.etDuzenleSure);
        etMalzeme=(EditText)view.findViewById(R.id.etDuzenleMalzeme);
        etTarif=(EditText)view.findViewById(R.id.etDuzenleTarif);
        btDuzenle=(Button)view.findViewById(R.id.btDuzenle);

        etYemek.setText(Yemek);
        etKisi.setText(Kisi);
        etSure.setText(Sure);
        etMalzeme.setText(Malzeme);
        etTarif.setText(Tarif);

        btDuzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase=new MyDatabase(getActivity());
                myDatabase.update(myDatabase.getReadableDatabase(), Integer.valueOf(yid),etYemek.getText().toString()
                        , etKisi.getText().toString(),
                        etSure.getText().toString()
                        , etMalzeme.getText().toString(),
                        etTarif.getText().toString());
                MyGoster myGoster=(MyGoster)getActivity();
                myGoster.goster(yid);


                sv.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public void guncelle(int id,String yemek_adi, String yemek_kisisayisi, String yemek_suresi,
                         String yemek_malzemesi, String yemek_tarifi){
        yid=String.valueOf(id);
        Yemek=yemek_adi.toString();
        Kisi=yemek_kisisayisi.toString();
        Sure=yemek_suresi.toString();
        Malzeme=yemek_malzemesi.toString();
        Tarif=yemek_tarifi.toString();
    }
}
