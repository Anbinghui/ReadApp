package com.jxkj.readapp.view.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.BatteryManager;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import com.jxkj.readapp.R;
import com.jxkj.readapp.bean.CollectionBookBean;
import com.jxkj.readapp.util.AppUtils;
import com.jxkj.readapp.util.CharSetUtil;
import com.jxkj.readapp.util.DpPxUtils;
import com.jxkj.readapp.util.LogUtil;
import com.jxkj.readapp.util.SpUtil;
import com.jxkj.readapp.util.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by An on 2017/8/22.
 */

public class PageFactory {

    private int screenHeight,screenWidth;
    private int pageHeight,pageWidth;
    private int  lineNum;
    private int lineSpace  = DpPxUtils.dip2px(AppUtils.getAppContext(),5);
    private long fileLength;
    private int fontSize;
    private Paint mPaint;
    private int begin;
    private int end;
    private MappedByteBuffer mappedFile;//映射到内存中的文件
    private RandomAccessFile randomFile;//关闭Random流时使用
    private String encoding;
    private Context mContext;
    private static final int margin = DpPxUtils.dip2px(AppUtils.getAppContext(),5);//文字显示距离屏幕实际尺寸的偏移量
    private SpUtil mSputil;
    private boolean isNightMode;
    private PageView pageView;
    private Canvas mCanvas;
    private List<String> content = new ArrayList<>();
    private CollectionBookBean book;


    private static PageFactory instance = null;
    private PageFactory (){}
    public static PageFactory getInstance(PageView view,CollectionBookBean book){
        synchronized (PageFactory.class) {
            if(instance == null) {
                instance = new PageFactory(view);
                instance.openBook(book);

            }
        }
        return instance;
    }

    private PageFactory(PageView view) {
        DisplayMetrics metrics =    new DisplayMetrics();
        mContext  = view.getContext();
        pageView = view;
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        mSputil = SpUtil.init(mContext,SpUtil.BOOK_INFO, Context.MODE_APPEND);
        isNightMode = mSputil.getBoolean(SpUtil.BOOK_IS_NIGHT);
        fontSize = mSputil.getInt(SpUtil.FONTSIZE);
        pageHeight = screenHeight - margin*2  -fontSize;
        pageWidth = screenWidth  - margin*2;
        lineNum = pageHeight/(fontSize+lineSpace);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(fontSize);
        mPaint.setColor(isNightMode ? ContextCompat.getColor(mContext, R.color.nightModeTextColor) :
                ContextCompat.getColor(mContext,R.color.dayModeTextColor));
        Bitmap bitmap = Bitmap.createBitmap(screenWidth,screenHeight,Bitmap.Config.ARGB_8888);
        pageView.setmBitmap(bitmap);
        mCanvas = new Canvas(bitmap);






    }

    private void openBook(CollectionBookBean book) {
        this.book = book;
        begin = mSputil.getInt(book.get_id()+SpUtil.BOOK_MARK_BEGIN);
        end = mSputil.getInt(book.get_id()+SpUtil.BOOK_MARK_END);
        File file = new File(book.getPath());
        encoding = CharSetUtil.getCharset(file);
        fileLength = file.length();
        try {
            randomFile = new RandomAccessFile(file,"r");
            mappedFile = randomFile.getChannel().map(FileChannel.MapMode.READ_ONLY,0,fileLength);

        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeShortText(mContext,"打开文件失败");
        }


    }


    /**向后读取段落
     * @param end
     * @return
     */
    private byte[] readNextParagraph(int end) {
        byte be;
        int befor = 0;
        int i = end;
        while (i<fileLength) {
            be = mappedFile.get(i);
            if(encoding.equals("UTF-16LE")){
                if(be==0 && befor==10) {
                    break;
                }
            }else {
                if(be==10) {
                    break;
                }
            }
            befor = be;
            i++;
        }
        i = (int) Math.min(fileLength-1,i);

        int mPareSize = i-end+1;
        byte[] buffer = new byte[mPareSize];
        for (int i1 = 0; i1 < mPareSize; i1++) {
            buffer[i1] = mappedFile.get(end+i1);
        }
        return buffer;
    }

    /**读取上一段内容
     * @param begin
     * @return
     */
    private byte[] readBeforParagraph(int begin) {
        byte b0;
        byte before = 1;
        int i= begin-1;
        while(i > 0){
            b0 = mappedFile.get(i);
            if(encoding.equals("UTF-16LE")){
                if(b0 == 10 && before==0 && i != begin-2){
                    i+=2;
                    break;
                }
            }
            else{
                if(b0 == 0x0a && i != begin -1 ){
                    i++;
                    break;
                }
            }
            i--;
            before = b0;
        }
        int nParaSize = begin -i ;
        byte[] buf = new byte[nParaSize];
        for (int j = 0; j < nParaSize; j++) {
            buf[j] = mappedFile.get(i + j);
        }
        return buf;

    }

