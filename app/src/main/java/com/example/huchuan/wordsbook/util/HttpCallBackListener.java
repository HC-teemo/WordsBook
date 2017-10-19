package com.example.huchuan.wordsbook.util;

import java.io.InputStream;

/**
 * Created by huchuan on 2017/10/16.
 */

public interface HttpCallBackListener {
    public void onFinish(InputStream response);
    public void onError();
}
