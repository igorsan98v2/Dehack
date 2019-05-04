package com.example.basefeature;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;


public class SQLDriver extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "places.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "PLACES";
    private static Map<String, Integer> COLUMNS;
    //сервер не успел , простите (

    private static String[] execsVals = {
            "1,6,48.73367071,37.5964258,\"у Маришки\"",
            "1,6,48.7248625,37.607241,\"Наоми\"",
            "1,6,48.7473833,37.596841,\"Монро\"",
            " 1,6,48.7251747,37.5322,\"Эсфирь\" ",
            " 1,6,48.7396601,37.590472,'Салон красоты \"Гламур\"'",
            "1,6,48.7531301,37.6097051,\"Династия\"",
            "1,7,48.749808,37.61605,'\"Сосед\"'",
            " 1,7,48.7294245,37.6089156,'Ломбард'",
            " 1,7,48.7253223,37.5402413,'Ломбард'",
            "1,2,48.721722,37.600327,'Аптека \"Вита-Фарм\"'",
            "1,2,48.748568,37.620375,'Территориальный комитет № 4'",
            "1,3,48.768129,37.551450,'Ясногорский поссовет'",
            "1,2,48.762711,37.603539,'ЧП \"Старостенко\"'",
            "1,1,48.748833,37.617221,'Аптека № 8 ООО \"Фармация\"'",
            "1,2,48.742360,37.601996,'Лечебно-профилактический центр \"Альянс\"'",
            "1,2,48.743302,37.591570,'Ветеринарная лечебница \"Айболит\"'",
            "1,3,48.759123,37.618061,'Беленьковский городской совет'",
            "1,1,48.731274,37.607604,'Аптека № 169 \"Добрі ліки\"'",
            "1,1,48.735187,37.586298,'Аптека № 4 ДКП \"Фармация\"'",
            "1,2,48.741511,37.584401,'Медицинский центр \"Доверие\"'",
            "1,3,48.734970,37.576933,'Офис управления \"ЖКХ\"'",
            "1,3,48.733816,37.592971,'Офис территориального комитета'",
            "1,4,48.737510,37.591076,'Магазин \"Продукты\"'",
            "1,4,48.738411,37.592404,'Магазин \"Спортландия\"'",
            "1,4,48.733293,37.583956,'Магазин \"Collins\"'",
            "1,4,48.733068,37.584560,'Магазин \"Лучано\"'",
            "1,4,48.733760,37.584257,'Магазин \"Промтовары\"'",
            "1,4,48.736788,37.586697,'\"Магазин бытовой техники\"'",
            "1,4,48.737047,37.588204,'Магазин \"Всё в ажуре\"'",
            "1,4,48.740160,37.590399,'Магазин обуви \"Avenus\"'",
            "1,4,48.748854,37.597962,'Магазин \"Крокус\" цветы'",
            "1,4,48.737437,37.580212,'Магазин \"Продукты\"'",
            "1,4,48.737860,37.581078,'Магазин \"Продукты\"'",
            "1,4,48.738014,37.579737,'Магазин \"Продукты\"'",
            "1,5,48.750126,37.609971,'Закусочная \"Аппетит\"'",
            "1,7,48.7249665,37.6016544,'Ломбард \"Капитал\"'",
            "1,7,48.749808,37.61605,'Ломбард\"Сосед\"'",
            "1,6,48.7531301,37.6097051,'Парикмахерская \"Династия\"'",
            "1,6,48.7396601,37.590472,'Салон красоты \"Гламур\"'",
            "1,6,48.7251747,37.5322,'Парикмахерская \"Эсфирь\"'",
            "1,6,48.7473833,37.596841,'Парикмахерская \"Монро\"'",
            "4,2,48.721722,37.600327,'Аптека \"Вита-Фарм\"'",
            "4,2,48.748568,37.620375,'Территориальный комитет № 4'",
            "4,3,48.768129,37.551450,'Ясногорский поссовет'",
            "3,2,48.762711,37.603539,'ЧП \"Старостенко\"'",
            "3,1,48.748833,37.617221,'Аптека № 8 ООО \"Фармация\"'",
            "3,2,48.742360,37.601996,'Лечебно-профилактический центр \"Альянс\"'",
            "3,2,48.743302,37.591570,'Ветеринарная лечебница \"Айболит\"'",
            "3,3,48.759123,37.618061,'Беленьковский городской совет'",
            "2,1,48.731274,37.607604,'Аптека № 169 \"Добрі ліки\"'",
            "2,1,48.735187,37.586298,'Аптека № 4 ДКП \"Фармация\"'",
            "2,2,48.741511,37.584401,'Медицинский центр \"Доверие\"'",
            "2,3,48.734970,37.576933,'Офис управления \"ЖКХ\"'",
            "2,3,48.733816,37.592971,'Офис территориального комитета'",
            "1,8,48.743361,37.592736,'Пересечение улиц \"Дружбы\" и \"Василия Стуса\"'",
            "1,8,48.742480,37.595195,'Пересечение улиц \"Дружбы и \"Героев Украины\"'",
            "1,8,48.732257,37.583526,'Пересечение улиц \"Василия Стуса\" и \"Парковая\"'",
            "1,8,48.727610,37.595957,'Пересечение улиц \"Юбилейная\" и \"Парковая\"'",
            "1,8,48.725130,37.537895,'Пересечение улиц \"Школьная\" и \"Сиверская\"'"


    };


    //  private static final String DB_PATH ="/data/data/"+BuildConfig.APPLICATION_ID+"/databases/"+DATABASE_NAME;
    private static Context context;
    private SQLiteDatabase db;

    public SQLDriver(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // super(context, DATABASE_NAME, null, DATABASE_VERSION);
        COLUMNS = new HashMap<>(0);
        this.context = context;
        db = getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS PLACES (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    place_id INTEGER NOT NULL,\n" +
                "    category_id INTEGER NOT NULL,\n" +
                "    lat REAL NOT NULL ,\n" +
                "    lang REAL NOT NULL,\n" +
                "    name TEXT NOT NULL,\n" +
                "    UNIQUE(lang,lat,name)\n" +
                ")");
        //вот тут я подключился бы к серверу
        for (String value : execsVals) {
            try {
                db.execSQL("INSERT INTO ISSUES(id,title,descript,photo_path,lat,lng,date_real) VALUES(" + value + ");");
            } catch (Exception e) {
                Log.d("SQL", e.getMessage());
            }
        }
        //db.isOpen();
    }

    ArrayList<Issue> queryIssues(String filter) {
        ArrayList<Issue> issues = new ArrayList<Issue>();
        if (filter == null) {
            int category_id, place_id;
            double lat, lang;
            String name;
            Cursor res = null;


            try {


                //   res =db.query(DATABASE_TABLE,null,null,null,null,null,null);
                res = db.rawQuery("Select * from issues", null);
                if (res != null) {
                    res.moveToFirst();
                    COLUMNS.put("lat", res.getColumnIndex("lat"));
                    COLUMNS.put("lang", res.getColumnIndex("lang"));
                    COLUMNS.put("place_id", res.getColumnIndex("place_id"));
                    COLUMNS.put("category_id", res.getColumnIndex("category_id"));
                    COLUMNS.put("name", res.getColumnIndex("name"));

                    while (res.moveToNext()) {
                        lat = res.getDouble(COLUMNS.get("lat"));
                        lang = res.getDouble(COLUMNS.get("lang"));
                        place_id = res.getInt(COLUMNS.get("category_id"));//потому что ярик
                        category_id = res.getInt(COLUMNS.get("place_id"));
                        name = res.getString(COLUMNS.get("name"));
                        issues.add(new IssueInfo(new LatLng(lat, lang), name, category_id, place_id));
                    }
                }
            } catch (Exception e) {
                Log.d("SQL", e.getMessage());
            }


            //Cursor res = db.query(DATABASE_TABLE,null,null,null,null,null,null);

        }
        return issues;
    }

    public void checkUpdate() {
        //do some
    }

}