package com.assignment.makeup_app;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.makeup_app.adapter.ColorAdapter;
import com.assignment.makeup_app.helper.Utils;
import com.assignment.makeup_app.model.BrandList;
import com.assignment.makeup_app.model.ColorList;
import com.assignment.makeup_app.model.MakeUpBrandList;
import com.assignment.makeup_app.model.ProductColor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private RecyclerView rvColor;
    RelativeLayout viewRecycler;
    LinearLayout llPrdct;
    ImageView ivLogo;
    TextView tvBrand, tvName, tvDesc, tvAmt;
    private ProgressBar progressBar;
    //private MakeUpBrandList makeUpBrandLists;
    ColorAdapter colorAdapter;

    // List<ProductColor> productColor;

    private List<ColorList> productColor = new ArrayList<>();
    private BrandList makeUpBrandLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getBundle();
        setView();
        setOnClick();
        setRecylerview();
    }

    private void getBundle() {
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                if (extras.containsKey("brandListItem")) {
                    makeUpBrandLists = (BrandList) extras.getSerializable("brandListItem");
                }
            }
        } catch (Exception e) {
            Log.e("exe", e.toString());
        }
    }

    private void setOnClick() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        toolbar.setTitle("Detailed Activity");
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        ivLogo = findViewById(R.id.ivLogo);
        tvBrand = findViewById(R.id.tvBrand);
        tvName = findViewById(R.id.tvName);
        tvDesc = findViewById(R.id.tvDesc);
        llPrdct = findViewById(R.id.llPrdct);
        tvAmt = findViewById(R.id.tvAmt);
        rvColor = findViewById(R.id.rvColor);
        viewRecycler = findViewById(R.id.viewRecycler);
        rvColor.setVisibility(View.GONE);
        viewRecycler.setVisibility(View.GONE);
        productColor.clear();

        if (makeUpBrandLists != null) {
            String brand = makeUpBrandLists.getBrand();
            String amt = makeUpBrandLists.getPrice();
            String name = makeUpBrandLists.getName();
            String desc = makeUpBrandLists.getDescription();
            String url = makeUpBrandLists.getImageLink();
            productColor = makeUpBrandLists.getProductColors();
            //arrayListProduct.add(productColor);

            tvBrand.setText(brand);
            tvName.setText(name);
            tvAmt.setText("₹ " + amt);

            if (Utils.isContentVerified(desc)) {
                llPrdct.setVisibility(View.VISIBLE);
                tvDesc.setText(desc);
            } else {
                llPrdct.setVisibility(View.GONE);
            }

            if (Utils.isContentVerified(url)) {
                Picasso.with(this)
                        .load(url)
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(ivLogo);
            } else {
                ivLogo.setImageResource(R.drawable.ic_placeholder);
            }
        }

    }

    private void setRecylerview() {
        if (productColor != null && productColor.size() > 0) {
            rvColor.setVisibility(View.VISIBLE);
            viewRecycler.setVisibility(View.VISIBLE);
            //LinearLayoutManager manager = new LinearLayoutManager(this);
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvColor.setHasFixedSize(true);
            rvColor.setLayoutManager(manager);
            colorAdapter = new ColorAdapter(productColor, DetailActivity.this);
            rvColor.setAdapter(colorAdapter);
            colorAdapter.notifyDataSetChanged();
        } else {
            rvColor.setVisibility(View.GONE);
            viewRecycler.setVisibility(View.GONE);
        }
    }

}
