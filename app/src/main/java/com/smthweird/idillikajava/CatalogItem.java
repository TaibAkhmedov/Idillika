package com.smthweird.idillikajava;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatalogItem {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageLink")
    @Expose
    private String imageLink;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("available")
    @Expose
    private Boolean available;
    @SerializedName("favorite")
    @Expose
    private Boolean favorite;
    @SerializedName("brand")
    @Expose
    private String brand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
