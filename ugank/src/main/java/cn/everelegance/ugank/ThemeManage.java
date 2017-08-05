package cn.everelegance.ugank;

/**主题色管理
 * Created by Administrator on 2017/8/3.
 */

public enum ThemeManage {

    INSTANCE;

    private int colorPrimary;

    public  void initColorPrimary(int colorPrimary){
        setColorPrimary(colorPrimary);
    }


    public int getColorPrimary(){
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary){
        this.colorPrimary = colorPrimary;
    }
}
