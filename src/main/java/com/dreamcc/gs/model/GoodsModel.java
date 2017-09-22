package com.dreamcc.gs.model;

import java.math.BigDecimal;

public class GoodsModel extends BaseModel {
    private String id;

    private String goodsName;

    private String goodsUnit;

    private String goodsType;

    private String goodsColor;

    private Integer goodsStore;

    private Integer goodsLimit;

    private BigDecimal goodsCommission;

    private String goodsProducer;

    private String goodsRemark;

    private BigDecimal goodsSelPrice;

    private BigDecimal goodsBuyPrice;

    public String getId() {
        return id;
    }

    public void setGoodsId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor == null ? null : goodsColor.trim();
    }

    public Integer getGoodsStore() {
        return goodsStore;
    }

    public void setGoodsStore(Integer goodsStore) {
        this.goodsStore = goodsStore;
    }

    public Integer getGoodsLimit() {
        return goodsLimit;
    }

    public void setGoodsLimit(Integer goodsLimit) {
        this.goodsLimit = goodsLimit;
    }

    public BigDecimal getGoodsCommission() {
        return goodsCommission;
    }

    public void setGoodsCommission(BigDecimal goodsCommission) {
        this.goodsCommission = goodsCommission;
    }

    public String getGoodsProducer() {
        return goodsProducer;
    }

    public void setGoodsProducer(String goodsProducer) {
        this.goodsProducer = goodsProducer == null ? null : goodsProducer.trim();
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark == null ? null : goodsRemark.trim();
    }

    public BigDecimal getGoodsSelPrice() {
        return goodsSelPrice;
    }

    public void setGoodsSelPrice(BigDecimal goodsSelPrice) {
        this.goodsSelPrice = goodsSelPrice;
    }

    public BigDecimal getGoodsBuyPrice() {
        return goodsBuyPrice;
    }

    public void setGoodsBuyPrice(BigDecimal goodsBuyPrice) {
        this.goodsBuyPrice = goodsBuyPrice;
    }
}
