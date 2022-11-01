package com.assignment.makeup_app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.makeup_app.R;
import com.assignment.makeup_app.helper.Utils;
import com.assignment.makeup_app.model.ColorList;
import com.assignment.makeup_app.model.ProductColor;


import java.util.ArrayList;
import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {

    private List<ColorList> productColors = new ArrayList<>();;
    Context context;


    public ColorAdapter(List<ColorList> productColors, Context context) {
        this.productColors = productColors;
        this.context = context;
    }

    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ColorAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ColorAdapter.ViewHolder holder, int position) {
        String colorName = productColors.get(position).getColourName();
        String hexValue = productColors.get(position).getHexValue();
        if (productColors != null && Utils.isContentVerified(colorName)) {
            holder.tvColorName.setVisibility(View.VISIBLE);
            holder.tvColorName.setText(colorName);
        } else {
            holder.tvColorName.setText("");
            holder.tvColorName.setVisibility(View.GONE);
        }
        if (productColors != null && Utils.isContentVerified(hexValue)) {
            holder.view.setVisibility(View.VISIBLE);
            //holder.view.setBackgroundColor(Color.parseColor(hexValue));
            holder.view.setBackgroundColor(Color.parseColor(hexValue));
        } else {
            holder.view.setVisibility(View.GONE);
            holder.view.setBackgroundColor(Color.parseColor("#ff888888"));
        }
    }

    @Override
    public int getItemCount() {
        return productColors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvColorName;
        //ImageView txtImgColor;
        View view;
        ProductColor productColors;
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            tvColorName = (TextView) itemView.findViewById(R.id.tvColorName);
            view = (View) itemView.findViewById(R.id.viewColor);

        }
    }
}
