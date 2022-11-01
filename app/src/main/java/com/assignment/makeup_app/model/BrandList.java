package com.assignment.makeup_app.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BrandList implements Serializable {
    private Integer id;
    private String brand;
    private String name;
    private String price;
    private String priceSign;
    private String currency;
    private String imageLink;
    private String productLink;
    private String websiteLink;
    private String description;
    private String rating;
    private String category;
    private String productType;
    private List<String> tagList = new ArrayList<String>();
    private String createdAt;
    private String updatedAt;
    private String productApiUrl;
    private String apiFeaturedImage;
    private List<ColorList> productColors = new ArrayList<ColorList>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceSign() {
        return priceSign;
    }

    public void setPriceSign(String priceSign) {
        this.priceSign = priceSign;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getProductLink() {
        return productLink;
    }

    public void setProductLink(String productLink) {
        this.productLink = productLink;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductApiUrl() {
        return productApiUrl;
    }

    public void setProductApiUrl(String productApiUrl) {
        this.productApiUrl = productApiUrl;
    }

    public String getApiFeaturedImage() {
        return apiFeaturedImage;
    }

    public void setApiFeaturedImage(String apiFeaturedImage) {
        this.apiFeaturedImage = apiFeaturedImage;
    }

    public List<ColorList> getProductColors() {
        return productColors;
    }

    public void setProductColors(List<ColorList> productColors) {
        this.productColors = productColors;
    }


    // Decodes array of business json results into business model objects
    public static ArrayList<BrandList> fromJson(JSONArray jsonArray) {
        JSONObject jsonObject;
        ArrayList<BrandList> brandLists = new ArrayList<BrandList>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            BrandList brandList = BrandList.fromJsonn(jsonObject);
            if (brandList != null) {
                brandLists.add(brandList);
            }
        }

        return brandLists;
    }

    // Decodes business json into business model object
    public static BrandList fromJsonn(JSONObject jsonObject) {
        BrandList b = new BrandList();
        // Deserialize json into object fields
        try {
            b.id = jsonObject.getInt("id");
            b.brand = jsonObject.optString("brand");
            b.name = jsonObject.optString("name");
            b.price = jsonObject.optString("price");
            b.priceSign = jsonObject.optString("price_sign");
            b.imageLink = jsonObject.optString("image_link");
            b.productLink = jsonObject.optString("product_link");
            b.websiteLink = jsonObject.optString("website_link");
            b.description = jsonObject.optString("description");
            b.rating = jsonObject.optString("rating");
            b.category = jsonObject.optString("category");
            b.productType = jsonObject.optString("product_type");
            b.tagList = null;
            b.createdAt = jsonObject.optString("created_at");
            b.updatedAt = jsonObject.optString("updated_at");
            b.productApiUrl = jsonObject.optString("product_api_url");
            b.apiFeaturedImage = jsonObject.optString("api_featured_image");
            b.currency = jsonObject.optString("currency");
            JSONArray arrayColor = jsonObject.getJSONArray("product_colors");
            b.productColors = ColorList.fromJsonu(arrayColor);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }


}