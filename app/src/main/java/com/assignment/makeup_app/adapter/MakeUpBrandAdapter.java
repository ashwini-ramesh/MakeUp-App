package com.assignment.makeup_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.makeup_app.R;
import com.assignment.makeup_app.helper.Utils;
import com.assignment.makeup_app.model.BrandList;
import com.assignment.makeup_app.model.MakeUpBrandList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MakeUpBrandAdapter extends RecyclerView.Adapter<MakeUpBrandAdapter.ViewHolder> {

    private List<BrandList> brandList;
    Context context;
    private MakeUpBrandAdapter.ItemListener mListener;


    public MakeUpBrandAdapter(List<BrandList> brandList, MakeUpBrandAdapter.ItemListener mListener, Context context) {
        this.brandList = brandList;
        this.mListener = mListener;
        this.context = context;
    }

    @Override
    public MakeUpBrandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MakeUpBrandAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MakeUpBrandAdapter.ViewHolder holder, int position) {
        String name = brandList.get(position).getName();
        String imgPath = brandList.get(position).getImageLink();
        String brand = brandList.get(position).getBrand();
        if (Utils.isContentVerified(imgPath)) {
            Picasso.with(context)
                    .load(imgPath)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(holder.txtImgOperator);
        } else {
            holder.txtImgOperator.setImageResource(R.drawable.ic_placeholder);

        }
        holder.setData(brandList.get(position));

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtDesc;

        String name, imgPath, brand,desc;
        ImageView txtImgOperator;
        BrandList brandListItem;
        int position;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //txtDesc = (TextView) itemView.findViewById(R.id.txtName);
            txtName = (TextView) itemView.findViewById(R.id.tvName);
            txtImgOperator = (ImageView) itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(brandListItem);
            }
        }

        public void setData(BrandList brandListItem) {
            this.brandListItem = brandListItem;
            this.position = position;
            txtName.setText(brandListItem.getBrand());
            //tvWebSite.setText("From: " + brandListItem.getName());
        }
    }

    public interface ItemListener {
        void onItemClick(BrandList brandListItem);
    }
    public void setFilter(List<BrandList> arrayList) {
        brandList = new ArrayList<>();
        brandList.addAll(arrayList);
        notifyDataSetChanged();
    }


}
