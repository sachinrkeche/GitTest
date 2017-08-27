package com.manifestsoftwarelab.demoapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manifestsoftwarelab.demoapp.Pojo.InstitutePojo;
import com.manifestsoftwarelab.demoapp.Pojo.MainPojo;
import com.manifestsoftwarelab.demoapp.Pojo.StudentPojo;
import com.manifestsoftwarelab.demoapp.R;

import java.util.List;

/**
 * Created by DELL on 8/27/2017.
 */

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.MyCustomView> {
    private List<StudentPojo> mainPojoList;
    public RecyclerItemAdapter(List<StudentPojo> mainPojos) {
        this.mainPojoList = mainPojos;
    }

    @Override
    public MyCustomView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row_recycler, parent, false);
        return new MyCustomView(itemView);
    }

    @Override
    public void onBindViewHolder(MyCustomView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyCustomView extends RecyclerView.ViewHolder {
        private TextView txt_hopspital_area, txt_hopspital_name;
        public MyCustomView(View itemView) {
            super(itemView);
//            txt_hopspital_area = (TextView) itemView.findViewById(R.id.txt_hopspital_area);
//            txt_hopspital_name = (TextView) itemView.findViewById(R.id.txt_hopspital_name);
//            itemView.setOnClickListener(this);

        }
    }
}
