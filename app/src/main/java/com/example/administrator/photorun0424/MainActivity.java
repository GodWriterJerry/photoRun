package com.example.administrator.photorun0424;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private  Banner banner;
    private FloatingActionButton fab,fab1,fab2,fab3;
    private Boolean ISAUTO=true;
    String[] images= new String[] {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493051437299&di=6dc9f111cc0391d95175fa5c9eb2c2df&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fb151f8198618367a4a4430642d738bd4b21ce5be.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493646269&di=e257fbe4b074b4afdc644becea3e9b47&imgtype=jpg&er=1&src=http%3A%2F%2Fi3.hoopchina.com.cn%2Fhupuapp%2Fbbs%2F189%2F27105189%2Fthread_27105189_20161002234708_s_1606808_o_1799637902.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493051611035&di=8d84fe7c95d57348b6361804f3bae065&imgtype=0&src=http%3A%2F%2Fimg.warting.com%2Fallimg%2F2016%2F0807%2F1-160PGR111.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=92398a7be1cd7b89e96c3a8b3f254291/00b8fc039245d68892013815a3c27d1ed31b24c1.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493051629986&di=fa2d9b7769bc94df9271d4cd3b84b955&imgtype=0&src=http%3A%2F%2Fbbs.static.coloros.com%2Fdata%2Fattachment%2Fforum%2F201604%2F18%2F201151oov1bw2v1bbh7207.jpg",
            "http://imgsrc.baidu.com/forum/w=580/sign=ff31b7e3314e251fe2f7e4f09787c9c2/ff32af13b07eca80db3d2c3e902397dda04483d6.jpg",
            "http://imgsrc.baidu.com/forum/w%3D580/sign=25521a587a899e51788e3a1c72a6d990/3f469245d688d43f973c58577c1ed21b0ff43b47.jpg"};

    //设置图片标题:自动对应
    String[] titles=new String[]{"科比布莱恩特","身高1.98米","5个总冠军","2FMVP，1MVP","生涯最高81分","NBA历史总得分第三","2016从NBA退役"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab1= (FloatingActionButton) findViewById(R.id.fab1);
        fab2= (FloatingActionButton) findViewById(R.id.fab2);
        fab3= (FloatingActionButton) findViewById(R.id.fab3);

        banner = (Banner) findViewById(R.id.banner);

        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        banner.setIndicatorGravity(BannerConfig.CENTER);

        //Banner.LEFT   指示
        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitles(Arrays.asList(titles));

        //设置是否自动轮播（不设置则默认自动）

        banner.setBannerAnimation(Transformer.Accordion);

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(1000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        banner.setImageLoader(new GlideImageLoader());

        banner.setImages(Arrays.asList(images));

        banner.isAutoPlay(ISAUTO);

        banner.start();

        //设置点击事件，下标是从1开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getApplicationContext(), "这是第" + (position+1)+"张图片", Toast.LENGTH_LONG).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ISAUTO){
                    ISAUTO=false;
                    onStop();
                    Toast.makeText(MainActivity.this,"暂停自动轮播",Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.mipmap.play);
                }else{
                    ISAUTO=true;
                    onStart();
                    Toast.makeText(MainActivity.this,"开始自动轮播",Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.mipmap.pause);
                }

            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banner.setBannerAnimation(Transformer.ZoomOut);
                Toast.makeText(MainActivity.this,"开始放大效果",Toast.LENGTH_SHORT).show();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banner.setBannerAnimation(Transformer.FlipHorizontal);
                Toast.makeText(MainActivity.this,"开始水平翻转效果",Toast.LENGTH_SHORT).show();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banner.setBannerAnimation(Transformer.ScaleInOut);
                Toast.makeText(MainActivity.this,"开始内外延伸效果",Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}
