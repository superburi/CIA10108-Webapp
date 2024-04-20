package com.howard.rentalorderdetails.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


public class RentalOrderDetails {

    /*
     * rOrdNo -> 租借品訂單編號
     * rNo -> 租借品編號
     * rPrice -> 單價
     * rDesPrice -> 押金(單件)
     */


    private Integer rOrdNo;
    private Integer rNo;
    private BigDecimal rPrice;
    private BigDecimal rDesPrice;


/*----------------------getter、setter--------------------------*/

    public Integer getrOrdNo() {
        return rOrdNo;
    }

    public void setrOrdNo(Integer rOrdNo) {
        this.rOrdNo = rOrdNo;
    }

    public Integer getrNo() {
        return rNo;
    }

    public void setrNo(Integer rNo) {
        this.rNo = rNo;
    }

    public BigDecimal getrPrice() {
        return rPrice;
    }

    public void setrPrice(BigDecimal rPrice) {
        this.rPrice = rPrice;
    }

    public BigDecimal getrDesPrice() {
        return rDesPrice;
    }

    public void setrDesPrice(BigDecimal rDesPrice) {
        this.rDesPrice = rDesPrice;
    }

}


