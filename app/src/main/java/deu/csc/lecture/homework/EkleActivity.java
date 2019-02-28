package deu.csc.lecture.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EkleActivity extends Activity {
    MyDatabase myDatabase;
    EditText etYemek,etKisi,etSure,etMalzeme,etTarif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);
        etYemek=(EditText)findViewById(R.id.etEkleYemek);
        etKisi=(EditText)findViewById(R.id.etEkleKisi);
        etSure=(EditText)findViewById(R.id.etEkleSure);
        etMalzeme=(EditText)findViewById(R.id.etEkleMalzeme);
        etTarif=(EditText)findViewById(R.id.etEkleTarif);
        myDatabase = new MyDatabase(this);

    }

    public void btn1_ekle(View view) {
        if(!etYemek.getText().toString().isEmpty()) {
            myDatabase.insert(myDatabase.getReadableDatabase(), etYemek.getText().toString()
                    , etKisi.getText().toString(),
                    etSure.getText().toString()
                    , etMalzeme.getText().toString(),
                    etTarif.getText().toString());
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "Lütfen bilgileri boş bırakmayınız!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
