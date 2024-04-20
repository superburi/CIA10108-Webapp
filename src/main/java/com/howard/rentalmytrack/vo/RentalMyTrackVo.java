package com.howard.rentalmytrack.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;


public class RentalMyTrackVo {

    /*
     * rNo -> 租借品編號
     * memNo -> 會員編號
     * rTrackTime -> 加入追蹤時間
     * expRentalDate -> 期望租借時間
     */

    private Integer rNo;
    private Integer memNo;
    private Timestamp rTrackTime;
    private Date expRentalDate;

    /*----------------------constructor--------------------------*/
    public RentalMyTrackVo() {
        super();
    }

    /*----------------------getter、setter--------------------------*/

    public Integer getrNo() {
        return rNo;
    }

    public void setrNo(Integer rNo) {
        this.rNo = rNo;
    }

    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public Timestamp getrTrackTime() {
        return rTrackTime;
    }

    public void setrTrackTime(Timestamp rTrackTime) {
        this.rTrackTime = rTrackTime;
    }

    public Date getExpRentalDate() {
        return expRentalDate;
    }

    public void setExpRentalDate(Date expRentalDate) {
        this.expRentalDate = expRentalDate;
    }
}
