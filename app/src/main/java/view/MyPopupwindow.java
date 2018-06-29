package view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.mj.mibd.R;

public class MyPopupwindow extends PopupWindow {

    private Context mContext;

    private int layoutId;
    //PopupWindow的contentView
    private View mContentView;
    private int height;
    public MyPopupwindow(Context context,int layoutId) {
        super(context);
        mContext = context;
        this.layoutId=layoutId;
        ColorDrawable dw = new ColorDrawable( 0000000000);
        this.setBackgroundDrawable(dw);
        initContentView();
    }
    /**
     * 初始化contentView
     */
    private void initContentView() {

        mContentView = LayoutInflater.from(mContext).inflate(layoutId, null);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mContentView.measure(w,h);

        setFocusable(true);
        setContentView(mContentView);
    }

    public View getContainer(){
        return mContentView;
    }

    public int getHEIGHR(){
        return  height;
    }
}
