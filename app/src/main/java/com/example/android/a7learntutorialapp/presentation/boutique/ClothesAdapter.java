package com.example.android.a7learntutorialapp.presentation.boutique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.a7learntutorialapp.R;
import com.example.android.a7learntutorialapp.data.model.Cloth;

import java.util.List;


public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder> {

    private Context context;
    private List<Cloth> clothes;

    public ClothesAdapter(Context context, List<Cloth> clothes){

        this.context = context;
        this.clothes = clothes;
    }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.layout_clothing_item,parent,false);

        return new ClothesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClothesViewHolder holder, int position) {
        Cloth cloth=clothes.get(position);

        holder.clothImage.setImageDrawable(cloth.getImage());
        holder.clothTitle.setText(cloth.getTitle());
        holder.clothViewCount.setText(String.valueOf(cloth.getViewCount()));
    }

    @Override
    public int getItemCount() {
        return clothes.size();
    }


    public class ClothesViewHolder extends RecyclerView.ViewHolder{
        private ImageView clothImage;
        private TextView clothTitle;
        private TextView clothViewCount;
        public ClothesViewHolder(View itemView) {
            super(itemView);
            clothImage=(ImageView)itemView.findViewById(R.id.clothing_image);
            clothTitle=(TextView) itemView.findViewById(R.id.clothing_title);
            clothViewCount=(TextView)itemView.findViewById(R.id.clothing_viewcount_text);
        }
    }
}
