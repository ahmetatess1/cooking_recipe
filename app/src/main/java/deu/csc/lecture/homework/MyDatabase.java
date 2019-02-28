package deu.csc.lecture.homework;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private  static String DB_NAME="AppDB";
    private  static  int DB_VERSION=1;

    private static final String TABLE_NAME = "yemek_listesi";
    private static String YEMEK_ADI = "yemek_adi";
    private static String YEMEK_ID = "id";
    private static String YEMEK_SURESI = "suresi";
    private static String YEMEK_KISISAYISI = "kisisayisi";
    private static String YEMEK_MALZEMESI = "malzeme";
    private static String YEMEK_TARIFI = "tarif";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String command= "CREATE TABLE "+ TABLE_NAME+"( "
                +YEMEK_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +YEMEK_ADI+" TEXT,"
                +YEMEK_KISISAYISI+" TEXT,"
                +YEMEK_SURESI+" TEXT,"
                +YEMEK_MALZEMESI+" TEXT,"
                +YEMEK_TARIFI+" TEXT"
                +")";
        db.execSQL(command);
        insert(db,"Musakka","4-6","30 dk","1 adet patlıcan","once patlıcanları kızart");
        insert(db,"Çiğ Köfte","8-10","20 dk","3 su bardağı esmer çiğ köftelik bulgur\n"+
                "3 su bardağı su\n"+
                "2 orta boy soğan\n"+
                "3 diş sarımsak\n"+
                "1 çay bardağı kadar isot\n"+
                "4 yemek kaşığı pul biber\n"+
                "1 tatlı kaşığı karabiber\n"+
                "1 tatlı kaşığı tuz\n"+
                "3 yemek kaşığı domates salçası\n"+
                "1 yemek kaşığı biber salçası\n"+
                "1, 5 çay bardağı sıvı yağ\n"+
                "1 yemek kaşığı nar ekşisi\n"+
                "Maydanoz","Dışardan çiğ köfte almaya SON.Bulgurun demlenme süresine göre hazırlama süresini 20 dk yaptım. Fakat daha kısa sürüyor:)\n"+
                "Bir defa tadına varan bir daha istiyor.\n"+
                "\n"+
                "Esmer çiğ köftelik bulguru kaynar su ile ıslatıp, üstü kapalı demlenmeye alıyoruz.\n"+
                "Bu sırada soğan, sarımsak ve geriye kalan diğer malzemelerimizi de hazırlıyoruz.\n"+
                "Bulgur iyice demini aldıktan sonra maydanoz hariç tüm malzemelerimizi bulgurun içine karıştırıp blenderdan geçiriyoruz.\n"+
                "İstenilen kıvam maksimum 5-7 dk içerisinde elde edilecektir.\n"+
                "Daha sonra ince ince doğradığımız maydanozları da katıp biraz elimizle yoğurduktan sonra şekil verip afiyetle yiyoruz.");
        insert(db,"Adana Kebap","4-6","120 dk","500 gr dana döş çift çekim kıyma\n"+
                        "Az koyun kuyruk yağı\n"+
                        "1 tane soğan\n"+
                        "2 diş sarımsak\n"+
                        "1 tatlı kaşığı pul biber\n"+
                        "İsteğe bağlı baharat\n"+
                        "Tuz","Soğannı rendeliyoruz. Suyunu sıkıyoruz, sarımsağı da rendeliyoruz.\n"+
                        "Sarımsağı, soğanı, kıymayı, baharatları ve tuzu da ekleyip güzelce yoğuruyoruz.\n"+
                        "Şişlere limon büyüklüğünden biraz fazla etimizi geçiriyoruz.\n"+
                        "Ağzını kapatıyoruz ve 4-5 saat buzdolabında bekletiyoruz.\n"+
                        "Fırında yapıcaksanız tahta çubuklara takıyoruz. Elektrikli ızgarada ise demir çubuklara takıyoruz.\n"+
                "Fırında pişireceksek yağlı kağıt serili tepsiye diziyoruz.\n"+
                        "Önceden ısıtılmış fırında 200 derecede pişiriyoruz.\n"+
                        "Elektrikli ızgarada ise çevirerek kızarana kadar pişiriyoruz.\n"+
                        "İstenirse domates, biber de közleye bilirsiniz.\n"+
                        "İstediğimiz söğüşle ve lavaşla servis yapıyoruz.");
        insert(db,"Ali Nazik","1-2","40 dk","2-3 közlenmiş patlıcan\n"+
                "2 diş sarımsak\n"+
                "1 kase yoğurt\n"+
                "Tuz\n"+
                "300 gram kuşbaşı et\n"+
                "1 yemek kaşığı salça\n"+
                "2 yemek kaşığı tereyağ\n"+
                "Toz kırmızı biber, kara biber, tuz, kekik (çok az)\n"+
                "Zeytinyağ","Derin bir tavaya biraz zeytinyağı damlatıp etleri ekleyin ve kapağını kapatın.\n"+
                "Diğer yandan közlenmiş ve doğranmış patlıcanları ve yoğurdu bir kaba alın, sarımsakları ve tuzu ekleyip güzelce karıştırın.\n"+
                "Etler suyunu çektikten sonra tereyağını ekleyip kavurun salçayı ve baharatları ekleyin.\n"+
                "Sosumuz hafif sulu olması gerekiyor. Ben derin bir tabağa servis yaptım, altına patlıcanlı yoğurdu üzerine de et sosumuzu ilave ederek.\n"+
                "Yanlarını lavaş ekmeğiyle süsleyip, üzerine domates ve maydanozla sunum yaptım. Dilediğiniz gibi sunum yapıp afiyet olsun:)");
    }

    public  void insert(SQLiteDatabase db, String yemek_adi, String yemek_kisisayisi, String yemek_suresi,
                         String yemek_malzemesi, String yemek_tarifi)
    {
        ContentValues contentValues= new ContentValues();
        contentValues.put(YEMEK_ADI,yemek_adi);
        contentValues.put(YEMEK_KISISAYISI,yemek_kisisayisi);
        contentValues.put(YEMEK_SURESI,yemek_suresi);
        contentValues.put(YEMEK_MALZEMESI,yemek_malzemesi);
        contentValues.put(YEMEK_TARIFI,yemek_tarifi);
        db.insert(TABLE_NAME,null,contentValues);
    }

    public void remove(SQLiteDatabase db, int id){
        db.delete(TABLE_NAME,YEMEK_ID+" = ?",new String[]{String.valueOf(id)});
    }

    public void update(SQLiteDatabase db,int id, String yemek_adi, String yemek_kisisayisi, String yemek_suresi,
                       String yemek_malzemesi, String yemek_tarifi){
        ContentValues contentValues= new ContentValues();
        contentValues.put(YEMEK_ADI,yemek_adi);
        contentValues.put(YEMEK_KISISAYISI,yemek_kisisayisi);
        contentValues.put(YEMEK_SURESI,yemek_suresi);
        contentValues.put(YEMEK_MALZEMESI,yemek_malzemesi);
        contentValues.put(YEMEK_TARIFI,yemek_tarifi);

        db.update(TABLE_NAME,contentValues,YEMEK_ID+" = ?",
                    new String[]{String.valueOf(id)});
    }

    public ArrayList<item> showlist(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<item> list=new ArrayList<item>();
        if (cursor.moveToFirst()) {
            do {
                    item item1 = new item();
                    item1.setYemekId(cursor.getString(0));
                    item1.setYemekAdı(cursor.getString(1));
                    item1.setKisiSayısı(cursor.getString(2));
                    item1.setZaman(cursor.getString(3));
                    item1.setMalzeme(cursor.getString(4));
                    item1.setTarif(cursor.getString(5));

                list.add(item1);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public item QueryMeal(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        item item1 = new item();
        if (cursor.moveToFirst()) {
                item1.setYemekId(cursor.getString(0));
                item1.setYemekAdı(cursor.getString(1));
                item1.setKisiSayısı(cursor.getString(2));
                item1.setZaman(cursor.getString(3));
                item1.setMalzeme(cursor.getString(4));
                item1.setTarif(cursor.getString(5));
        }
        return item1;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1 && newVersion==2)
        {
            remove(db,1);
            insert(db,"imam bayıldı","4","30","1 adet patlıcan","once patlıcanları kızart");
        }

    }
}