package com.example.project1;

public class Sauna {
    private String make;
    private String product_code;
    private String wood_type;
    private Integer capacity;
    private String heating;
    private Double price;
    private String image;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getProductCode() {
        return product_code;
    }

    public void setProductCode(String product_code) {
        this.product_code = product_code;
    }

    public String getWoodType() {
        return wood_type;
    }

    public void setWoodType(String wood_type) {
        this.wood_type = wood_type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getShipping() {

        if (capacity < 4)
            return price * .05;
        else
            return price * .08;

    }
}
