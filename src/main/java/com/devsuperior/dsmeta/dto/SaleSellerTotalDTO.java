package com.devsuperior.dsmeta.dto;

public class SaleSellerTotalDTO {

    private String sellerName;
    private Double total;

    public SaleSellerTotalDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
