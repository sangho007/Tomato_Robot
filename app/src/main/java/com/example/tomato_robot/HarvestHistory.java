package com.example.tomato_robot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HarvestHistory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<HarvestItem> harvestItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_history);

        initData();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HarvestAdapter((harvestItems)));

    }

    private void initData(){
        harvestItems = new ArrayList<>();
        harvestItems.add(new HarvestItem("first","123g"));
        harvestItems.add(new HarvestItem("second","456g"));
    }

    static class HarvestItem {
        private String date ;
        private String weight;

        public HarvestItem(String date, String weight){
            this.date = date;
            this.weight = weight;
        }

        public String getDate(){
            return date;
        }

        public String getWeight(){
            return weight;
        }
    }

    public static class HarvestAdapter extends RecyclerView.Adapter<HarvestAdapter.ViewHolder> {
        private List<HarvestItem> mData;

        public HarvestAdapter(List<HarvestItem> data) {
            this.mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.harvest_history_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            HarvestItem harvestItem = mData.get(position);
            holder.date.setText(harvestItem.getDate());
            holder.weight.setText(harvestItem.getWeight());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView date;
            TextView weight;

            public ViewHolder(View itemView) {
                super(itemView);
                date = itemView.findViewById(R.id.harvest_date);
                weight = itemView.findViewById(R.id.harvest_weight);
            }
        }
    }

}