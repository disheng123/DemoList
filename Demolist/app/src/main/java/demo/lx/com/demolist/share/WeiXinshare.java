package demo.lx.com.demolist.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
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
//        api.registerApp("wx9a61015dadc233b5");
        initWx();
    }

    public void initWx() {
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
        } else {
//            WXWebpageObject webpage = new WXWebpageObject();
//            webpage.webpageUrl = "http://baidu.com";
//
//            WXMediaMessage msg = new WXMediaMessage();
//            msg.description = "我的分享内容";
//            msg.title = "测试分享";
//
//            Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
//            msg.thumbData = Utils.bmpToByteArray(thumb, true);
//

            WXTextObject textObj = new WXTextObject();
            textObj.text = "asdasdasd";

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;

            msg.description = "asdasdasdsadasdwq321321321321";
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            req.scene = SendMessageToWX.Req.WXSceneSession;

            api.sendReq(req);
        }
    }
}
