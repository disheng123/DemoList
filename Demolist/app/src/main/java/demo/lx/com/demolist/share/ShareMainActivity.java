package demo.lx.com.demolist.share;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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
                Snackbar.make(v,"点击了分享按钮",Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}
