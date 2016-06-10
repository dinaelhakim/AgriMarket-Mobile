/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reguest_parameters_pojos;

/**
 *
 * @author Israa
 */
public class SearchLimitedOffersByProductNameParam {
    private String productName;
    private int pageNo;
    private int sortType;
    public static final int DATE_SORT=1;
    public static final int PRICE_SORT=2;
    public static final int QUANTITY_SORT=3;


    public SearchLimitedOffersByProductNameParam() {
    }

    public SearchLimitedOffersByProductNameParam(String productName, int pageNo, int sortType) {
        this.productName = productName;
        this.pageNo = pageNo;
        this.sortType = sortType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    
    
}
