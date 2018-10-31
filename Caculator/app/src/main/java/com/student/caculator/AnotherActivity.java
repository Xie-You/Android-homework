package com.student.caculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.*;
import java.lang.*;


public class AnotherActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_clear; //清除
    Button btn_del;   //删除

    Button btn_A;
    Button btn_B;
    Button btn_C;
    Button btn_D;
    Button btn_E;
    Button btn_F;

    Button btn_er;
    Button btn_shi;
    Button btn_shiliu;



    private TextView et_input;
    private StringBuilder pending = new StringBuilder();


    private void initView() {
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);

        btn_A = (Button) findViewById(R.id.btn_A);
        btn_B = (Button) findViewById(R.id.btn_B);
        btn_C = (Button) findViewById(R.id.btn_C);
        btn_D = (Button) findViewById(R.id.btn_D);
        btn_E = (Button) findViewById(R.id.btn_E);
        btn_F = (Button) findViewById(R.id.btn_F);


        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);

        btn_er = (Button) findViewById(R.id.btn_er);
        btn_shi = (Button) findViewById(R.id.btn_shi);
        btn_shiliu = (Button) findViewById(R.id.btn_shiliu);


        et_input = (TextView) findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);


        btn_A.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_D.setOnClickListener(this);
        btn_E.setOnClickListener(this);
        btn_F.setOnClickListener(this);


        btn_del.setOnClickListener(this);
        btn_clear.setOnClickListener(this);

        btn_er.setOnClickListener(this);
        btn_shi.setOnClickListener(this);
        btn_shiliu.setOnClickListener(this);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another2);

        initView();

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        int last = 0;
        if(pending.length()!=0)
        {
            last = pending.codePointAt(pending.length()-1);

        }

        switch (v.getId()) {

            case R.id.btn_0:
                pending = pending.append("0");
                et_input.setText(pending);
                break;
            case R.id.btn_1:
                pending = pending.append("1");
                et_input.setText(pending);
                break;
            case R.id.btn_2:
                pending = pending.append("2");
                et_input.setText(pending);
                break;
            case R.id.btn_3:
                pending = pending.append("3");
                et_input.setText(pending);
                break;
            case R.id.btn_4:
                pending = pending.append("4");
                et_input.setText(pending);
                break;
            case R.id.btn_5:
                pending = pending.append("5");
                et_input.setText(pending);
                break;
            case R.id.btn_6:
                pending = pending.append("6");
                et_input.setText(pending);
                break;
            case R.id.btn_7:
                pending = pending.append("7");
                et_input.setText(pending);
                break;
            case R.id.btn_8:
                pending = pending.append("8");
                et_input.setText(pending);
                break;
            case R.id.btn_9:
                pending = pending.append("9");
                et_input.setText(pending);
                break;

            case R.id.btn_A:
                pending = pending.append("A");
                et_input.setText(pending);
                break;

            case R.id.btn_B:
                pending = pending.append("B");
                et_input.setText(pending);
                break;

            case R.id.btn_C:
                pending = pending.append("C");
                et_input.setText(pending);
                break;


            case R.id.btn_D:
                pending = pending.append("D");
                et_input.setText(pending);
                break;


            case R.id.btn_E:
                pending = pending.append("E");
                et_input.setText(pending);
                break;


            case R.id.btn_F:
                pending = pending.append("F");
                et_input.setText(pending);
                break;



            case R.id.btn_del: //删除
                if (pending.length() != 0) {
                    pending = pending.delete(pending.length() - 1, pending.length());
                    et_input.setText(pending);
                }
                break;
            case R.id.btn_clear: //清空
                pending = pending.delete(0, pending.length());
                et_input.setText(pending);
                break;


            case R.id.btn_er:
                if(judge1()==0) {
                    et_input.setText(pending + "\n十进制  " + Integer.valueOf(String.valueOf(pending), 2).toString()
                            + "\n十六进制  " + Integer.toHexString(Integer.parseInt(String.valueOf(pending), 2))
                    );
                }
                else{
                    et_input.setText("输入有误");
                }
                break;

            case R.id.btn_shi:

                if(judge2()==0) {
                    int b=Integer.parseInt(String.valueOf(pending));
                    et_input.setText(pending + "\n二进制  " + Integer.toBinaryString(b)
                            + "\n十六进制  " + Integer.toHexString(b)
                    );
                }
                else{
                    et_input.setText("输入有误");
                }
                break;

            case R.id.btn_shiliu:
                et_input.setText(pending+ "\n二进制  "+ Integer.toBinaryString(Integer.valueOf(String.valueOf(pending),16))
                        + "\n十进制  " +Integer.valueOf(String.valueOf(pending),16).toString()
                );
                break;

            default:
                break;

        }
    }

    private int judge1(){

        int a = 0;
        for(int i = 0 ; i < pending.length() ;i++){
            if(pending.charAt(i)!='0'&&pending.charAt(i)!='1') {
                a=1;
            }

        }
        return a;
    }

    private int judge2(){

        int a = 0;
        for(int i = 0 ; i < pending.length() ;i++){
            if(pending.charAt(i)=='A'||pending.charAt(i)=='B'||pending.charAt(i)=='C'||pending.charAt(i)=='D'||pending.charAt(i)=='E'||pending.charAt(i)=='F') {
                a=1;
            }

        }
        return a;
    }

}
