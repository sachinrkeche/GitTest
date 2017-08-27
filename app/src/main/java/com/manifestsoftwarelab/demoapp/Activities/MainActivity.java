package com.manifestsoftwarelab.demoapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.manifestsoftwarelab.demoapp.Helper.CommonUtilities;

import android.util.Log;
import android.widget.Toast;

import com.manifestsoftwarelab.demoapp.Adapter.RecyclerItemAdapter;
import com.manifestsoftwarelab.demoapp.Async.AsyncListener;
import com.manifestsoftwarelab.demoapp.Async.GetDistrictInfoAsync;
import com.manifestsoftwarelab.demoapp.Helper.CommonUtilities;
import com.manifestsoftwarelab.demoapp.Helper.DatabaseHelper;
import com.manifestsoftwarelab.demoapp.Helper.IsNetConnectionAvailable;
import com.manifestsoftwarelab.demoapp.Pojo.InstitutePojo;
import com.manifestsoftwarelab.demoapp.Pojo.MainPojo;
import com.manifestsoftwarelab.demoapp.Pojo.StudentPojo;
import com.manifestsoftwarelab.demoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.manifestsoftwarelab.demoapp.Helper.CommonUtilities.JSON;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ArrayList<StudentPojo> InstitutePojoArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerItemAdapter recyclerItemAdapter;

    public  String JSON="{\"company_name\": \"Manifest software lab\",\"course_name\": \"Android}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerItemAdapter = new RecyclerItemAdapter(InstitutePojoArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerItemAdapter);


      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My District");*/

        databaseHelper = new DatabaseHelper(getApplicationContext());


        getDistrictData();
    }

    public void getDistrictData() {
                if (JSON != null) {
                    JSONArray jsonArray = null;
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(JSON);
                        jsonArray = jsonObject.getJSONArray("students");

                        Log.e("***",""+jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject studentsJsonObject = jsonArray.getJSONObject(i);
                            StudentPojo studentPojo = new StudentPojo();
                            studentPojo.setStudent_id(studentsJsonObject.getString("student_id"));
                            studentPojo.setStudent_name(studentsJsonObject.getString("student_name"));
                            studentPojo.setStudent_address(studentsJsonObject.getString("student_address"));
                            studentPojo.setStudent_qualification(studentsJsonObject.getString("student_qualification"));
                            studentPojo.setCompany_name(jsonObject.getString("company_name"));
                            studentPojo.setCourse_name(jsonObject.getString("course_name"));

                            InstitutePojoArrayList.add(studentPojo);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    WriteDataToLocalDB(InstitutePojoArrayList);
                    /*try {
                        JSONArray jsonArray = new JSONArray(result);
                        for (int i = 0; i < jsonArray.length(); i++) {
                              JSONObject jsonObject = jsonArray.getJSONObject(i);
                              MainPojo districtInfo = new MainPojo();
                              districtInfo.setName(jsonObject.getString("MAbout_DS"));

                            jsonArrayimages = jsonObject.getJSONArray("listds_img");
                            ArrayList<String> tempImages = new ArrayList<String>();
                            for (int j = 0; j < jsonArrayimages.length(); j++) {
                                JSONObject jsonObject_images = jsonArrayimages.getJSONObject(j);
                                tempImages.add(image_prefix + jsonObject_images.getString("DS_Img"));
                            }
                            districtInfo.setImage_urls(tempImages);
                            mainPojoArrayList.add(districtInfo);
                            setImagesData();
                        }
                        WriteDataToLocalDB(mainPojoArrayList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
//        if (IsNetConnectionAvailable.isConnectedMobile(this) || IsNetConnectionAvailable.isConnectedWifi(this)) {
//            new GetDistrictInfoAsync(MainActivity.this, listener).execute();
//        } else {
                    // new HideShowAppMessages().hideShowAppMessages(this);
//            getDistrictInfoFromDB();
//        }
                }
    }


    private void WriteDataToLocalDB(ArrayList<StudentPojo> districtInfoList) {
        databaseHelper.insertDistrictInfoToDBAsync(districtInfoList, new DatabaseHelper.DatabaseHandler<Void>() {
            @Override
            public void onComplete(boolean success, Void result) {
                if (success) {
                    Toast.makeText(MainActivity.this, "data added in SQLite", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, " data not set added in SQLite", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void getDistrictInfoFromDB() {
        databaseHelper.getDistrictInfoDetail(new DatabaseHelper.DatabaseHandler<ArrayList<MainPojo>>() {

            @Override
            public void onComplete(boolean success, ArrayList<MainPojo> result) {
                if (success) {
                    if (result != null) {
                        System.out.print("*********************" + result);
                 /*       locationPojoList = result;
                        locationAdapter = new LocationAdapter(locationPojoList);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.addItemDecoration(new DividerItemDecoration(LocationActivity.this, LinearLayoutManager.VERTICAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        locationAdapter.clickListenerOnLocation(LocationActivity.this);
                        recyclerView.setAdapter(locationAdapter);*/
                    } else {
                        Toast.makeText(getApplicationContext(), "from database gets null", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to get the Info from Data base", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
