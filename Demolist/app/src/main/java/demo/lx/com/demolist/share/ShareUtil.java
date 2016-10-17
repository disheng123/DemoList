package demo.lx.com.demolist.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.lx.com.demolist.R;

/**
 * 作者：李翔 on 2016/10/12 17:15
 * qq：415473855
 * 分享
 */
public class ShareUtil {
    private int type;   //1：微信聊天，2：微信朋友圈
    private Context context;
    public ShareUtil(Context context){
        this.context = context;
    }

    public void ToastDialog() {
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
                type = 1;
                WeiXinshare weiXinshare = new WeiXinshare(context,type);
                weiXinshare.setAppid("wx9a61015dadc233b5");
                weiXinshare.initWx("标题","内容","http://www.chinagirlol.cc/data/attachment/forum/201412/03/233758hw7o7h08kkozkcwi.jpg","www.parteam.cn");
            }
        });
        //朋友圈
        sharPengYou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                type = 2;
                WeiXinshare weiXinshare = new WeiXinshare(context,type);
                weiXinshare.setAppid("wx9a61015dadc233b5");
                weiXinshare.initWx("标题pengyouquan","内容","http://www.chinagirlol.cc/data/attachment/forum/201412/03/233758hw7o7h08kkozkcwi.jpg","www.parteam.cn");
            }
        });
        //微博
        sharXinLang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        //QQ
        sharQQ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
