package com.pk.sai.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pk.sai.models.MyResolvesModel;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "saidb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String RESOLVE_TABLE_NAME = "myresolves";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our  resolve column
    private static final String PROMISE_COL = "resolve";



    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + RESOLVE_TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PROMISE_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new mantras to our sqlite database.
    public void addNewResolve(String mantra) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(PROMISE_COL, mantra);

        // after adding all values we are passing
        // content values to our table.
        db.insert(RESOLVE_TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();

        // TO View the sqlite date select "Device File Explorer" from android studio. Download the sqlite file from your device and view in "SQLite Browser".
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + RESOLVE_TABLE_NAME);
        onCreate(db);
    }

    public boolean onUpdateResolve(MyResolvesModel myResolve){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROMISE_COL, myResolve.getMyPromise());

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(myResolve.getId()) };

        boolean updateSuccessful = db.update(RESOLVE_TABLE_NAME, values, where, whereArgs) > 0;

        db.close();

        return updateSuccessful;

    }

    public boolean deleteSingleRow(int rowId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RESOLVE_TABLE_NAME, ID_COL + "=" + rowId, null) > 0;
    }

    // we have created a new method for reading all the courses.
    public ArrayList<MyResolvesModel> readMantras() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorMantras = db.rawQuery("SELECT * FROM " + RESOLVE_TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<MyResolvesModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorMantras.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new MyResolvesModel(cursorMantras.getInt(0),
                        cursorMantras.getString(1)));
            } while (cursorMantras.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorMantras.close();
        return courseModalArrayList;
    }


}

