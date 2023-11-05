package com.example.apptourbooking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptourbooking.Activitis.UserActivities.BrandDetailActivity;
import com.example.apptourbooking.Domain.Brand;
import com.example.apptourbooking.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {
    TextView brandName;
    TextView description;
    ImageView logo;
    ConstraintLayout item;

    public ViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        brandName = (TextView) itemView.findViewById(R.id.bvh_txt_brandName);
        description = (TextView) itemView.findViewById(R.id.bvh_txt_description);
        logo = (ImageView) itemView.findViewById(R.id.bvh_img_brandLogo);

    }
}

public class BrandAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int layout;
    private List<Brand> brandList;

    public BrandAdapter(Context context, int layout, List<Brand> brandList) {
        this.context = context;
        this.layout = layout;
        this.brandList = brandList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Brand brand = brandList.get(position);
        holder.brandName.setText(brand.getBrandName());
        holder.description.setText(brand.getDescription());
        holder.logo.setImageResource(brand.getLogo());
        holder.item.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BrandDetailActivity.class);
            intent.putExtra("brand", brand);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }
}
