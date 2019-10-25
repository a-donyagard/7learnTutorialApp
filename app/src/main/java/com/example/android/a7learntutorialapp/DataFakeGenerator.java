package com.example.android.a7learntutorialapp;

import android.content.Context;
import androidx.core.content.res.ResourcesCompat;

import com.example.android.a7learntutorialapp.animations.AnimationsMainActivity;
import com.example.android.a7learntutorialapp.datamodel.AppFeature;
import com.example.android.a7learntutorialapp.datamodel.Cloth;
import com.example.android.a7learntutorialapp.datamodel.Post;
import com.example.android.a7learntutorialapp.view.activity.BoutiqueActivity;
import com.example.android.a7learntutorialapp.view.activity.MainActivity;
import com.example.android.a7learntutorialapp.view.activity.MusicPlayerActivity;
import com.example.android.a7learntutorialapp.view.activity.PostsActivity;
import com.example.android.a7learntutorialapp.view.activity.ProfileActivity;
import com.example.android.a7learntutorialapp.view.activity.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 7/21/2016.
 */
public class DataFakeGenerator {
    private static final int POSTS_PER_PAGE = 6;

    public static List<Post> getPosts(int page) {

        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Post post = new Post();
            post.setId(i);
            post.setTitle(" پست شماره ی "+String.valueOf((page*POSTS_PER_PAGE)+i));
            post.setContent("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.");
            post.setDate("2 ساعت پیش");
            switch (i) {
                case 1:
                    post.setPostImage(R.drawable.pic1);
                    break;
                case 2:
                    post.setPostImage(R.drawable.pic2);
                    break;
                case 3:
                    post.setPostImage(R.drawable.pic3);
                    break;
                case 4:
                    post.setPostImage(R.drawable.pic4);
                    break;
                case 5:
                    post.setPostImage(R.drawable.pic5);
                    break;
                case 6:
                    post.setPostImage(R.drawable.pic6);
                    break;
            }
            posts.add(post);
        }
        return posts;
    }


    public static List<Cloth> getClothes(Context context) {
        List<Cloth> clothes = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Cloth cloth = new Cloth();
            cloth.setId(i);
            cloth.setTitle("لورم ایپسوم متن ساختگی");
            cloth.setViewCount(700);
            switch (i) {
                case 1:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic1_clothes, null));
                    break;
                case 2:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic2__clothes, null));
                    break;
                case 3:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic3_clothes, null));
                    break;
                case 4:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic4_clothes, null));
                    break;
                case 5:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic5_clothes, null));
                    break;
                case 6:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic6_clothes, null));
                    break;
                case 7:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic7_clothes, null));
                    break;
                case 8:
                    cloth.setImage(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic8_clothes, null));
                    break;

            }
            clothes.add(cloth);
        }
        return clothes;
    }


    public static List<AppFeature> getAppFeatures(Context context) {
        List<AppFeature> appFeatures = new ArrayList<>();

        AppFeature appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_POSTS_ACTIVITY);
        appFeature.setTitle(context.getString(R.string.app_feature_latest_posts));
        appFeature.setFeatureImage(R.drawable.posts);
        appFeature.setDestinationActivity(PostsActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_USER_PROFILE);
        appFeature.setTitle(context.getString(R.string.app_feature_user_profile));
        appFeature.setFeatureImage(R.drawable.user_profile);
        appFeature.setDestinationActivity(ProfileActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_FASHION);
        appFeature.setTitle(context.getString(R.string.app_feature_fashion));
        appFeature.setFeatureImage(R.drawable.fashion);
        appFeature.setDestinationActivity(BoutiqueActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_MUSIC);
        appFeature.setTitle(context.getString(R.string.app_feature_music_player));
        appFeature.setFeatureImage(R.drawable.music_player);
        appFeature.setDestinationActivity(MusicPlayerActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_VIDEO);
        appFeature.setTitle(context.getString(R.string.app_feature_video_player));
        appFeature.setFeatureImage(R.drawable.video_player);
        appFeature.setDestinationActivity(VideoPlayerActivity.class);
        appFeatures.add(appFeature);

        appFeature = new AppFeature();
        appFeature.setId(AppFeature.ID_LOGIN);
        appFeature.setTitle(context.getString(R.string.app_feature_login));
        appFeature.setFeatureImage(R.drawable.login);
        appFeature.setDestinationActivity(MainActivity.class);
        appFeatures.add(appFeature);

        appFeature=new AppFeature();
        appFeature.setId(AppFeature.ID_ANIMATIONS);
        appFeature.setTitle(context.getString(R.string.app_feature_animations_in_android));
        appFeature.setFeatureImage(R.drawable.animations_in_android);
        appFeature.setDestinationActivity(AnimationsMainActivity.class);
        appFeatures.add(appFeature);


        return appFeatures;
    }
}
