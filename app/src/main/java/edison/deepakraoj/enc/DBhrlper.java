package edison.deepakraoj.enc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak Rao J on 6/15/2017.
 */

public class DBhrlper extends SQLiteOpenHelper {

    public static String DATABASENAME="testt.db";
    public String TABLENAME="embed";
    public static int DATABASEVERSION=1;
    public static SQLiteDatabase sdb=null;
    public Context con=null;
    public String SNO="sno";
    String path=null;

    String DEVICENO="deviceno";
    String DEVICENAME="deveicename";
    String TIME="time";
    String DATEE="date";

String Creattable="CREATE TABLE "+TABLENAME+" ("+SNO+" INTEGER PRIMARY KEY, "+DEVICENO+" TEXT, "+DEVICENAME+" TEXT, "+TIME+" TEXT, "+DATEE+" TEXT)";

    public DBhrlper(Context context) {
        super(context,DATABASENAME,null,DATABASEVERSION);
        path="/data/data"+context.getPackageName()+"/databases/"+DATABASENAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Creattable);
        Log.d("DATABASE", "Database Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS "+Creattable);
        Log.d("DATABASE", "Database upda");
        onCreate(db);
    }

    public void  open()
    {
        try {
        sdb=this.getWritableDatabase();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public boolean checkdata(){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, TABLENAME, new String[]{DEVICENO,DEVICENAME},null,
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
            /*{
                //updating details
                ContentValues cv = new ContentValues();



                cv.put(DEVICENO,dno);
                cv.put(DEVICENAME,dname);

                return	sdb.update(TABLENAME, cv, SNO + "=1", null)>0;

            }else*/{
                //inserting data







                return true;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }
    public String gettimefromdb(String dno) {
        // List<String> roomnames=new ArrayList<String>();
        // Select All Query
        String temp = null;
        //String selectQuery = "SELECT " + ROOM_NAME_SENSOR_TABLE + " FROM " + SENSOR_TABLE_NAME;

        // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = null;
        try {
            cursor = sdb.query(true, TABLENAME, new String[] { DEVICENO,TIME },
                    DEVICENO + "='" + dno + "'", null, null, null, null, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    temp = (cursor.getString(cursor.getColumnIndex(TIME)));

                    // Adding contact to list
                    // devicecontactList.add(device);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return temp;
    }
    public String getdatefromdb(String dno) {
        // List<String> roomnames=new ArrayList<String>();
        // Select All Query
        String temp = null;
        //String selectQuery = "SELECT " + ROOM_NAME_SENSOR_TABLE + " FROM " + SENSOR_TABLE_NAME;

        // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = null;
        try {
            cursor = sdb.query(true, TABLENAME, new String[] { DEVICENO,TIME,DATEE },
                    DEVICENO + "='" + dno + "'", null, null, null, null, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    temp = (cursor.getString(cursor.getColumnIndex(DATEE)));

                    // Adding contact to list
                    // devicecontactList.add(device);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        // return contact list
        return temp;
    }

    public boolean insertOrUpdate_Logintype(String dno,String dname,String tim,String dat){

        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, TABLENAME, new String[]{DEVICENO,DEVICENAME,TIME,DATEE},SNO+"=1",
                    null, null, null, null,null);


            /*{
                //updating details
                ContentValues cv = new ContentValues();



                cv.put(DEVICENO,dno);
                cv.put(DEVICENAME,dname);

                return	sdb.update(TABLENAME, cv, SNO + "=1", null)>0;

            }else*/{
                //inserting data
                ContentValues cv = new ContentValues();




                cv.put(DEVICENO,dno);
                cv.put(DEVICENAME,dname);
                cv.put(TIME,tim);
                cv.put(DATEE,dat);

                return sdb.insert(TABLENAME, null, cv)>0;

            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return false;
    }



    public List<String> fetch(){
        List<String> list=new ArrayList<String>();
        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, TABLENAME, new String[]{DEVICENO,DEVICENAME},null,
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
                if (mcursor.moveToFirst()) {
                    do {


                      String   dn=(mcursor.getString(mcursor.getColumnIndex(DEVICENO)));
                       // String   dna=(mcursor.getString(mcursor.getColumnIndex(DEVICENAME)));
                       // String temp=dn+","+dna;
                        list.add(dn);


                    } while (mcursor.moveToNext());
                }



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return list;
    }


    public List<String> fetchdname(){
        List<String> list=new ArrayList<String>();
        Cursor mcursor=null;
        try{//SNO+"=1"
            mcursor=sdb.query(true, TABLENAME, new String[]{DEVICENO,DEVICENAME},null,
                    null, null, null, null,null);

            if(mcursor!=null && mcursor.getCount()>0)
                if (mcursor.moveToFirst()) {
                    do {


                        //String   dn=(mcursor.getString(mcursor.getColumnIndex(DEVICENO)));
                        String   dna=(mcursor.getString(mcursor.getColumnIndex(DEVICENAME)));
                       // String temp=dn+","+dna;
                        list.add(dna);


                    } while (mcursor.moveToNext());
                }



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //closing cursor
            if(mcursor!=null){
                mcursor.close();
            }
        }

        return list;
    }

    public void close()
    {
        try {
            if (sdb != null) {
                sdb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
