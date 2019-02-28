package deu.csc.lecture.homework;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class MainActivity extends Activity {
    ListView lv;
    ArrayList<item> list;
    MyAdaptor myAdap;
    SearchView mSearchEdit;
    MyDatabase myDatabase;
    int posit;
    private static final int REQ_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView1);
        myDatabase = new MyDatabase(this);
        //myDatabase.insert(myDatabase.getReadableDatabase(),"Imam Bayildi","6","40","2 adet patlıcan","once soğanları kızart");
        ListeyiGoster();
        /*list = myDatabase.showlist();
        myAdap = new MyAdaptor(this, R.layout.row, list);
        lv.setAdapter(myAdap);*/

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), DetayActivity.class);
                intent.putExtra("id", list.get(position).getYemekId());
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                posit = position;
                showYesNoDialog(view);
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        ListeyiGoster();
    }

    public void showYesNoDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Yemek Tarifini Silmek İstediğinize Emin Misiniz ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String s = list.get(posit).getYemekAdı();
                myDatabase.remove(myDatabase.getReadableDatabase(),Integer.valueOf(list.get(posit).getYemekId()));
                list.remove(posit);
                ListeyiGoster();
                Toast.makeText(getApplicationContext(), "\"" + s + "\" tarifi silindi", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
    // verileri göstermek için
    private void ListeyiGoster() {
        list = myDatabase.showlist();
        myAdap = new MyAdaptor(this, R.layout.row, list);
        lv.setAdapter(myAdap);

    }

    //menuye eklediğimiz item'ı activitymize bağladık ve rengini ayarladık.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        Drawable icon = menu.getItem(1).getIcon();
        icon.mutate();
        //icon rengi
        icon.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_IN);
        MenuItem mSearch = menu.findItem(R.id.app_bar_search);
        mSearchEdit =(SearchView) mSearch.getActionView();
        mSearchEdit.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //myAdap.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdap.filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.yeni_yemek_ekle:
                Intent intent = new Intent(getApplicationContext(), EkleActivity.class);
                startActivityForResult(intent,REQ_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode== RESULT_OK) {
            ListeyiGoster();
        Toast.makeText(this, "Tarif Başarılı Şekilde Eklendi",
                  Toast.LENGTH_SHORT).show();        }
    }
}
