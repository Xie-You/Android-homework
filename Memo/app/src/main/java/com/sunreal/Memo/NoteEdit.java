package com.sunreal.Memo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NoteEdit extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_note_time;
    private TextView tv_title;
    private SQLiteDatabase db;
    private DatabaseOperation dop;
    private EditText et_Notes;

    private Button btn_ok;
    private Button btn_cancel;
    private Button btn_clear;

    Intent intent;
    String editModel = null;
    int item_Id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        InitView();
    }
    private void InitView() {
        tv_note_time = (TextView) findViewById(R.id.tv_note_time);
        et_Notes = (EditText) findViewById(R.id.et_content);
//      tv_title = (TextView) findViewById(R.id.tv_note_title);



        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_clear = (Button) findViewById(R.id.btn_clear) ;
        dop = new DatabaseOperation(this,db);
        intent = getIntent();
        editModel = intent.getStringExtra("editModel");
        item_Id = intent.getIntExtra("noteId", 0);
        loadData();


        //获取时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = sdf.format(date);
        tv_note_time.setText(dateString);



        btn_cancel.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }


    //加载数据
    private void loadData(){

        //如果是新增记事模式，则将editText清空
        if(editModel.equals("newAdd")){
            et_Notes.setText("");
        }
        //如果编辑的是已存在的记事，则将数据库的保存的数据取出，并显示在EditText中
        else if(editModel.equals("update")){


            dop.create_db();
            Cursor cursor = dop.query_db(item_Id);
            cursor.moveToFirst();
            //取出数据库中相应的字段内容
            String context = cursor.getString(cursor.getColumnIndex("context"));
            et_Notes.append(context);
            dop.close_db();

        }
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                //取得EditText中的内容
                String context = et_Notes.getText().toString();
                //截取EditText中的前一部分作为标题，用于显示在主页列表中
                String title ;
                if(context.length()<10){
                    title = context;
                }
                else{
                    title = context.substring(0,10);
                }



                if(context.isEmpty()){
                    Toast.makeText(NoteEdit.this, "记事不能为空!", Toast.LENGTH_LONG).show();
                }
                else{
                    //取得当前时间
                    SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm");
                    Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
                    String   time   =   formatter.format(curDate);


                    //打开数据库
                    dop.create_db();
                    //判断是更新还是新增记事
                    if(editModel.equals("newAdd")){
                        //将记事插入到数据库中
                        dop.insert_db(title,context,time);
                    }
                    //如果是编辑则更新记事即可
                    else if(editModel.equals("update")){
                        dop.update_db(title,context,time,item_Id);
                    }
                    dop.close_db();
                    //结束当前activity
                    NoteEdit.this.finish();
                }
                break;


            case R.id.btn_cancel:
                finish();
                break;


            case R.id.btn_clear:
                et_Notes.setText("");
                break;

        }
    }


}
