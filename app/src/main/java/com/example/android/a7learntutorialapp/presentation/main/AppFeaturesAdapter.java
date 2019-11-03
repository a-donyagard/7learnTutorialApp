package com.example.android.a7learntutorialapp.presentation.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.presentation.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;


public class AppFeaturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<AppFeature> appFeatures = new ArrayList<>();
    private Typeface yekanFont;

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_DEFAULT_ITEM = 1;

    public AppFeaturesAdapter(Context context) {
        this.context = context;
//        yekanFont = Typeface.createFromAsset(context.getAssets(), "fonts/yekan.ttf");
    }

    public void setAppFeatures(List<AppFeature> appFeatures) {
        this.appFeatures = appFeatures;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                View view = LayoutInflater.from(context).inflate(R.layout.layout_app_feature_banner, parent, false);
                return new AppFeatureBanner(view, yekanFont);
            case VIEW_TYPE_DEFAULT_ITEM:
                return new AppFeatureViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.layout_app_feature, parent, false), yekanFont);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AppFeatureViewHolder) {
            AppFeatureViewHolder appFeatureViewHolder = (AppFeatureViewHolder) holder;
            appFeatureViewHolder.bindAppFeature(appFeatures.get(position - 1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_DEFAULT_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return appFeatures.size() + 1;
    }


    public static class AppFeatureViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView chapterTitle;

        public AppFeatureViewHolder(View itemView, Typeface yekanFont) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.feature_image_view);
            chapterTitle = (TextView) itemView.findViewById(R.id.feature_title);
//            chapterTitle.setTypeface(yekanFont);
        }

        public void bindAppFeature(final AppFeature appFeature) {
            Picasso.with(itemView.getContext()).load(appFeature.getFeatureImage()).into(imageView);
            chapterTitle.setText(appFeature.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appFeature.getDestinationActivity() == LoginActivity.class) {
                        Activity activity = (Activity) itemView.getContext();
                        activity.startActivityForResult(new Intent(itemView.getContext(),
                                LoginActivity.class), MainActivity.REQUEST_CODE_LOGIN);
                    } else {
                        itemView.getContext().startActivity(new Intent(itemView.getContext(),
                                appFeature.getDestinationActivity()));
                    }
                }
            });
        }
    }

    public static class AppFeatureBanner extends RecyclerView.ViewHolder {
        //        private TextView appFeaturesLabel;
        private BannerSlider bannerSlider;

        public AppFeatureBanner(View itemView, Typeface typeface) {
            super(itemView);
//            appFeaturesLabel = (TextView) itemView.findViewById(R.id.label_app_features_list);
//            appFeaturesLabel.setTypeface(typeface);
            bannerSlider = itemView.findViewById(R.id.banner_slider);
            bannerSlider.addBanner(new RemoteBanner("https://s.7learn.com/img/slide-android.png"));
            bannerSlider.addBanner(new DrawableBanner(R.drawable.bottom_navigation_banner));
            bannerSlider.addBanner(new DrawableBanner(R.drawable.constaint_layout_sample_banner));
            bannerSlider.addBanner(new DrawableBanner(R.drawable.infinite_scroll_provider_banner));
            bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void onClick(int position) {
                    switch (position) {
                        case 0:
                            showWebpage("http://www.7learn.com/class/9101");
                            break;
                        case 1:
                            showWebpage("https://github.com/saeedsh92/Infinite-Scroll-Provider");
                            break;
                        case 2:
                            showWebpage("https://github.com/saeedsh92/bottomnavigation");
                            break;
                        case 3:
                            showWebpage("https://github.com/saeedsh92/ConstraintLayoutSample");
                            break;
                    }
                }
            });
        }

        private void showWebpage(String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            itemView.getContext().startActivity(Intent.createChooser(intent, "انتخاب مرورگر..."));
        }
    }
}
