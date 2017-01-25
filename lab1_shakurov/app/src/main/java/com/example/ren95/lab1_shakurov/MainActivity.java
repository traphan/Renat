package com.example.ren95.lab1_shakurov;

import android.inputmethodservice.ExtractEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> L_gramma = new ArrayList<String>();
    private ArrayList<String> R_gramma = new ArrayList<String>();
    private ArrayList<String> L_gramma_e = new ArrayList<String>();
    private ArrayList<String> R_gramma_e = new ArrayList<String>();
    private String[] str = {"0", "1", "e", "::=", " | "};
    private int n = 4;
    EditText neterm0;
    EditText neterm1;
    EditText neterm2;
    EditText neterm3;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    EditText edit8;
    EditText edit9;
    EditText edit10;
    EditText edit11;
    EditText edit12;
    ArrayList<View> layoutList;
    View layout1, layout2, layout3, layout4, layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        neterm0 = (EditText) findViewById(R.id.netrem0);
        neterm1 = (EditText) findViewById(R.id.neterm1);
        neterm2 = (EditText) findViewById(R.id.neterm2);
        neterm3 = (EditText) findViewById(R.id.neterm3);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        edit3 = (EditText) findViewById(R.id.edit3);
        edit4 = (EditText) findViewById(R.id.edit4);
        edit5 = (EditText) findViewById(R.id.edit5);
        edit6 = (EditText) findViewById(R.id.edit6);
        edit7 = (EditText) findViewById(R.id.edit7);
        edit8 = (EditText) findViewById(R.id.edit8);
        edit9 = (EditText) findViewById(R.id.edit9);
        edit10 = (EditText) findViewById(R.id.edit10);
        edit11 = (EditText) findViewById(R.id.edit11);
        edit12 = (EditText) findViewById(R.id.edit12);

        layout1 = (View) findViewById(R.id.view1);
        layout2 = (View) findViewById(R.id.view2);
        layout3 = (View) findViewById(R.id.view3);
        layout4 = (View) findViewById(R.id.view4);
        layout = (View) findViewById(R.id.view);
        Switch sw = (Switch)findViewById(R.id.switch1);
        sw.setChecked(true);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==false){
                    n=3;
                   layout4.setVisibility(View.INVISIBLE);
                }
                else {
                    n=4;
                    layout4.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void MatrToR_Gram(int[][] matr, int k, String[] noterm) {

        String temp = "";

        for (int i = 0; i < n; i++) {
            temp += noterm[i] + str[3];

            for (int j = 0; j < k; j++) {
                if (matr[i][j] != -1) {
                    if (j != k - 1)
                        temp += str[j] + noterm[matr[i][j]] + str[4];
                    else
                        temp += str[j] + str[4];
                }
            }
            if (temp.charAt(temp.length() - 2) == '|')
                temp = temp.substring(0, temp.length() - 3);
            R_gramma.add(temp);
            temp = "";
        }
    }

    private void MatrToR_Gram_e(int[][] matr, int k, String[] noterm) {
        String temp = "";

        for (int i = 0; i < n; i++) {
            temp += noterm[i] + str[3];

            for (int j = 0; j < k - 1; j++) {
                if (matr[i][j] != -1) {
                    temp += str[j] + noterm[matr[i][j]] + str[4];
                    if (matr[matr[i][j]][k - 1] == 1)
                        temp += str[j] + str[4];

                }
            }
            if (temp.charAt(temp.length() - 2) == '|')
                temp = temp.substring(0, temp.length() - 3);
            R_gramma_e.add(temp);
            temp = "";
        }
    }

    private void MatrToL_Gram(int[][] matr, int k, String[] noterm) {

        String temp = "";


        for (int i = 0; i < n; i++) {
            temp += noterm[i] + str[3];
            L_gramma.add(temp);
            temp = "";
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k - 1; j++) {
                if (matr[i][j] != -1) {
                    L_gramma.set(matr[i][j], L_gramma.get(matr[i][j]) + noterm[i] + str[j] + str[4]);
                }
            }
        }
        L_gramma.set(0, L_gramma.get(0) + str[k - 1]);
        for (int i = 0; i < n; i++)//удаление последней черты
        {
            int j = L_gramma.get(i).length();
            if (L_gramma.get(i).charAt(j - 2) == '|')
                L_gramma.set(i, L_gramma.get(i).substring(0, j - 3));

        }
    }

    private void MatrToL_Gram_e(int[][] matr, int k, String[] noterm)//без е
    {

        String temp = "";

        for (int i = 0; i < n; i++) {
            temp += noterm[i] + str[3];
            L_gramma_e.add(temp);
            temp = "";
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k - 1; j++) {
                if (matr[i][j] != -1) {
                    if (i != 0) {
                        L_gramma_e.set(matr[i][j], L_gramma_e.get(matr[i][j]) + noterm[i] + str[j] + str[4]);
                    } else {
                        L_gramma_e.set(matr[i][j], L_gramma_e.get(matr[i][j]) + noterm[i] + str[j] + str[4] + str[j] + str[4]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++)//удаление последней черты
        {
            int j = L_gramma_e.get(i).length();
            if (L_gramma_e.get(i).charAt(j - 2) == '|')
                L_gramma_e.set(i, L_gramma_e.get(i).substring(0, j - 3));

        }
    }


    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 1; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            if (child instanceof EditText) {
                ArrayList<View> viewArrayList = new ArrayList<View>();
                viewArrayList.addAll(getAllChildren(child));
                result.addAll(viewArrayList);
            } else {
                if (child instanceof ViewGroup) {
                    ArrayList<View> viewArrayList = new ArrayList<View>();
                    viewArrayList.addAll(getAllChildren(child));
                    result.addAll(viewArrayList);
                }
            }
        }

        return result;
    }

    public void getgramma(View view) {

        int  k = 3;
        int[][] matr;
        String str_temp = "";
        matr = new int[n][k];
        layoutList = new ArrayList<>();


        String[] neterm = {neterm0.getText().toString(), neterm1.getText().toString(), neterm2.getText().toString(), neterm3.getText().toString()};

        ArrayList<View> layoutList = new ArrayList<>();
        layoutList = this.getAllChildren(layout);

int index=1;
        for (int j = 0; j < 12; j++) // считывание данных, создание матрицы
        {

            TextView ctrl = (TextView) findViewById(layoutList.get(j).getId());
            str_temp = ctrl.getText().toString();

            if (n * k >= index) {
                if ((index - 1) % k == k - 1) {
                    if (str_temp.equals("1"))
                        matr[(index - 1) / k][(index - 1) % k] = 1;
                    else {
                        matr[(index - 1) / k][(index - 1) % k] = -1;
                    }
                } else {
                    int f=0;
                    if (str_temp.equals("")) {//добавил
                        f = -1;
                    }
                    if(f==0){
                    for ( int i = 0; i < n;i++) {
                            if (str_temp.equals(neterm[i])) {
                                f = i;
                                break;
                            }
                        }
                    }
                    if (f < n)
                        matr[(index - 1) / k][(index - 1) % k] = f;
                 /*   else {
                        matr[(index - 1) / k][(index - 1) % k] = -1;
                    }*/
                }
            }
            index++;
        }

        R_gramma.clear();
        L_gramma.clear();
        R_gramma_e.clear();
        L_gramma_e.clear();
        MatrToR_Gram(matr, k, neterm);
        MatrToL_Gram(matr, k, neterm);
        MatrToL_Gram_e(matr, k, neterm);
        MatrToR_Gram_e(matr, k, neterm);
        TextView textL = (TextView) findViewById(R.id.textView);
        textL.append("Левостороння грамматика\n");
        for (int g = 0; g < n; g++)//Вывод
        {
            textL.append(L_gramma.get(g) + "\n");
        }
        textL.append("\n");
        for (int g = 0; g < n; g++)//Вывод
        {
            textL.append(L_gramma_e.get(g) + "\n");
        }
        textL.append("Правосторонняя грамматика\n");
        for (int g = 0; g < n; g++)//Вывод без e
        {
            textL.append(R_gramma.get(g) + "\n");
        }
        textL.append("\n");
        for (int g = 0; g < n; g++)//Вывод без e
        {
            textL.append(R_gramma_e.get(g) + "\n");
        }

    }

    public void pressButtonClear(View view) {
 TextView l=(TextView)findViewById(R.id.textView);
        l.setText("");
        ArrayList<View> layoutList = new ArrayList<>();
        layoutList = this.getAllChildren(layout);
        for(int i=0;i<12;i++){
            TextView ctrl = (TextView) findViewById(layoutList.get(i).getId());
            ctrl.setText("");
        }
    }



}




