package com.manifestsoftwarelab.demoapp.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.manifestsoftwarelab.demoapp.Pojo.InstitutePojo;
import com.manifestsoftwarelab.demoapp.Pojo.MainPojo;
import com.manifestsoftwarelab.demoapp.Pojo.StudentPojo;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Manifest.db";
    Context context;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public void updateInstantPosition() {
        /*deleteLastposition();
        ContentValues values = new ContentValues();
        values.put("deviceId", position.getDeviceId());
        values.put("time", position.getTime().getTime());
        values.put("latitude", position.getLatitude());
        values.put("longitude", position.getLongitude());
        values.put("altitude", position.getAltitude());
        values.put("speed", position.getSpeed());
        values.put("course", position.getCourse());
        values.put("battery", position.getBattery());
        values.put("event", position.getEvent());
        db.insertOrThrow("lastposition", null, values);*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   /*     db.execSQL("CREATE TABLE root (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Route_Id INTEGER," +
                "Route_Name TEXT," +
                "Created_Date DATE" +
                "Tracker_Date DATE" +
                "Status TEXT" +
                "Countstatus TEXT)");*/

  /*      db.execSQL("CREATE TABLE subcategoris (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cat_id INTEGER," +
                "sub_cat_Id INTEGER," +
                "sub_cat_name TEXT," +
                "isCheck INTEGER)");

        db.execSQL("CREATE TABLE questions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "que_Id TEXT," +
                "question TEXT," +
                "option1 TEXT," +
                "option2 TEXT," +
                "option3 TEXT," +
                "option4 TEXT," +
                "answer TEXT)")*/
        ;
    }

    public void createTableroot() {
        db.execSQL("CREATE TABLE root (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Route_Id TEXT," +
                "Route_Name TEXT," +
                "Status TEXT," +
                "Countstatus TEXT)");
    }

    public void createTableLocation() {
        db.execSQL("CREATE TABLE location (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Location_Id TEXT," +
                "Location_Name TEXT," +
                "Route_Name TEXT," +
                "Status TEXT," +
                "After_img TEXT," +
                "Route_Id TEXT," +
                "Before_img TEXT)");
    }

    public void FirstImageTable() {
        db.execSQL("CREATE TABLE firstimage (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Location_Id TEXT," +
                "Route_Id TEXT," +
                "Image_Date TEXT," +
                "Encoded_Image TEXT)");
    }

    public void SecondImageTable() {
        db.execSQL("CREATE TABLE secondimage (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Location_Id TEXT," +
                "Route_Id TEXT," +
                "Second_Image_Date TEXT," +
                "Encoded_Image TEXT)");
    }

    public void createTableZPInfo() {
        db.execSQL("CREATE TABLE zpinfo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "District_Info TEXT," +
                "Image TEXT)");
    }

    public void createTableDistrictInfo() {
        db.execSQL("CREATE TABLE StudentData (" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_name TEXT," +
                "insti_name TEXT," +
                "student_address TEXT");
        Log.e("**insert**","inserted succesfully");

    }

    public void createTableProjectInfo() {
        db.execSQL("CREATE TABLE project (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "images TEXT," +
                "cost TEXT," +
                "ceo TEXT," +
                "president TEXT," +
                "department TEXT," +
                "duration TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS root;");
        onCreate(db);
    }

    public void drop_table(String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }


   /* public void insertZPInfoToDBAsync(final ArrayList<ZPInfoPojo> zpInfoPojoArrayList, DatabaseHandler<Void> handler) {
        new DatabaseAsyncTask<Void>(handler) {
            @Override
            protected Void executeMethod() {
                insertZPInfo(zpInfoPojoArrayList);
                return null;
            }
        }.execute();
    }*/




  /*  public void insertZPInfo(ArrayList<ZPInfoPojo> zpInfoPojoArrayList) {
        drop_table("zpinfo");
        createTableZPInfo();
        for (ZPInfoPojo zpInfoPojo : zpInfoPojoArrayList) {
            ContentValues values = new ContentValues();
            values.put("District_Info", zpInfoPojo.getDistrict_info());
            ArrayList<String> stringUrlList = zpInfoPojo.getStringList();
            for (String s : stringUrlList) {
                String encodedImage = Base64.encodeToString(getByteImage(s), Base64.DEFAULT);
                values.put("Image", encodedImage);
            }
            db.insertOrThrow("zpinfo", null, values);
        }
    }
*/


    public void insertDistrictInfoToDBAsync(final ArrayList<StudentPojo> districtInfoPojoArrayList, DatabaseHandler<Void> handler) {
        new DatabaseAsyncTask<Void>(handler) {
            @Override
            protected Void executeMethod() {
                insertDistrictInfo(districtInfoPojoArrayList);
                return null;
            }
        }.execute();
    }

    public void insertDistrictInfo(ArrayList<StudentPojo> districtInfoPojoArrayList) {
        drop_table("StudentData");
        createTableDistrictInfo();
        for (StudentPojo districtInfoPojo : districtInfoPojoArrayList) {
            ContentValues values = new ContentValues();
            values.put("company_name", districtInfoPojo.getCompany_name());
            values.put("student_name", districtInfoPojo.getStudent_name());
            values.put("student_address", districtInfoPojo.getStudent_address());
            db.insertOrThrow("StudentData", null, values);
        }
        getDistrictInfo();
    }

    public void getAllData()
    {


    }


  /*  public void insertprojectInfoToDBAsync(final ArrayList<ProjectPojo> projectPojoArrayList, DatabaseHandler<Void> handler) {
        new DatabaseAsyncTask<Void>(handler) {
            @Override
            protected Void executeMethod() {
                insertProjectInfo(projectPojoArrayList);
                return null;
            }
        }.execute();
    }*/



  /*  public void insertProjectInfo(ArrayList<ProjectPojo> projectPojoArrayList) {
        drop_table("project");
        createTableProjectInfo();
        for (ProjectPojo projectPojo : projectPojoArrayList) {
            ContentValues values = new ContentValues();
            values.put("name", projectPojo.getProjectName());
            values.put("duration", projectPojo.getEstimatedDuration());
            values.put("cost", projectPojo.getApproxCosting());
            values.put("ceo", projectPojo.getCEOName());
            values.put("president", projectPojo.getPresidentName());
            values.put("department", projectPojo.getDeparmentName());
            values.put("duration", projectPojo.getEstimatedDuration());
            ArrayList<String> stringUrlList = projectPojo.getImageUrlList();
            for (String s : stringUrlList) {
                String encodedImage = Base64.encodeToString(getByteImage(s), Base64.DEFAULT);
                values.put("images", encodedImage);
            }
            db.insertOrThrow("project", null, values);
        }
    }
*/

  /*  public void getZPInfoDetail(DatabaseHandler<ArrayList<ZPInfoPojo>> handler) {
        new DatabaseAsyncTask<ArrayList<ZPInfoPojo>>(handler) {
            @Override
            protected ArrayList<ZPInfoPojo> executeMethod() {
                return getZPInfo();
            }
        }.execute();
    }*/



   /* public ArrayList<ZPInfoPojo> getZPInfo() {
        ArrayList<ZPInfoPojo> zpInfoPojoArrayList = new ArrayList<>();
        //String[] settingsProjection = {"Route_Id", "Location_Id", "Location_Name", "Route_Name", "Status", "Before_img", "After_img"};
        Cursor cursor = db.rawQuery("SELECT * FROM zpinfo", null);
        String imgurl = cursor.getString(cursor.getColumnIndex("Image"));
        try {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        ZPInfoPojo tempZPInfo = new ZPInfoPojo();
                        tempZPInfo.setDistrict_info(cursor.getString(cursor.getColumnIndex("District_Info")));
                        ArrayList<String> stringUrlList = tempZPInfo.getStringList();
                        for (String s : stringUrlList) {
                            s = cursor.getString(cursor.getColumnIndex("Image"));
                            *//*problem is here to  show the image *//*
                        }
                        tempZPInfo.setStringList(stringUrlList);
                        zpInfoPojoArrayList.add(tempZPInfo);
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return zpInfoPojoArrayList;
    }
*/


    public void getDistrictInfoDetail(DatabaseHandler<ArrayList<MainPojo>> handler) {
        new DatabaseAsyncTask<ArrayList<MainPojo>>(handler) {
            @Override
            protected ArrayList<MainPojo> executeMethod() {

                return null;
            }
        }.execute();
    }

    public void getDistrictInfo() {
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData", null);
        String studentName = cursor.getString(cursor.getColumnIndex("student_name"));
        String compayName = cursor.getString(cursor.getColumnIndex("company_name"));
        try {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        Log.e("**student name**",studentName);
                        Log.e("**company name**",compayName);
//                        districtInfoPojoArrayList.add(tempDistrictInfo);
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }


    public byte[] getByteImage(String url) {
        try {
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayBuffer baf = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }

            return baf.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

    public interface DatabaseHandler<T> {
        void onComplete(boolean success, T result);
    }

    private static abstract class DatabaseAsyncTask<T> extends AsyncTask<Void, Void, T> {
        private DatabaseHandler<T> handler;
        private RuntimeException error;

        public DatabaseAsyncTask(DatabaseHandler<T> handler) {

            this.handler = handler;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                return executeMethod();
            } catch (RuntimeException error) {
                this.error = error;
                return null;
            }
        }

        protected abstract T executeMethod();

        @Override
        protected void onPostExecute(T result) {
            handler.onComplete(error == null, result);
        }
    }

}
