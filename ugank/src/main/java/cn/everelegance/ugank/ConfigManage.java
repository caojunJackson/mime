package cn.everelegance.ugank;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/8/3.
 */

public enum  ConfigManage {

    INSTANCE;
    private static final String spName  = "app_config";
    private static final String key_isListShowImg  = "isListShowImg";
    private static final String key_thumbanilQuality  = "thumbnailQiuality";
    private static final String key_banner_url  = "keyBannerUrl";
    private static final String key_launcher_img_show  = "keyLauncherImgShow";
    private static final String key_launcher_img_probability_show  = "keyLauncherImgProbabilityShow";


    private  boolean isListShowImg ;
    private  int thumbanilQuality;
    private String bannerUrl;
    private boolean isShowLauncherImg;
    private boolean isProbabilityShowLauncherImag;

    private  void initConfig(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName, Context
                .MODE_PRIVATE);

        isListShowImg = sharedPreferences.getBoolean(key_isListShowImg,false);
        thumbanilQuality =sharedPreferences.getInt(key_thumbanilQuality , 1);
        bannerUrl = sharedPreferences.getString(key_banner_url, "");
        isShowLauncherImg = sharedPreferences.getBoolean(key_launcher_img_show, false);
        isProbabilityShowLauncherImag = sharedPreferences.getBoolean
                (key_launcher_img_probability_show, false);

    }


    public boolean isListShowImg() {
        return isListShowImg;
    }

    public void setListShowImg(boolean listShowImg) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, 0);
        boolean commit = sharedPreferences.edit().putBoolean(key_isListShowImg, listShowImg)
                .commit();
        if (commit) {
            isListShowImg = listShowImg;
        }
    }

    public int getThumbanilQuality() {
        return thumbanilQuality;
    }

    public void setThumbanilQuality(int thumbanilQuality) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, 0);
        boolean commit = sharedPreferences.edit().putInt(key_thumbanilQuality, thumbanilQuality)
                .commit();
        if (commit) {
            this.thumbanilQuality = thumbanilQuality;
        }
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, 0);
        boolean commit = sharedPreferences.edit().putString(key_banner_url, bannerUrl).commit();
        if (commit) {
            this.bannerUrl = bannerUrl;
        }
    }

    public boolean isShowLauncherImg() {
        return isShowLauncherImg;
    }

    public void setShowLauncherImg(boolean showLauncherImg) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, 0);
        boolean commit = sharedPreferences.edit().putBoolean(key_launcher_img_show,
                showLauncherImg).commit();
        if (commit) {
            isShowLauncherImg = showLauncherImg;
        }

    }

    public boolean isProbabilityShowLauncherImag() {
        return isProbabilityShowLauncherImag;
    }

    public void setProbabilityShowLauncherImag(boolean probabilityShowLauncherImag) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(spName, 0);
        boolean commit = sharedPreferences.edit().putBoolean(key_launcher_img_probability_show,
                probabilityShowLauncherImag).commit();
        if (commit) {
            isProbabilityShowLauncherImag = probabilityShowLauncherImag;
        }
    }
}
