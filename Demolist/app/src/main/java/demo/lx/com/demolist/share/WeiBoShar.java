package demo.lx.com.demolist.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

import demo.lx.com.demolist.R;

/**
 * Created by DELL on 2016/10/19.
 */
public class WeiBoShar {

    private IWeiboShareAPI mWeiboShareAPI = null;
    private Context context;

    public WeiBoShar(Context context,String WeiBoAppId) {
        this.context = context;
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(context,WeiBoAppId);
        mWeiboShareAPI.registerApp();
    }

    public void initWeiBo(String title,String content,Bitmap imageUrl,String shareUrl) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.title = title;
        mediaObject.description = content;
        mediaObject.actionUrl = shareUrl;
        mediaObject.setThumbImage(imageUrl);

        WeiboMessage weiboMessage = new WeiboMessage();
        weiboMessage.mediaObject = mediaObject;

        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.message = weiboMessage;
        mWeiboShareAPI.sendRequest((Activity) context, request);
    }

}
