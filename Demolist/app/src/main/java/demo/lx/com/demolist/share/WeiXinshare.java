package demo.lx.com.demolist.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import demo.lx.com.demolist.R;
import demo.lx.com.demolist.Utils;

/**
 * 作者：李翔 on 2016/10/12 17:14
 * qq：415473855
 */
public class WeiXinshare {
    private static final int THUMB_SIZE = 150;
    private IWXAPI api;
    private Context context;
    private int type;

    public WeiXinshare(Context context,int type,String appid){
        this.context = context;
        this.type = type;
        api = WXAPIFactory.createWXAPI(context,appid);
    }

    public void initWx(String title, String content, String imageUrl, String shareUrl) {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            WXWebpageObject web = new WXWebpageObject();
            web.webpageUrl= shareUrl;
            final WXMediaMessage msg = new WXMediaMessage();
            msg.title = title;
            msg.description = content;
            msg.mediaObject = web;

            if(imageUrl != null || imageUrl.isEmpty()){
                ImageLoader.getInstance().loadImage("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476758526&di=752d161e71fc2fbb944d026c5561f940&src=http://pic.58pic.com/58pic/15/28/08/76X58PIC2UP_1024.jpg", new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bmp) {
                        if(bmp == null){
                            Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.moren1);
                            msg.thumbData = Utils.bmpToByteArray(thumb, true);
                        }else{
                            msg.thumbData = Utils.bmpToByteArray(bmp, true);
                        }
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
//            Bitmap bmp = ImageLoader.getInstance().loadImageSync("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1476758526&di=752d161e71fc2fbb944d026c5561f940&src=http://pic.58pic.com/58pic/15/28/08/76X58PIC2UP_1024.jpg");
//                if(bmp == null){
//                    Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.moren1);
//                    msg.thumbData = Utils.bmpToByteArray(thumb, true);
//                }else{
//                    msg.thumbData = Utils.bmpToByteArray(bmp, true);
//                }

            } else{
                Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.moren1);
                msg.thumbData = Utils.bmpToByteArray(thumb, true);
            }

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
        }
    }
}
