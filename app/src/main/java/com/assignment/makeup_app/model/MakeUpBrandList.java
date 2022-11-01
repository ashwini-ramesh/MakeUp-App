package com.assignment.makeup_app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MakeUpBrandList implements Serializable {

    private int id;
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
    private String[] tagList;
    private String createdAt;
    private String updatedAt;
    private String productAPIURL;
    private String apiFeaturedImage;
    private List<ProductColor> productColors;

    public MakeUpBrandList(int id, String brand, String name, String price, String priceSign, String currency, String imageLink, String productLink, String websiteLink, String description, String rating, String category, String productType, String[] tagList, String createdAt, String updatedAt, String productAPIURL, String apiFeaturedImage, List<ProductColor> productColors) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.priceSign = priceSign;
        this.currency = currency;
        this.imageLink = imageLink;
        this.productLink = productLink;
        this.websiteLink = websiteLink;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.productType = productType;
        this.tagList = tagList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productAPIURL = productAPIURL;
        this.apiFeaturedImage = apiFeaturedImage;
        this.productColors = productColors;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String[] getTagList() {
        return tagList;
    }

    public void setTagList(String[] tagList) {
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

    public String getProductAPIURL() {
        return productAPIURL;
    }

    public void setProductAPIURL(String productAPIURL) {
        this.productAPIURL = productAPIURL;
    }

    public String getApiFeaturedImage() {
        return apiFeaturedImage;
    }

    public void setApiFeaturedImage(String apiFeaturedImage) {
        this.apiFeaturedImage = apiFeaturedImage;
    }

    public List<ProductColor> getProductColors() {
        return productColors;
    }

    public void setProductColors(List<ProductColor> productColors) {
        this.productColors = productColors;
    }
}
