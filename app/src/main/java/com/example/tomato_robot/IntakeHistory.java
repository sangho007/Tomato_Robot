package com.example.tomato_robot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IntakeHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<IntakeItem> intakeItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_history);

        AppVariable app = (AppVariable) getApplicationContext();
        intakeItems = app.getIntakeItems();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new IntakeAdapter(intakeItems));
    }





    static class IntakeItem {
        public void setDate(String date) {
            this.date = date;
        }

        private String date;

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        private int imageId;

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWeight() {
            return weight;
        }

        private String weight;

        public IntakeItem(String date, String weight) {
            this.date = date;
            this.weight = weight;

            if(Integer.parseInt(this.weight.toString()) >= 400){
                this.imageId = R.drawable.yes_icon;
            }else{
                this.imageId = R.drawable.no_icon;
            }
        }

        public String getDate() {
            return date;
        }

        public int getImageId() {
            return imageId;
        }
    }

    static class IntakeAdapter extends RecyclerView.Adapter<IntakeAdapter.ViewHolder> {
        private List<IntakeItem> mData;

        public IntakeAdapter(List<IntakeItem> data) {
            this.mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.intake_history_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            IntakeItem intakeItem = mData.get(position);
            holder.title.setText(intakeItem.getDate());

            holder.icon.setImageResource(intakeItem.getImageId());

            holder.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, IntakeDetail.class);
                    intent.putExtra("date",intakeItem.getDate());
                    intent.putExtra("weight",intakeItem.getWeight());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView icon;

            Button btn_detail;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.intake_date);
                icon = itemView.findViewById(R.id.intake_yes_no_icon);
                btn_detail = itemView.findViewById(R.id.btn_view_intake_detail);
            }
        }
    }
}
