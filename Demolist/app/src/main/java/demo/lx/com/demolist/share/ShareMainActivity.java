package demo.lx.com.demolist.share;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import demo.lx.com.demolist.R;

public class ShareMainActivity extends AppCompatActivity {
    private Button btn_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initListener();
    }

    private void initView() {
        btn_share = (Button) findViewById(R.id.btn_share);
    }

    private void initListener() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastDialog();
            }
        });
    }

    private void ToastDialog() {
        final Dialog dialog = new Dialog(ShareMainActivity.this, R.style.myDialogTheme4);
        View view = LayoutInflater.from(ShareMainActivity.this).inflate(R.layout.shar_content, null);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        WindowManager windowManager = this.getWindowManager();
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

        sharRelQuXiao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sharRel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sharWeixin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                WeiXinshare weiXinshare = new WeiXinshare(ShareMainActivity.this);
                weiXinshare.initWx();
            }
        });

        sharPengYou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });

        sharXinLang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

            }
        });

        sharQQ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
