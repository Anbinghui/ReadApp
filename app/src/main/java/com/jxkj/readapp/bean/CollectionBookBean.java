package com.jxkj.readapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by An on 2017/8/18.
 */
@Entity
public class CollectionBookBean  implements Serializable{
    private  String _id;
    private String author;
    private String cover;
    private String shortIntro;
    private String title;
    private boolean hasCp;
    private boolean isTop = false;
    private boolean isSeleted = false;
    private boolean showCheckBox = false;
    private boolean isFromSD = false;
    private String path = "";
    private int latelyFollower;
    private double retentionRatio;
    private String updated = "";
    private int chaptersCount;
    private String lastChapter;
    private String recentReadingTime;
    private String encoding;

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getRecentReadingTime() {
        return this.recentReadingTime;
    }
    public void setRecentReadingTime(String recentReadingTime) {
        this.recentReadingTime = recentReadingTime;
    }
    public String getLastChapter() {
        return this.lastChapter;
    }
    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }
    public int getChaptersCount() {
        return this.chaptersCount;
    }
    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }
    public String getUpdated() {
        return this.updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public double getRetentionRatio() {
        return this.retentionRatio;
    }
    public void setRetentionRatio(double retentionRatio) {
        this.retentionRatio = retentionRatio;
    }
    public int getLatelyFollower() {
        return this.latelyFollower;
    }
    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public boolean getIsFromSD() {
        return this.isFromSD;
    }
    public void setIsFromSD(boolean isFromSD) {
        this.isFromSD = isFromSD;
    }
    public boolean getShowCheckBox() {
        return this.showCheckBox;
    }
    public void setShowCheckBox(boolean showCheckBox) {
        this.showCheckBox = showCheckBox;
    }
    public boolean getIsSeleted() {
        return this.isSeleted;
    }
    public void setIsSeleted(boolean isSeleted) {
        this.isSeleted = isSeleted;
    }
    public boolean getIsTop() {
        return this.isTop;
    }
    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }
    public boolean getHasCp() {
        return this.hasCp;
    }
    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getShortIntro() {
        return this.shortIntro;
    }
    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String get_id() {
        return this._id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    @Generated(hash = 855822281)
    public CollectionBookBean(String _id, String author, String cover,
            String shortIntro, String title, boolean hasCp, boolean isTop,
            boolean isSeleted, boolean showCheckBox, boolean isFromSD, String path,
            int latelyFollower, double retentionRatio, String updated,
            int chaptersCount, String lastChapter, String recentReadingTime,
            String encoding) {
        this._id = _id;
        this.author = author;
        this.cover = cover;
        this.shortIntro = shortIntro;
        this.title = title;
        this.hasCp = hasCp;
        this.isTop = isTop;
        this.isSeleted = isSeleted;
        this.showCheckBox = showCheckBox;
        this.isFromSD = isFromSD;
        this.path = path;
        this.latelyFollower = latelyFollower;
        this.retentionRatio = retentionRatio;
        this.updated = updated;
        this.chaptersCount = chaptersCount;
        this.lastChapter = lastChapter;
        this.recentReadingTime = recentReadingTime;
        this.encoding = encoding;
    }

    @Generated(hash = 1902107094)
    public CollectionBookBean() {
    }
}
