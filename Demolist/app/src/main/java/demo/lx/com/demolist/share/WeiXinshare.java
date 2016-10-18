package demo.lx.com.demolist.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.net.URL;

import demo.lx.com.demolist.R;
import demo.lx.com.demolist.Utils;

/**
 * 作者：李翔 on 2016/10/12 17:14
 * qq：415473855
 */
public class WeiXinshare {
    private static final int THUMB_SIZE = 120;
    private IWXAPI api;
    private Context context;
    private int type;
    private String appid;

    public WeiXinshare(Context context,int type,String appid){
        this.context = context;
        this.type = type;
        this.appid = appid;
    }

    public void initWx(final String title, final String content,Bitmap imageUrl, final String shareUrl) {
        api = WXAPIFactory.createWXAPI(context,appid);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            WXWebpageObject web = new WXWebpageObject();
            web.webpageUrl= shareUrl;

            WXMediaMessage msg = new WXMediaMessage();
            msg.title = title;
            msg.description = content;
            msg.mediaObject = web;

            Bitmap thumbBmp = Bitmap.createScaledBitmap(imageUrl, THUMB_SIZE, THUMB_SIZE, true);
            msg.thumbData = Utils.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;

            // WXSceneSession聊天界面,
            if(type == 1){
                req.scene = SendMessageToWX.Req.WXSceneSession;
            }//WXSceneTimeline朋友圈界面
            else{
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            }
            api.sendReq(req);

//            if(imageUrl != null || imageUrl.isEmpty()){
//                Bitmap bitmap =Bitmap.createScaledBitmap(Utils.GetLocalOrNetBitmap(imageUrl),THUMB_SIZE,THUMB_SIZE,true);//压缩Bitmap
//                msg.thumbData = Utils.bmpToByteArray(bitmap, true);
//            }else{
//                Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.moren1);
//                msg.thumbData = Utils.bmpToByteArray(thumb, true);
//            }
        }
    }
}
