package com.sunreal.Memo;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
/**
 *
 */
public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView listview; //Listview布局

    View view;

    // 数据源只能是特定泛型的集合。
    //内容和时间的集合
    private List<Map<String, Object>> dataList;
    //增加按钮
    private Button addNote;
    //内容view

    private TextView tv_note_id ;




    private DatabaseOperation dop;
    //数据库
    private SQLiteDatabase db;


    private SearchView mSearchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitView();
    }





    private void InitView() {

        listview = (ListView) findViewById(R.id.listview);
        dataList = new ArrayList<Map<String, Object>>();
        addNote = (Button) findViewById(R.id.btn_editnote);

        mSearchView = (SearchView) findViewById(R.id.searchView);


        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override

            public boolean onQueryTextSubmit(String query) {

                return false;

            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null) {
                    showNotesList(newText);
                }
                else{
                    showNotesList(null);
                }
                return false;
            }
        });




        dop = new DatabaseOperation(this, db);

        //设置监听点击时间
        listview.setOnItemClickListener(this);
        //长按
        listview.setOnItemLongClickListener(this);
        //添加
        addNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //启用新增页面
                Intent intent = new Intent(MainActivity.this, NoteEdit.class);
                intent.putExtra("editModel", "newAdd");
                startActivity(intent);
            }
        });





    }


    //在activity显示的时候更新listview
    @Override
    protected void onStart() {

        super.onStart();
        //显示记事列表
        showNotesList(null);
    }



    //显示记事列表
    private void showNotesList(String mSearchView){
        //创建或打开数据库
        dop.create_db();
        Cursor cursor=null;
        if(mSearchView!=null){
            cursor = dop.query_db(mSearchView);
        }
        else {
        cursor=dop.query_db();
        }
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item,
                cursor,
                new String[]{"_id", "title", "time"}, new int[]{R.id.tv_note_id, R.id.tv_note_title, R.id.tv_note_time});
        listview.setAdapter(adapter);

        dop.close_db();

    }



    //视图被点击

    //记事列表单击监听器


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            tv_note_id = (TextView)view.findViewById(R.id.tv_note_id);
            int item_id = Integer.parseInt(tv_note_id.getText().toString());
            Intent intent = new Intent(MainActivity.this,NoteEdit.class);
            intent.putExtra("editModel", "update");
            intent.putExtra("noteId", item_id);
            startActivity(intent);
        }



    //长按事件
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        //alert提示类
        tv_note_id = (TextView)view.findViewById(R.id.tv_note_id);
        final int item_id = Integer.parseInt(tv_note_id.getText().toString());
        Builder builder = new Builder(this);
        builder.setTitle("删除便签");
        builder.setMessage("确认删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dop.create_db();
                dop.delete_db(item_id);
                dop.close_db();
                //刷新列表显示
                listview.invalidate();
                showNotesList(null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
        return true;
    }






}
