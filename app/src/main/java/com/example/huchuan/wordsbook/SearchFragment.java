package com.example.huchuan.wordsbook;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.huchuan.wordsbook.model.Words;
import com.example.huchuan.wordsbook.util.HttpCallBackListener;
import com.example.huchuan.wordsbook.util.HttpUtil;
import com.example.huchuan.wordsbook.util.ParseJSON;
import com.example.huchuan.wordsbook.util.WordsAction;
import com.example.huchuan.wordsbook.util.WordsHandler;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;


public class SearchFragment extends Fragment {

    private EditText input;
    private ImageButton search;
    private WordsAction wordsAction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        input=(EditText)view.findViewById(R.id.input);
        search=(ImageButton)view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        wordsAction=WordsAction.getInstance(this.getActivity());
        return view;
    }



    public void search(){
        String inputWords=input.getText().toString();
        inputWords=inputWords.replace(" ", "");
        if(inputWords!=null&&!inputWords.equals("")){
            ((MainActivity)SearchFragment.this.getActivity()).fragmentChange();
            Words wordsInSql=wordsAction.getWordsFromSQLite(inputWords);
            if(wordsInSql.getWord()!=null){
                ((MainActivity)SearchFragment.this.getActivity()).searchHandel(wordsInSql,true);
            }
            else {
                HttpUtil.sentHttpRequest(wordsAction.getAddressForWords(inputWords), new HttpCallBackListener() {
                    @Override
                    public void onFinish(InputStream response) {
                        Log.i("请求状态","完成");
                        try {
                            Words words= WordsHandler.getWords(ParseJSON.parse(response));
                            ((MainActivity)SearchFragment.this.getActivity()).searchHandel(words,false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError() {
                        Log.i("请求状态","错误");
                    }
                });
            }
        }
    }
}
