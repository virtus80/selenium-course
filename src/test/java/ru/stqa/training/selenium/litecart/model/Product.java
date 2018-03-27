package ru.stqa.training.selenium.litecart.model;

public class Product {

    private String status = "Enabled";
    private String name;
    private String code;
    private String category = "Root";
    private String productGroup = "Unisex";
    private String quantity;
    private String quantityUnit = "pcs";
    private String deliveryStatus = "3-5 days";
    private String soldOutStatus = "Temporary sold out";
    private String photo;
    private String dateValidFrom;
    private String dateValidTo;
    private String manufacturer = "ACME Corp.";
    private String keywords;
    private String shortDescription;
    private String description;

    public Product(String name, String code, String quantity, String photo, String dateValidFrom, String dateValidTo, String keywords, String shortDescription, String description) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.photo = photo;
        this.dateValidFrom = dateValidFrom;
        this.dateValidTo = dateValidTo;
        this.keywords = keywords;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public Product withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product withCode(String code) {
        this.code = code;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public Product withProductGroup(String productGroup) {
        this.productGroup = productGroup;
        return this;
    }

    public String getQuantity() {
        return quantity;
    }

    public Product withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public Product withQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
        return this;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public Product withDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public String getSoldOutStatus() {
        return soldOutStatus;
    }

    public Product withSoldOutStatus(String soldOutStatus) {
        this.soldOutStatus = soldOutStatus;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public Product withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getDateValidFrom() {
        return dateValidFrom;
    }

    public Product withDateValidFrom(String dateValidFrom) {
        this.dateValidFrom = dateValidFrom;
        return this;
    }

    public String getDateValidTo() {
        return dateValidTo;
    }

    public Product withDateValidTo(String dateValidTo) {
        this.dateValidTo = dateValidTo;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Product withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public Product withKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Product withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }
}
