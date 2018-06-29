package dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mj.mibd.R;


public abstract class BaseDialog extends Dialog {
    public int layoutId;
    public BaseDialog(int layoutId,@NonNull Context context) {
        super(context);
        this.layoutId=layoutId;
        requestWindowFeature(Window.FEATURE_NO_TITLE);/*去除对话框的标题*/
//        getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);/*设置对话框边框背景 必须在代码中设置对话框背景，不然对话框背景是黑色的*/
        setContentView(getContentView());
        setDimAmount(0.2f);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);/*去除对话框的标题*/
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);/*设置对话框边框背景 必须在代码中设置对话框背景，不然对话框背景是黑色的*/
        setContentView(getContentView());
        setDimAmount(0.2f);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public BaseDialog setGravity(int gravity){
        getWindow().setGravity(gravity);
        return this;
    }

    public BaseDialog isCanceledOnTouchOutside(boolean isCaccle){

        /*设置点击是否消失*/
        setCanceledOnTouchOutside(isCaccle ? true : false);
        return  this;
    }

    public BaseDialog setWidthandHeight(int width,int height){
        /*设置宽度*/
        getWindow().setLayout(width, height);
        return this;
    }

    protected abstract View getContentView();

    /*
  动画类型
   */
    public BaseDialog setAnimType(AnimInType animInType) {

        if (animInType == AnimInType.CENTER) {
            getWindow().setWindowAnimations(R.style.dialog_zoom);
            return this;

        }
        if (animInType == AnimInType.LEFT) {
            getWindow().setWindowAnimations(R.style.dialog_anim_left);
            return this;

        }
        if (animInType == AnimInType.TOP) {
            getWindow().setWindowAnimations(R.style.dialog_anim_top);
            return this;

        }
        if (animInType == AnimInType.RIGHT) {
            getWindow().setWindowAnimations(R.style.dialog_anim_right);
            return this;

        }
        if (animInType == AnimInType.BOTTOM) {
            getWindow().setWindowAnimations(R.style.dialog_anim_bottom);
            return this;

        }
        return this;
    }


    /*
  设置背景阴影,必须setContentView之后调用才生效
   */
    public BaseDialog setDimAmount(float dimAmount) {

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = dimAmount;
        return this;
    }


    /*
 动画类型
  */
    public enum AnimInType {
        CENTER(0),
        LEFT(2),
        TOP(4),
        RIGHT(3),
        BOTTOM(1);
        AnimInType(int n) {
            intType = n;
        }
        final int intType;
    }
}
