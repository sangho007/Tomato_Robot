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
//    private ArrayList<IntakeItem> intakeItems;
    private List<ClassTomato> intakeTomatoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_history);

        TomatoDB tomatoDB = TomatoDB.getInstance(this);
        AppVariable app = (AppVariable) getApplicationContext();

        new Thread(new Runnable() {
            @Override
            public void run() {
                TomatoDao tomatoDao = tomatoDB.tomatoDao();
                intakeTomatoes = tomatoDao.getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView = findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(IntakeHistory.this));
                        recyclerView.setAdapter(new IntakeHistory.IntakeAdapter(intakeTomatoes));
                    }
                });
            }
        }).start();

    }



    static class IntakeAdapter extends RecyclerView.Adapter<IntakeAdapter.ViewHolder> {
        private List<ClassTomato> mData;

        public IntakeAdapter(List<ClassTomato> data) {
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
            ClassTomato intaketomato = mData.get(position);
            holder.title.setText(intaketomato.getDate());

            if(intaketomato.getWeight() >= 400){
                holder.icon.setImageResource(R.drawable.yes_icon);
            }else{
                holder.icon.setImageResource(R.drawable.no_icon);
            }


            holder.btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, IntakeDetail.class);
                    intent.putExtra("date",intaketomato.getDate());
                    intent.putExtra("weight",Integer.toString(intaketomato.getWeight()));
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
