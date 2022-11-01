package com.assignment.makeup_app;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.DefaultRetryPolicy;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.assignment.makeup_app.adapter.MakeUpBrandAdapter;
import com.assignment.makeup_app.helper.MaterialSearchViewFragment;
import com.assignment.makeup_app.helper.ShimmerFrameLayout;
import com.assignment.makeup_app.model.BrandList;
import com.assignment.makeup_app.model.MakeUpBrandList;
import com.assignment.makeup_app.model.ProductColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements MakeUpBrandAdapter.ItemListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private MakeUpBrandAdapter.ItemListener mListener;
    private ArrayList<MakeUpBrandList> makeUpBrandLists = new ArrayList<>();
    private MakeUpBrandAdapter makeUpBrandAdapter;
    ProgressBar progressBar;
    private String JSON_URL = "https://makeup-api.herokuapp.com/api/v1/products.json";
    ImageView imgEmpty;
    private MaterialSearchViewFragment searchView;
    private final ArrayList<ProductColor> arrayListProductColor = new ArrayList<>();
    private List<ProductColor> productList = new ArrayList<>();
    //List<MakeUpBrandList> makeUpBrandLists;

    ArrayList<BrandList> brandLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setOnClick();
        getData();
        // buildRecyclerView();

        searchView.setOnQueryTextListener(new MaterialSearchViewFragment.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final List<BrandList> filteredModelList = filter(brandLists, query);
                recyclerView.setVisibility(View.VISIBLE);
                imgEmpty.setVisibility(View.GONE);
                makeUpBrandAdapter.setFilter(filteredModelList);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                final List<BrandList> filteredModelList = filter(brandLists, newText);
                if (filteredModelList.size() == 0) {
                    recyclerView.setVisibility(View.GONE);
                    imgEmpty.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    imgEmpty.setVisibility(View.GONE);
                    makeUpBrandAdapter.setFilter(filteredModelList);
                }
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchViewFragment.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
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
        //  toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        //mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        //mShimmerViewContainer.setVisibility(View.VISIBLE);
        // mShimmerViewContainer.startShimmerAnimation();
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        imgEmpty = findViewById(R.id.imgEmpty);
        imgEmpty.setVisibility(View.GONE);
        mListener = this;
        searchView = findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
    }


    @Override
    public void onItemClick(BrandList brandListItem) {
        //open new Activity here
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("brandListItem", brandListItem);
        startActivity(intent);

        if (searchView != null)
            if (searchView.isSearchOpen()) {
                searchView.closeSearch();
            }
    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                //recyclerView.setVisibility(View.VISIBLE);
                Log.d("exe", "response-" + response.toString());
                //try {
                    //JSONArray jsonArr = new JSONArray(response);
                    brandLists = BrandList.fromJson(response);

                /*} catch (JSONException e) {
                    e.printStackTrace();
                }*/

               /* for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        //arrayListProductColor.clear();
                        JSONObject responseObj = response.getJSONObject(i);
                        String id = responseObj.optString("id");
                        String brand = responseObj.optString("brand");
                        String name = responseObj.optString("name");
                        String price = responseObj.optString("price");
                        String priceSign = responseObj.optString("price_sign");
                        String imageLink = responseObj.optString("image_link");
                        String productLink = responseObj.optString("product_link");
                        String websiteLink = responseObj.optString("website_link");
                        String description = responseObj.optString("description");
                        String rating = responseObj.optString("rating");
                        String category = responseObj.optString("category");
                        String productType = responseObj.optString("product_type");
                        String createdAt = responseObj.optString("created_at");
                        String updatedAt = responseObj.optString("updated_at");
                        String productAPIURL = responseObj.optString("product_api_url");
                        String apiFeaturedImage = responseObj.optString("api_featured_image");
                        String currency = responseObj.optString("currency");
                        //ProductColor[] productColors = responseObj.getString("ProductColor");
                        JSONArray array = responseObj.getJSONArray("product_colors");
                        for (int j = 0; j < array.length(); j++) {
                            JSONObject object1 = array.getJSONObject(j);
                            //ProductColor productColors = new ProductColor(object1.optString("hex_value"), object1.optString("colour_name"));
                            //arrayListProductColor.add(productColors);
                        }
                        *//*makeUpBrandLists.add(new MakeUpBrandList(Integer.parseInt(id), brand,
                                name,
                                price,
                                "",
                                currency,
                                imageLink,
                                productLink,
                                websiteLink,
                                description,
                                rating,
                                category,
                                productType,
                                null,
                                createdAt,
                                updatedAt,
                                productAPIURL,
                                apiFeaturedImage,
                                productColors));*//*

                        makeUpBrandLists.add(new MakeUpBrandList(Integer.parseInt(id), brand,
                                name,
                                price,
                                "",
                                currency,
                                imageLink,
                                productLink,
                                websiteLink,
                                description,
                                rating,
                                category,
                                productType,
                                null,
                                createdAt,
                                updatedAt,
                                productAPIURL,
                                apiFeaturedImage,
                                arrayListProductColor));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/
                if (brandLists != null && brandLists.size() > 0) {
                    buildRecyclerView();
                } else {
                    invalidateOptionsMenu();
                    imgEmpty.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                imgEmpty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                Log.d("exe", "error-" + error.getMessage());
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {
        invalidateOptionsMenu();
        recyclerView.setVisibility(View.VISIBLE);
        imgEmpty.setVisibility(View.GONE);
        makeUpBrandAdapter = new MakeUpBrandAdapter(brandLists, mListener, MainActivity.this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        // LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(makeUpBrandAdapter);
        makeUpBrandAdapter.notifyDataSetChanged();
    }

    public List<BrandList> filter(List<BrandList> models, String query) {
        query = query.toLowerCase();

        final List<BrandList> filteredModelList = new ArrayList<>();
        for (BrandList model : models) {
            final String name;
            name = model.getBrand();
            if (name != null && name.toLowerCase().contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem menuitem = menu.findItem(R.id.action_search);
        if (brandLists != null && brandLists.size() > 0) {
            menuitem.setVisible(true);
        } else {
            menuitem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle ItemLabel selection
        switch (item.getItemId()) {
            case R.id.action_search:
                if (brandLists != null && brandLists.size() > 0) {
                    searchView.showSearch();
                } else {
                    Toast.makeText(MainActivity.this, "No Brands found...", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

}