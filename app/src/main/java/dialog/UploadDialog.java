package dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.mj.mibd.R;

public class UploadDialog extends BaseDialog {

    private UpLoadDialogCallBack callBack;
    public UploadDialog(int layoutId, @NonNull Context context) {
        super(layoutId,context);
    }

    public UploadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UploadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setGravity(Gravity.CENTER);
    }
    public void setClickListener(UpLoadDialogCallBack callBack){
        this.callBack=callBack;
    }

    @Override
    public View getContentView() {
        View view=View.inflate(getContext(), R.layout.dialog_center_main_upload,null);
        view.findViewById(R.id.upload_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(v);
            }
        });
        view.findViewById(R.id.upload_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClick(v);
            }
        });
        return view;
    }


    public interface UpLoadDialogCallBack{
        void onClick(View view);
    }

}
