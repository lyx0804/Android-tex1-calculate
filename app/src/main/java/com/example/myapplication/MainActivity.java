package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

//定义实现监听接口的类MainActivity
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //声明17个按钮和一个文本编辑框
    Button bt_num0,bt_num1,bt_num2,bt_num3,bt_num4,bt_num5,bt_num6,bt_num7,
            bt_num8,bt_num9,bt_delete,bt_div,bt_mult,bt_minus,bt_plus,bt_equal,bt_dot,bt_clear;
    private EditText et_input;
    boolean clr_flag;   //判断文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //显示activity_main.xml定义的用户界面
        initViewAndListener();  //调用函数initViewAndListener()
    }

    //定义函数initViewAndListener()，用于与用户界面程序中的组件建立关联，并分别注册监听接口
    private void initViewAndListener() {
        bt_num0 = (Button)findViewById(R.id.bt_num0);
        bt_num0.setOnClickListener(this);

        bt_num1 = (Button)findViewById(R.id.bt_num1);
        bt_num1.setOnClickListener(this);


        bt_num2 =(Button) findViewById(R.id.bt_num2);
        bt_num2.setOnClickListener(this);

        bt_num3 = (Button)findViewById(R.id.bt_num3);
        bt_num3.setOnClickListener(this);

        bt_num4 = (Button)findViewById(R.id.bt_num4);
        bt_num4.setOnClickListener(this);

        bt_num5 = (Button)findViewById(R.id.bt_num5);
        bt_num5.setOnClickListener(this);

        bt_num6 = (Button)findViewById(R.id.bt_num6);
        bt_num6.setOnClickListener(this);

        bt_num7 = (Button)findViewById(R.id.bt_num7);
        bt_num7.setOnClickListener(this);

        bt_num8 = (Button)findViewById(R.id.bt_num8);
        bt_num8.setOnClickListener(this);

        bt_num9 = (Button)findViewById(R.id.bt_num9);
        bt_num9.setOnClickListener(this);

        bt_delete = (Button)findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(this);

        bt_div = (Button)findViewById(R.id.bt_div);
        bt_div.setOnClickListener(this);

        bt_mult = (Button)findViewById(R.id.bt_mult);
        bt_mult.setOnClickListener(this);

        bt_minus = (Button)findViewById(R.id.bt_minus);
        bt_minus.setOnClickListener(this);


        bt_plus = (Button)findViewById(R.id.bt_plus);
        bt_plus.setOnClickListener(this);

        bt_equal = (Button)findViewById(R.id.bt_equal);
        bt_equal.setOnClickListener(this);

        et_input= (EditText)findViewById(R.id.et_input);

        bt_dot = (Button)findViewById(R.id.bt_dot);
        bt_dot.setOnClickListener(this);

        bt_clear = (Button)findViewById(R.id.bt_clear);
        bt_clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        switch (v.getId()){
            case   R.id.bt_num0:
            case   R.id.bt_num1:
            case   R.id.bt_num2:
            case   R.id.bt_num3:
            case   R.id.bt_num4:
            case   R.id.bt_num5:
            case   R.id.bt_num6:
            case   R.id.bt_num7:
            case   R.id.bt_num8:
            case   R.id.bt_num9:
            case   R.id.bt_dot:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.bt_plus:
            case R.id.bt_minus:
            case R.id.bt_mult:
            case R.id.bt_div:
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                if(str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/")) {
                    str=str.substring(0,str.indexOf(" "));
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.bt_clear:
                if(clr_flag)
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                break;
            case R.id.bt_delete: //判断是否为空，然后在进行删除
                if(clr_flag){
                    clr_flag=false;
                    str="";
                    et_input.setText("");
                }
                else if(str!=null&&!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.bt_equal: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {
        String exp=et_input.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0;
        //s1和s2都不是空
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("*")){
                cnt=d1*d2;
            }
            if(op.equals("/")){
                if(d2==0) Toast.makeText(this,"除数不能为0",Toast.LENGTH_SHORT).show();
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("/")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1不是空    s2是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("*")){
                cnt=0;
            }
            if(op.equals("/")){
                cnt=0;
            }
            if(!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("*")){
                cnt=0;
            }
            if(op.equals("/")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }
}