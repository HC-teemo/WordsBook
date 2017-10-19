package com.example.huchuan.wordsbook;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.huchuan.wordsbook.model.Words;
import com.example.huchuan.wordsbook.util.HttpCallBackListener;
import com.example.huchuan.wordsbook.util.HttpUtil;
import com.example.huchuan.wordsbook.util.ParseJSON;
import com.example.huchuan.wordsbook.util.WordsHandler;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private TitleFragment titleFragment;
    private LogoFragment logoFragment;
    private SearchFragment searchFragment;
    private ContentFragment contentFragment;
    private ResultFragment resultFragmentl;
    private ImageButton btnSearch;
    private EditText input;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //初始化控件
        btnSearch=(ImageButton)this.findViewById(R.id.search);
        input=(EditText)this.findViewById(R.id.input);
        fragmentManager=getFragmentManager();
        titleFragment=(TitleFragment) fragmentManager.findFragmentById(R.id.id_fragment_title);
        logoFragment=(LogoFragment) fragmentManager.findFragmentById(R.id.id_fragment_logo);
        searchFragment=(SearchFragment) fragmentManager.findFragmentById(R.id.id_fragment_search);
        contentFragment=(ContentFragment) fragmentManager.findFragmentById(R.id.id_fragment_content);
        resultFragmentl=new ResultFragment();

    }


    //布局切换
    public void fragmentChange(){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.hide(logoFragment);
        transaction.replace(R.id.id_fragment_content,resultFragmentl);
        transaction.commit();
    }
    //搜索结果
    public void searchHandel(Words words){
        resultFragmentl.setContent(words);
    }
}