    /**
     * 读取下一页的内容
     */
    private  void pageDown() {
        LogUtil.i("阅读内容","pageDown"+"size:"+content.size()+"   lineNum:"+lineNum+"   end"+end+"   file"+fileLength);
        String strpara = "";
        try {
            while ((content.size()<lineNum) && (end<fileLength)) {
                byte[] byteTemp = readNextParagraph(end);
                end+= byteTemp.length;
                strpara = new String(byteTemp,encoding);
                strpara = strpara.replaceAll("\r\n","");
                strpara = strpara.replaceAll("\n","");
                while (strpara.length()>0) {
                    int size= mPaint.breakText(strpara,true,pageWidth,null);
                    content.add(strpara.substring(0,size));
                    strpara  = strpara.substring(size);
                    if(content.size()>=lineNum) {
                        break;
                    }

                }
                if(strpara.length()>0) {
                    end-= strpara.getBytes(encoding).length;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.makeShortText(mContext,"呀,出错了");
        }
    }

    /**
     * 上翻页
     */
    private void pageUp() {
        LogUtil.i("阅读内容","pageUp");
        String strPara = "";
        List<String> tempList = new ArrayList<>();
        while (tempList.size()<lineNum && begin>0) {
            byte[] byteTemp = readBeforParagraph(begin);
            begin-=byteTemp.length;

            try {
                strPara = new String(byteTemp,encoding);
                strPara = strPara.replaceAll("\r\n","");
                strPara = strPara.replaceAll("\n","");
                while (strPara.length()>0) {
                    int size = mPaint.breakText(strPara,true,pageWidth,null);
                    tempList.add(strPara.substring(0,size));
                    strPara = strPara.substring(size);
                    if (tempList.size()>lineNum) {
                        break;
                    }
                }
                if(strPara.length()>0) {
                    begin+=strPara.getBytes(encoding).length;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 画页面?
     */
    private void printPage() {
        LogUtil.i("阅读内容","printPage");
        if(content.size()>0) {
            int y = margin;
            if(isNightMode) {
                mCanvas.drawColor(ContextCompat.getColor(mContext,R.color.nightModeBackgroundColor));
            }else {
                mCanvas.drawColor(ContextCompat.getColor(mContext,R.color.dayModeBackgroundColor));
            }
            for (String str : content) {
                y+=fontSize+lineSpace;
                mCanvas.drawText(str,margin,y,mPaint);
            }
            float percent = (float) begin/fileLength*100;
            DecimalFormat format = new DecimalFormat("#0.00");
            String readProgress = format.format(percent)+"%";
            int length = (int) mPaint.measureText(readProgress);
            mCanvas.drawText(readProgress,(screenWidth-length)/2,screenHeight-margin,mPaint);
            //显示时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            mCanvas.drawText("时间"+time,margin,screenHeight-margin,mPaint);
            //显示电量
            String battery = getBattery();
            float[] widths = new float[battery.length()];
            float batteryLevelStringWidth = 0;
            mPaint.getTextWidths(battery,widths);
            for (float v : widths) {
                batteryLevelStringWidth+=v;
            }
            mCanvas.drawText(battery,screenWidth-margin-batteryLevelStringWidth,
                    screenHeight-margin,mPaint);
            pageView.invalidate();
        }

    }

    /**通过关键字搜索位置
     * @param beginPos
     * @param key
     * @return
     */
    private int searchPositionByKey(int beginPos,String key) {
        int position = beginPos;
        try {
            randomFile.seek(position);
            String keyString = new String(key.getBytes(encoding), Charset.forName("ISO-8859-1"));
            String temp;
            long pointer;
            for (;;) {
                pointer = randomFile.getFilePointer();
                temp = randomFile.readLine();
                if(temp!=null && temp.contains(keyString));
                position = (int) pointer;
                break;
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }

    /**获取手机电量
     * @return
     */
    private String getBattery() {
        Intent batteryIntent = mContext.registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int scaleLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        int scal = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        return String.valueOf(scaleLevel*100/scal);
    }

    /**
     * 下一页
     */
    public void nextPage() {
        LogUtil.i("阅读内容","nextPage");
        if(end>=fileLength) {
            ToastUtils.makeShortText(mContext,"已经是最后一页了");
            return;
        }else {
            content.clear();
            begin = end;
            pageDown();
        }
        printPage();
    }

    /**
     * 上一页
     */
    public void perPage() {
        if(begin<=0) {
            ToastUtils.makeShortText(mContext,"这里是第一页");
            return;
        }else {
            content.clear();
            pageUp();
            end = begin;
            pageDown();
        }
        printPage();

    }

    public void saveBookmark() {
        mSputil.put(SpUtil.BOOK_MARK_BEGIN,begin);
        mSputil.put(SpUtil.BOOK_MARK_END,end);

    }

    /**设置字体大小
     * @param size
     */
    public void setFontSize(int size) {
        if(size<15) {
            return;
        }
        fontSize = size;
        mPaint.setTextSize(fontSize);
        pageHeight = screenHeight-margin*2-fontSize;
        lineNum = pageHeight/(fontSize+lineSpace);
        end = begin;
        nextPage();
        mSputil.put(SpUtil.FONTSIZE,fontSize);
    }

    public void increaseFontSize() {setFontSize(fontSize+1);
    }
    public int getFontSize() {
        return fontSize;
    }
    public int getFileLength() {
        return (int) fileLength;
    }
    public MappedByteBuffer getMappedFile() {
        return mappedFile;
    }
    public void setPosition(int position){
        end = position;
        nextPage();
    }
    public int setProgress(int i) {
        int origin = begin;
        end = (int) (fileLength*i/100);
        if(end ==fileLength) {
            end--;
        }
        if(end ==0) {
            nextPage();
        }else {
            nextPage();
            perPage();
            nextPage();
        }
        return origin;

    }

    public int getProgress() {
        return (int) (begin*100/fileLength);
    }


    /**设置夜间模式
     * @param which
     */
    public void setNightMode(boolean which) {
        isNightMode = which;
        mPaint.setColor(which? ContextCompat.getColor(mContext,R.color.nightModeTextColor) :
                ContextCompat.getColor(mContext,R.color.dayModeTextColor));
        printPage();
    }

    public CollectionBookBean getBook() {
        return book;
    }

    public String getEncoding() {
        return encoding;
    }

    public int getCurrentEnd() {
        return end;
    }
    public int getCurrentBegin() {
        return begin;
    }

    public static void close() {
        if (instance != null) {
            try {
                instance.randomFile.close();
                instance =null;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
