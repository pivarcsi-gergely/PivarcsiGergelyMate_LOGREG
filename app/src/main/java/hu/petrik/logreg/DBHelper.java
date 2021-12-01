package hu.petrik.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "felhasznalok.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "felhasznalo";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_FELHNEV = "felhnev";
    private static final String COL_JELSZO = "jelszo";
    private static final String COL_TELJESNEV = "teljesnev";

    public DBHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COL_EMAIL + " TEXT NOT NULL, "
                + COL_FELHNEV + " TEXT NOT NULL, "
                + COL_JELSZO + " TEXT NOT NULL, "
                + COL_TELJESNEV + " TEXT NOT NULL, "
                + "UNIQUE(" + COL_FELHNEV + ", " + COL_EMAIL + ") "
                + ");";
        sqLiteDatabase.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean rogzites(String email, String felhnev, String jelszo, String teljesnev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(COL_EMAIL, email);
        value.put(COL_FELHNEV, felhnev);
        value.put(COL_JELSZO, jelszo);
        value.put(COL_TELJESNEV, teljesnev);
        return db.insert(TABLE_NAME, null, value) != -1;
    }

    public Cursor teljesNevKereses(String felhNev) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT " + COL_TELJESNEV + " FROM " + TABLE_NAME + " WHERE " + COL_FELHNEV + " LIKE ?";
        Cursor eredmeny = db.rawQuery(sql, new String[] {felhNev});
        return eredmeny;
    }
}
