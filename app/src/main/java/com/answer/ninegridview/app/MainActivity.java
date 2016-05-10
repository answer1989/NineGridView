package com.answer.ninegridview.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.answer.ninegridview.lib.NineGridAdapter;
import com.answer.ninegridview.lib.NineGridView;
import com.answer.ninegridview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(new RecyclerViewViewAdapter());

        NineGridView nineGridView = (NineGridView)findViewById(R.id.nine_grid_view);
        NineGridAdapter nineGridAdapter = new DemoNineGridAdapter(MainActivity.this, generateDemoList(new Random().nextInt(10)));
        nineGridView.setNineGridAdapter(nineGridAdapter);

    }

    private List<String> generateDemoList(int size) {
        List<String> imageUrl = new ArrayList<>();
        int count;
        if (size <= 0) {
            count = 1;
        } else {
            count = size;
        }
        for (int i = 0; i < count; i++) {
            imageUrl.add("http://img5.duitang.com/uploads/item/201306/24/20130624230830_UWTWS.thumb.700_0.jpeg");
        }
        return imageUrl;
    }


    class RecyclerViewViewAdapter extends RecyclerView.Adapter<RecyclerViewViewAdapter.MomentViewHolder> {


        @Override
        public MomentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MomentViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_view_moment, parent, false));
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        @Override
        public void onBindViewHolder(MomentViewHolder holder, int position) {
            NineGridAdapter nineGridAdapter = new DemoNineGridAdapter(MainActivity.this, generateDemoList(new Random().nextInt(10)));
            holder.nineGridView.setNineGridAdapter(nineGridAdapter);
        }

        public class MomentViewHolder extends RecyclerView.ViewHolder {

            NineGridView nineGridView;

            public MomentViewHolder(View itemView) {
                super(itemView);
                nineGridView = (NineGridView) itemView.findViewById(R.id.nine_grid_view);
            }
        }
    }
}
