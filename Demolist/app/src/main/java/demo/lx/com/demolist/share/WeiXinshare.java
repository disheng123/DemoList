package demo.lx.com.demolist.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


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

    public WeiXinshare(Context context,int type,String appid){
        this.context = context;
        this.type = type;
        api = WXAPIFactory.createWXAPI(context,appid);
    }

    public void initWx(String title,String content,Bitmap imageUrl,String shareUrl) {
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
        }
    }
}
