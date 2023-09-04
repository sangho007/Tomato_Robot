package com.example.tomato_robot;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        initData();

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new IntakeAdapter(intakeItems));
    }

    private void initData() {
        intakeItems = new ArrayList<>();
        intakeItems.add(new IntakeItem("Item Title 1", R.drawable.yes_icon));
        intakeItems.add(new IntakeItem("Item Title 2", R.drawable.yes_icon));
        // 다른 항목들도 여기에 추가하여 표시할 아이템을 목록에 추가합니다.
    }

    static class IntakeItem {
        private String date;
        private int imageId;

        public IntakeItem(String date, int imageId) {
            this.date = date;
            this.imageId = imageId;
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
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView icon;

            public ViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.intake_date);
                icon = itemView.findViewById(R.id.intake_yes_no_icon);
            }
        }
    }
}
