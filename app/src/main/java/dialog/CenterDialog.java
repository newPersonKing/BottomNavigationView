package dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mj.mibd.R;

public class CenterDialog extends BaseDialog {


    public CenterDialog(int layoutId,@NonNull Context context) {
        super(layoutId,context);
    }

    public CenterDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CenterDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setGravity(Gravity.CENTER);
    }

    @Override
    public View getContentView() {
        View view=View.inflate(getContext(), layoutId,null);
        return view;
    }


}
