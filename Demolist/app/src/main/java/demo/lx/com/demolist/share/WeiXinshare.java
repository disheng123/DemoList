package demo.lx.com.demolist.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
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

    public WeiXinshare(Context var0){
        context = var0;
        api = WXAPIFactory.createWXAPI(context,"wx9a61015dadc233b5");
        initWx();
    }

    public void initWx() {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
            WXWebpageObject music = new WXWebpageObject();
            music.webpageUrl= "http://www.baidu.com";

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = music;
            msg.title = "我的测试分享";
            msg.description = "我的分享内容";

            Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.moren1);
            msg.thumbData = Utils.bmpToByteArray(thumb, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            // 发送WXSceneSession聊天没见,WXSceneTimeline朋友圈界面
            req.scene = SendMessageToWX.Req.WXSceneTimeline;

            api.sendReq(req);
        }
    }
}
