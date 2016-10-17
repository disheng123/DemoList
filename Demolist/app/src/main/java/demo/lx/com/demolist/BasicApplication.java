package demo.lx.com.demolist;

import android.app.Application;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

public class BasicApplication extends Application {


	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader();

	}

	public void initImageLoader() {
//		File pFile = StorageUtils.getOwnCacheDirectory(this, ".Demolist/pic_dir/");
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.threadPoolSize(15);
//		config.diskCache(new UnlimitedDiskCache(pFile));	// 自定义缓存路径
		config.diskCacheFileCount(5000);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheSize(100 * 1024 * 1024); // 100 MiB
		config.memoryCacheSize(50 * 1014 * 1024);/*手机内存设置*/
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.memoryCacheExtraOptions(480, 800);// maxwidth, max height，即保存的每个缓存文件的最大长宽
        config.memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024)); // 你可以通过自己设置内存缓存大小
        ImageLoader.getInstance().init(config.build());
	}


}