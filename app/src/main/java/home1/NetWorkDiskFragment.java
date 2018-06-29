package home1;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.algorithm.android.utils.AlRecyclerViewDecoration;
import com.mj.mibd.R;


import java.util.ArrayList;
import java.util.List;

import adapter.MorePicAdapterDelegate;
import adpterimpl.BaseViewHolder;
import adpterimpl.MyAdapter;
import bean.BtEntry;
import bean.FolderEntry;
import bean.HeaderEntry;
import bean.ImageEntry;
import bean.ItemEntry;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.utils.Logger;
import delegationadapterimpl.BindCallBack;
import dialog.CenterDialog;
import dialog.UploadDialog;
import until.ScreenUtils;
import view.MyPopupwindow;

public class NetWorkDiskFragment extends Fragment {

    RecyclerView recyclerView;
    MyAdapter adapter;

    private CenterDialog centerDialog;
    private MyPopupwindow popupwindow;
    private LinearLayout ll_upload,ll_category,ll_upload_list;
    int screenWidth;
    private UploadDialog dialog;

    private MyPopupwindow categorypopupwindow;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.layout_nest_work_disk,null);
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ll_upload=view.findViewById(R.id.ll_upload);
        ll_category=view.findViewById(R.id.ll_category);
        ll_upload_list=view.findViewById(R.id.ll_upload_list);
        ll_upload.setOnClickListener(clickListener);
        ll_category.setOnClickListener(clickListener);
        ll_upload_list.setOnClickListener(clickListener);
        screenWidth= ScreenUtils.getScreenWidth(getActivity());
        adapter=new MyAdapter();

        adapter.addDelegate(new MorePicAdapterDelegate(R.layout.layout_net_work_recy_item, new BindCallBack<ItemEntry>() {
            @Override
            public void onBind(BaseViewHolder holder, final int position, ItemEntry item) {
                       holder.setImageResource(R.id.item_img,R.mipmap.icon_small_doc);
                holder.setText(R.id.name,"doc文件夹名称");
                holder.setText(R.id.time,"2018.08.06");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"这是第"+position+"item",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }),ItemEntry.class.getName());

        adapter.addDelegate(new MorePicAdapterDelegate(R.layout.layout_net_work_recy_item, new BindCallBack<BtEntry>() {
            @Override
            public void onBind(BaseViewHolder holder, final int position, BtEntry item) {
                holder.setImageResource(R.id.item_img,R.mipmap.icon_small_bt);
                holder.setText(R.id.name,"bt文件夹名称");
                holder.setText(R.id.time,"2018.08.06");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"这是第"+position+"item",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }), BtEntry.class.getName());

        adapter.addDelegate(new MorePicAdapterDelegate(R.layout.layout_net_work_recy_item, new BindCallBack<FolderEntry>() {
            @Override
            public void onBind(BaseViewHolder holder, final int position, FolderEntry item) {
                holder.setImageResource(R.id.item_img,R.mipmap.icon_small_folder);
                holder.setText(R.id.name,"folder文件夹名称");
                holder.setText(R.id.time,"2018.08.06");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"这是第"+position+"item",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }), FolderEntry.class.getName());

        adapter.addDelegate(new MorePicAdapterDelegate(R.layout.layout_net_work_recy_item, new BindCallBack<ImageEntry>() {
            @Override
            public void onBind(BaseViewHolder holder, final int position, ImageEntry item) {
                holder.setImageResource(R.id.item_img,R.mipmap.icon_small_image);
                holder.setText(R.id.name,"image名称");
                holder.setText(R.id.time,"2018.08.06");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"这是第"+position+"item",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }), ImageEntry.class.getName());

        adapter.addDelegate(new MorePicAdapterDelegate(R.layout.layout_net_work_top, new BindCallBack<HeaderEntry>() {
            @Override
            public void onBind(BaseViewHolder holder, final int position, HeaderEntry item) {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),"这是第"+position+"item",Toast.LENGTH_SHORT).show();
                    }
                });

                holder.getView(R.id.net_work_make_folder).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerDialog.show();
                    }
                });
                final View make_sort=holder.getView(R.id.net_work_make_sort);
                holder.getView(R.id.net_work_make_sort).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupwindow.setWidth(screenWidth/3);
                        popupwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupwindow.showAsDropDown(make_sort,0,0);

                    }
                });
                holder.getView(R.id.net_work_make_search).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),NetWorkSearchActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }),HeaderEntry.class.getName());

        initData2();
        recyclerView.addItemDecoration(new AlRecyclerViewDecoration(getResources(), R.color.background,
                R.dimen.dp_1, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);

        categorypopupwindow=new MyPopupwindow(getActivity(),R.layout.layout_pop_window_top);
        categorypopupwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        categorypopupwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog=new UploadDialog(R.layout.dialog_center_main_upload,getActivity());
        dialog.setClickListener(new UploadDialog.UpLoadDialogCallBack() {
            @Override
            public void onClick(View view) {

                switch (view.getId()){
                    case R.id.upload_img:
                        openImageSelectMultiMethod(1);
                        break;
                    case R.id.upload_video:
                        openVideoSelectMultiMethod(0);
                        break;

                }
            }
        });
        centerDialog=new CenterDialog(R.layout.dialog_make_folder,getActivity());
        popupwindow=new MyPopupwindow(getActivity(),R.layout.layout_net_work_sort);
        createAnimation();
        return view;
    }


    private void initData2() {
        List<ItemEntry> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new ItemEntry());
        }
        adapter.addDataItems(list);

        List<BtEntry> btlist=new ArrayList<>();
        for (int i=0;i<10;i++){
            btlist.add(new BtEntry());
        }
        adapter.addDataItems(btlist);

        List<ImageEntry> imglist=new ArrayList<>();
        for (int i=0;i<10;i++){
            imglist.add(new ImageEntry());
        }
        adapter.addDataItems(imglist);

        List<FolderEntry> folderlist=new ArrayList<>();
        for (int i=0;i<10;i++){
            folderlist.add(new FolderEntry());
        }
        adapter.addDataItems(folderlist);

        adapter.addHeaderItem(new HeaderEntry());
    }

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_upload:
                    dialog.show();
                    break;
                case R.id.ll_category:
                    categorypopupwindow.getContainer().clearAnimation();
                    categorypopupwindow.showAsDropDown(ll_category,0,0);
                    categorypopupwindow.getContainer().setAnimation(animationSet);
                    animationSet.start();
                    break;
                case R.id.ll_upload_list:
                    Intent intent=new Intent(getActivity(),UploadListActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    AnimationSet animationSet;
    private void createAnimation(){
        animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation(0,0,-categorypopupwindow.getContainer().getMeasuredHeight(),0);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(1000);

    }



    /**
     * OPEN 图片多选实现方法
     * <p>
     * 默认使用 第三个 ，如果运行sample,可自行改变Type，运行Demo查看效果
     */
    private void openImageSelectMultiMethod(int type) {
        switch (type) {
            case 0:

                //使用默认的参数
                RxGalleryFinalApi
                        .getInstance(getActivity())
                        .setImageMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选图片的回调");
                                    }
                                }).open();

                break;
            case 1:

                //使用自定义的参数
                RxGalleryFinalApi
                        .getInstance(getActivity())
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_IMAGE, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI)
                        .setImageMultipleResultEvent(new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                Logger.i("多选图片的回调");
                            }
                        }).open();

                break;
            case 2:

                //直接打开
                RxGalleryFinalApi.openMultiSelectImage(getActivity(), new RxBusResultDisposable<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        Logger.i("多选图片的回调");
                    }
                });

                break;
        }
    }

    /**
     * 视频多选回调
     */
    private void openVideoSelectMultiMethod(int type) {
        switch (type) {
            case 0:

                //使用默认的参数
                RxGalleryFinalApi
                        .getInstance(getActivity())
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 1:

                //使用自定义的参数
                RxGalleryFinalApi
                        .getInstance(getActivity())
                        .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_MULTI)
                        .setVDMultipleResultEvent(
                                new RxBusResultDisposable<ImageMultipleResultEvent>() {
                                    @Override
                                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                        Logger.i("多选视频的回调");
                                    }
                                }).open();

                break;
            case 2:

                //直接打开
                RxGalleryFinalApi
                        .openMultiSelectVD(getActivity(), new RxBusResultDisposable<ImageMultipleResultEvent>() {
                            @Override
                            protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                                Logger.i("多选视频的回调");
                            }
                        });

                break;
        }
    }
}
