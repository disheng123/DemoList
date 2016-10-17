package demo.lx.com.demolist.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;


/**
 * Created by luffy on 15/4/2.
 */
public class ImageLoadUtil {

    /**
     * 通用获取图片方法
     *
     * @param imageView item图片imageview
     * @param url       图片的url
     */
    public static void getCommonImage(final ImageView imageView, int id, String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(id)
                .showImageForEmptyUri(id)
                .showImageOnFail(id)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url, imageView, options);
    }
    /**
     * 获取圆形图片方法
     *
     * @param imageView item图片imageview
     * @param url       图片的url
     */
    public static void getCircleImage(final ImageView imageView, int id, String url) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(id)
                .showImageForEmptyUri(id)
                .showImageOnFail(id)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
//                .displayer(new CircleBitmapDisplayer())
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url, imageView, options);
    }


    /**
     * 获取圆角图片
     *
     * @param imageView 头像imageview
     * @param url       图片的url
     */
    public static void getHalfCircleImage(ImageView imageView, int id, String url,int nRound) {
        if (imageView == null) return;
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(id)
                .showImageForEmptyUri(id)
                .showImageOnFail(id)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .resetViewBeforeLoading(true)
                .displayer(new RoundedBitmapDisplayer(nRound))
                .build();
        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * 通用获取图片方法,带滚动进度条
     *
     * @param imageView item图片imageview
     * @param url       图片的url
     */
    public static void getProgressBarCommonImage(final ImageView imageView, int id, String url, final ProgressBar loadLocalPb) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(id)
                .showImageForEmptyUri(id)
                .showImageOnFail(id)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .considerExifParams(false)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                loadLocalPb.setVisibility(View.VISIBLE);
                loadLocalPb.setProgress(0);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                loadLocalPb.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }

    /**
     * 加载本地图片
     * @param uri
     * @param imageView
     */
    public static void getDisplayFromSDCard(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    private static Bitmap sc(Bitmap bitmap,float sc) {
        Matrix matrix = new Matrix();
        matrix.postScale(sc,sc); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }


    /**
     * Res图片转Bitmap
     * @param res
     * @param nResId
     * @param nScale
     * @param bISBlur
     * @return
     */
    public static Bitmap GetResPic(Resources res, int nResId, int nScale, boolean bISBlur) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 3;
        options.outHeight = 120;
        options.inJustDecodeBounds = true;
        //此时返回bm为空
        options.inJustDecodeBounds = false;
        options.inSampleSize = nScale;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        Bitmap bm = BitmapFactory.decodeResource(res, nResId, options);
//        if (bISBlur) {
//            bm = Util.BoxBlurFilter(bm);
//        }
        return bm;
    }

    /**
     * Bitmap转圆角
     * @param bitmap
     * @param fRound
     * @return
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap,float fRound) {	// 圆形图片（自定义圆角度的）
        Bitmap pBitmapRet = null;
        if (bitmap != null && fRound > 0) {
            int width = bitmap.getWidth();	// 圆形图片宽高
            int height = bitmap.getHeight();// 圆形图片宽高
            int r = 0;	// 正方形的边长
            if (width > height) {	// 取最短边做边长
                r = height;
            } else {
                r = width;
            }
            pBitmapRet = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);	// 构建一个bitmap
            Canvas canvas = new Canvas(pBitmapRet);	// new一个Canvas，在backgroundBmp上画图
            Paint paint = new Paint();
            paint.setAntiAlias(true);	// 设置边缘光滑，去掉锯齿
            RectF rect = new RectF(0, 0, width, height);	// 宽高相等，即正方形
            // 通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
            // 且都等于r/2时，画出来的圆角矩形就是圆形
            canvas.drawRoundRect(rect, (r*fRound / 2), (r*fRound / 2), paint);
            // 设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            // canvas将bitmap画在backgroundBmp上
            canvas.drawBitmap(bitmap, 0f, 0f, paint);
            // 销毁时调用
            canvas = null;
            // 返回已经绘画好的backgroundBmp
        } else {
            pBitmapRet = bitmap;
        }
        return pBitmapRet;
    }
}
