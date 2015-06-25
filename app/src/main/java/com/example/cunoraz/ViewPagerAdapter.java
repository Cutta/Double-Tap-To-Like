package com.example.cunoraz;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cunoraz.like.R;

//ViewPager
public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    int[] images;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageview;
        final ImageView heartAnim;
        final ImageView likeImg;
        final TextView likeCount;
        TextView whichOfThem;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);


        // Locate the ImageView in viewpager_item.xml
        imageview = (ImageView) itemView.findViewById(R.id.image);
        heartAnim = (ImageView) itemView.findViewById(R.id.heart_anim);
        likeImg = (ImageView) itemView.findViewById(R.id.item_comment_like_img);
        likeCount = (TextView) itemView.findViewById(R.id.item_comment_like_count);
        whichOfThem = (TextView) itemView.findViewById(R.id.which);

        whichOfThem.setText((position + 1) + "/" + 5);
        imageview.setImageResource(images[position]);

        final GestureDetector gd = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {

                Animation pulse_fade = AnimationUtils.loadAnimation(context, R.anim.pulse_fade_in);
                pulse_fade.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        heartAnim.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        heartAnim.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                heartAnim.startAnimation(pulse_fade);
                likeImg.setImageDrawable(context.getResources().getDrawable(R.drawable.like_active));
                likeCount.setText("3 Likes");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }
        });

        imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gd.onTouchEvent(event);
            }
        });


        // Add viewpager_item.xml to ViewPager
        (container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}
