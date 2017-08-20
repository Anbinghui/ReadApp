package com.jxkj.readapp.common.share;

/**
 * Created by An on 2017/6/13.
 */

public class Constant {
    public static String BASE_PATH = "/book/";
    public static final int NORMAL_PAGE_SIZE = 10;
    public static String PATH_DATA = "/cache";

    public static final String SUFFIX_TXT = ".txt";
    public static final String SUFFIX_PDF = ".pdf";
    public static final String SUFFIX_EPUB = ".epub";
    public static final String SUFFIX_ZIP = ".zip";
    public static final String SUFFIX_CHM = ".chm";
    public interface StoryType {
        String STORY_HOME = "home";
        String STORY_THEME = "theme";
    }

    public interface WebViewSetting {
        String SP_NO_IMAGE = "no_image";
        String SP_AUTO_CACHE = "auto_cache";
    }

    public static final int NET_OK = 200;
}
