package com.example.android.a7learntutorialapp;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.example.android.a7learntutorialapp.datamodel.Cloth;
import com.example.android.a7learntutorialapp.datamodel.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed shahini on 7/21/2016.
 */
public class DataFakeGenerator {
    public static List<Post> getData(Context context) {
        /*
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Post post = new Post();
            post.setId(i);
            post.setTitle("لورم ایپسوم متن ساختگی");
            post.setContent("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است.");
            post.setDate("2 ساعت پیش");
            switch (i) {
                case 1:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic1, null));
                    break;
                case 2:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic2, null));
                    break;
                case 3:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic3, null));
                    break;
                case 4:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic4, null));
                    break;
                case 5:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic5, null));
                    break;
                case 6:
                    post.setPostImageUrl(ResourcesCompat.getDrawable(context.getResources(), R.drawable.pic6, null));
                    break;
            }
            posts.add(post);
        }
        */
        return null;
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
}
