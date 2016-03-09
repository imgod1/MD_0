package com.example.md_0;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar_main;
    private RecyclerView recyclerV_main;
    private List<String> name_list;
    private MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        name_list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            name_list.add("Hello World I'm The " + i);
        }

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerV_main.setLayoutManager(layoutManager);
        myadapter = new MyAdapter(this, name_list);
        // 设置适配器
        recyclerV_main.setAdapter(myadapter);

        myadapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(view, "Hello Click\t" + position, Snackbar.LENGTH_SHORT).show();
            }
        });

        myadapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Hello Long Click\t" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
        recyclerV_main = (RecyclerView) findViewById(R.id.recyclerV_main);
        setSupportActionBar(toolbar_main);
    }
}
