package com.example.zhangpeng.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private static final String TAG = "ListViewActivity";
    private ListView lvContetn;
    private   FloatingActionButton fab;
    private Toolbar toolbar;
    private  List<String> stringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initView();
        addContent();
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * 初始化view
     */
    public void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        lvContetn= (ListView) findViewById(R.id.lv_content);
        stringList=new ArrayList<>();
    }
    /**
     * 向listView内添加内容
     */
    public void addContent(){
        for(int i=0;i<20;i++){
            stringList.add("你好"+i);
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,stringList);
        lvContetn.setAdapter(adapter);
        //用于控制子view动画效果
        LayoutAnimationController layoutAnimationController= new LayoutAnimationController(AnimationUtils.loadAnimation(this,R.anim.zoom_in));
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lvContetn.setLayoutAnimation(layoutAnimationController);
        lvContetn.startLayoutAnimation();
    }

}
