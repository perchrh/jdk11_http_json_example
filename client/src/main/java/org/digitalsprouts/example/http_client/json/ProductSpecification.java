package org.digitalsprouts.example.http_client.json;

public class ProductSpecification {

    private String productId;

    private Double productPropertyOne;
    private Boolean productPropertyTwo;
    private Integer productPropertyThree;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getProductPropertyOne() {
        return productPropertyOne;
    }

    public void setProductPropertyOne(Double productPropertyOne) {
        this.productPropertyOne = productPropertyOne;
    }

    public Boolean getProductPropertyTwo() {
        return productPropertyTwo;
    }

    public void setProductPropertyTwo(Boolean productPropertyTwo) {
        this.productPropertyTwo = productPropertyTwo;
    }

    public Integer getProductPropertyThree() {
        return productPropertyThree;
    }

    public void setProductPropertyThree(Integer productPropertyThree) {
        this.productPropertyThree = productPropertyThree;
    }
}
