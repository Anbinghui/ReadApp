package com.jxkj.readapp.util;

import android.util.Log;


public class DebugUtils {
    // 使用Log来显示调试信息,因为log在实现上每个message有4k字符长度限制
    // 所以这里使用自己分节的方式来输出足够长度的message
    public static void show(String tag, String content) {
        if (content.length() > 4000) {
            int chunkCount = content.length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= content.length()) {
                    Log.e(tag, "chunk " + i + " of " + chunkCount + ":" + content.substring(4000 * i));
                } else {
                    Log.e(tag, "chunk " + i + " of " + chunkCount + ":" + content.substring(4000 * i, max));
                }
            }
        } else {
            Log.e(tag, content.toString());
        }
    }
}
