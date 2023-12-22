package devandroid.dutra.appgaseta.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.contentcapture.ContentCaptureCondition;

import androidx.annotation.Nullable;

public class GasEtaDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "gaseta.db";
    public static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;



    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("GasEtaDB", "Constructor called");

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("GasEtaDB", "onCreate called");

        String sqlTabelaCombustivel =
                "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";

        sqLiteDatabase.execSQL(sqlTabelaCombustivel);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
