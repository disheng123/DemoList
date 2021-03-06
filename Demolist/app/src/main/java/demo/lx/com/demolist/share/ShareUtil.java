package demo.lx.com.demolist.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;


import org.json.JSONObject;

import demo.lx.com.demolist.R;
import demo.lx.com.demolist.Utils;

/**
 * 作者：李翔 on 2016/10/12 17:15
 * qq：415473855
 * 分享
 */
public class ShareUtil {

    class BaseUiListener implements IUiListener {
        protected void doComplete(JSONObject response) {

        }

        @Override
        public void onComplete(Object response) {
            // mBaseMessageText.setText("onComplete:");
            // mMessageText.setText(response.toString());
//            doComplete(response);
            Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(UiError uiError) {
//            showResult("onError:", "code:" + e.errorCode + ", msg:"
//                    + e.errorMessage + ", detail:" + e.errorDetail);
            Toast.makeText(context,uiError.toString(),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
//            showResult("onCancel", "");
            Toast.makeText(context,"quxiao",Toast.LENGTH_SHORT).show();
        }
    }

    private int type;   //1：微信聊天，2：微信朋友圈
    private Context context;
    private final String WXAPPID = "wx9a61015dadc233b5";
    private final String WEIBOAPPID = "";
    private int res;
    public ShareUtil(Context context){
        this.context = context;
    }

    public void ToastDialog(String title, String content, final String imageUrl, String shareUrl, final int res) {
        final Dialog dialog = new Dialog(context, R.style.myDialogTheme4);
        View view = LayoutInflater.from(context).inflate(R.layout.shar_content, null);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        WindowManager windowManager = ((Activity)context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);

        RelativeLayout sharRel = (RelativeLayout) view.findViewById(R.id.sharRel);
        LinearLayout sharWeixin = (LinearLayout) view.findViewById(R.id.sharWeixin);
        LinearLayout sharPengYou = (LinearLayout) view.findViewById(R.id.sharPengYou);
        LinearLayout sharXinLang = (LinearLayout) view.findViewById(R.id.sharXinLang);
        LinearLayout sharQQ = (LinearLayout) view.findViewById(R.id.sharQQ);
        RelativeLayout sharRelQuXiao = (RelativeLayout) view.findViewById(R.id.sharRelQuXiao);
        dialog.show();
        //取消按钮
        sharRelQuXiao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //界面外取消
        sharRel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //微信
        sharWeixin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if(imageUrl != null && !imageUrl.isEmpty()){
                    String imageUrls = "http://img.parteam.cn//default/activity/badminton/badminton4.jpg";
                    ImageLoader.getInstance().loadImage(imageUrls, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
                            type = 1;
                            WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                            Bitmap sharBitmap = Utils.compressImage(bmp);
                            weiXinshare.initWx("标题","内容",sharBitmap,"www.baidu.com");
                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                            type = 1;
                            WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                            Bitmap sharBitmap = Utils.compressImage(bitmap);
                            weiXinshare.initWx("标题","内容",sharBitmap,"www.baidu.com");
                        }
                        @Override
                        public void onLoadingCancelled(String s, View view) {

                        }
                    });
                }else{
                    type = 1;
                    WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                    Bitmap sharBitmap = Utils.compressImage(bmp);
                    weiXinshare.initWx("标题","内容",sharBitmap,"www.baidu.com");
                }
            }
        });
        //朋友圈
        sharPengYou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if(imageUrl != null && !imageUrl.isEmpty()){
                    String imageUrls = "http://img.parteam.cn//default/activity/badminton/badminton4.jpg";
                    ImageLoader.getInstance().loadImage(imageUrls, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {

                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
                            type = 2;
                            WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                            Bitmap sharBitmap = Utils.compressImage(bmp);
                            weiXinshare.initWx("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                            type = 2;
                            WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                            Bitmap sharBitmap = Utils.compressImage(bitmap);
                            weiXinshare.initWx("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                        }
                        @Override
                        public void onLoadingCancelled(String s, View view) {

                        }
                    });
                }else{
                    type = 2;
                    WeiXinshare weiXinshare = new WeiXinshare(context,type,WXAPPID);
                    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                    Bitmap sharBitmap = Utils.compressImage(bmp);
                    weiXinshare.initWx("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                }
            }
        });
        //微博
        sharXinLang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                if(imageUrl != null && !imageUrl.isEmpty()){
                    String imageUrls = "http://img.parteam.cn//default/activity/badminton/badminton4.jpg";
                    ImageLoader.getInstance().loadImage(imageUrls, new ImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String s, View view) {
                        }

                        @Override
                        public void onLoadingFailed(String s, View view, FailReason failReason) {
                            WeiBoShar weiBoShar = new WeiBoShar(context,WEIBOAPPID);
                            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                            Bitmap sharBitmap = Utils.compressImage(bmp);
                            weiBoShar.initWeiBo("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                        }

                        @Override
                        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                            WeiBoShar weiBoShar = new WeiBoShar(context,WEIBOAPPID);
                            Bitmap sharBitmap = Utils.compressImage(bitmap);
                            weiBoShar.initWeiBo("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                        }
                        @Override
                        public void onLoadingCancelled(String s, View view) {

                        }
                    });
                }else{
                    WeiBoShar weiBoShar = new WeiBoShar(context,WXAPPID);
                    Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),res);
                    Bitmap sharBitmap = Utils.compressImage(bmp);
                    weiBoShar.initWeiBo("标题pengyouquan","内容",sharBitmap,"www.baidu.com");
                }
            }
        });
        //QQ
        sharQQ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Tencent mTencent = Tencent.createInstance("", context.getApplicationContext());
                final Bundle params = new Bundle();
                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
                params.putString(QQShare.SHARE_TO_QQ_TITLE, "要分享的标题");
                params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "要分享的摘要");
                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
                params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
                params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "Demolist");
                mTencent.shareToQQ((Activity) context, params, new BaseUiListener());
            }
        });
    }
}
