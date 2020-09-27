package com.smthweird.idillikajava;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    List<CatalogItem> catalogList;
    private static Context context;
    static SharedPreferences sharedPreferences;

    public CatalogAdapter(Context context, List<CatalogItem> catalogList) {
        this.catalogList = catalogList;
        this.context = context;
    }

    @NonNull
    @Override
    public CatalogAdapter.CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        return new CatalogViewHolder(view);
    }

    private static void savePrefs(int id, boolean favorite_value) {
        sharedPreferences = context.getSharedPreferences("Favorite_Checked", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(String.valueOf(id), favorite_value).apply();
    }

    private static boolean loadPrefs(int id) {
        sharedPreferences = context.getSharedPreferences("Favorite_Checked", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(String.valueOf(id), false);
    }

    private static void deletePrefs(int id) {
        sharedPreferences = context.getSharedPreferences("Favorite_Checked", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(String.valueOf(id)).apply();
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        holder.bind(catalogList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return catalogList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return catalogList.size();
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {
        private ImageView catalogImage;
        private TextView catalogBrand;
        private TextView catalogTitle;
        private TextView catalogPrice;
        private CheckBox catalogFavorite;
        private CatalogItem mCatalogItem;
        private boolean isChecked;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            catalogImage = itemView.findViewById(R.id.ImageView_catalogImage);
            catalogBrand = itemView.findViewById(R.id.TextView_brand);
            catalogTitle = itemView.findViewById(R.id.TextView_description);
            catalogPrice = itemView.findViewById(R.id.TextView_price);
            catalogFavorite = itemView.findViewById(R.id.Checkbox_favorite);

        }

        public void bind(CatalogItem catalogItem) {
            catalogBrand.setText(catalogItem.getBrand());
            catalogTitle.setText(catalogItem.getTitle());
            catalogPrice.setText(catalogItem.getPrice() + "₽");

            mCatalogItem = catalogItem;
            isChecked = false;

            catalogFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean favorite_value) {
                    if (catalogFavorite.isChecked()) {
                        favoriteChecked();
                    } else if (catalogFavorite.isEnabled()){
                        favoriteUnChecked();
                    }
                }
            });

            isChecked = loadPrefs(mCatalogItem.getId());
            catalogFavorite.setChecked(isChecked);

            Glide.with(catalogImage)
                    .load(catalogItem.getImageLink())
                    .centerCrop()
                    .into(catalogImage);


        }

        public void favoriteChecked() {
            isChecked = true;
            catalogFavorite.setBackgroundResource(R.drawable.ic_favorite_button_selected);
            savePrefs(mCatalogItem.getId(), isChecked);
        }

        public void favoriteUnChecked() {
            isChecked = false;
            catalogFavorite.setBackgroundResource(R.drawable.ic_favorite_button);
            deletePrefs(mCatalogItem.getId());
        }


    }

}