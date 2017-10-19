package com.example.huchuan.wordsbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huchuan.wordsbook.model.Words;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ResultFragment extends Fragment {

    public ResultFragment() {
    }
    private LinearLayout cover;
    private TextView word;
    private ImageButton BrEmp3;
    private TextView BrE;
    private ImageButton AmEmp3;
    private TextView AmE;
    private TextView defs;
    private TextView sams;
    private String BrEmp3Url;
    private String AmEmp3Url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        cover=view.findViewById(R.id.cover);
        word=view.findViewById(R.id.word);
        BrEmp3=view.findViewById(R.id.BrEmp3);
        BrE=view.findViewById(R.id.BrE);
        AmEmp3=view.findViewById(R.id.AmEmp3);
        AmE=view.findViewById(R.id.AmE);
        defs=view.findViewById(R.id.defs);
        sams=view.findViewById(R.id.sams);
        return view;
    }

    public void setContent(final Words words) {
        if(words.getWord()!=null){
            final Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    word.setText(words.getWord());
                    BrE.setText("英:["+words.getBrE()+"]");
                    BrEmp3Url=words.getBrEmp3();
                    AmEmp3Url=words.getAmEmp3();
                    AmE.setText("美:["+words.getAmE()+"]");
                    try {
                        JSONArray defsA=words.getDefsList();
                        JSONArray samsA=words.getSamsList();
                        String defsStr="";
                        String samsStr="";
                        for (int i=0;i<defsA.length();i++){
                            JSONObject o=defsA.getJSONObject(i);
                            defsStr+=o.getString("pos")+"  "+o.getString("def")+"\n";
                        }
                        for (int i=0;i<samsA.length();i++){
                            JSONObject o=samsA.getJSONObject(i);
                            samsStr+=o.getString("eng")+"\n"+o.getString("chn")+"\n\n";
                        }
                        defs.setText(defsStr);
                        sams.setText(samsStr);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    cover.setVisibility(View.INVISIBLE);
                }
            };
            Thread thread=new Thread(){
                @Override
                public void run() {
                    getActivity().runOnUiThread(runnable);
                }
            };
            thread.start();

        }
    }
}
