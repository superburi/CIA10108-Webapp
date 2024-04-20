package com.howard.rentalorderdetails.vo;

import com.howard.rentalmytrack.vo.RentalMyTrackVo_ORM;
import com.howard.rentalorder.vo.RentalOrderVo;
import com.howard.rentalorder.vo.RentalOrderVo_ORM;
import com.howard.yu.rental.model.Rental;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "rentalorderdetails")
@IdClass(RentalOrderDetails_ORM.CompositeDetail.class)
public class RentalOrderDetails_ORM implements Serializable{

    /*
     * rOrdNo -> 租借品訂單編號
     * rNo -> 租借品編號
     * rPrice -> 單價
     * rDesPrice -> 押金(單件)
     */

//    @Id
//    @Column(name = "rOrdNo")
//    private Integer rOrdNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rOrdNo", referencedColumnName = "rOrdNo")
    private RentalOrderVo_ORM rentalOrderVo;
//    @Id
//    @Column(name = "rNo")
//    private Integer rNo;
    @Id
    @ManyToOne
    @JoinColumn(name = "rNo", referencedColumnName = "rNo")
    private Rental rental;
    @Column(name = "rPrice")
    private BigDecimal rPrice;
    @Column(name = "rDesPrice")
    private BigDecimal rDesPrice;


/*----------------------內部類別的 getter、setter--------------------------*/

    public CompositeDetail getCompositeDetail() {
        return new CompositeDetail(rOrdNo, rNo);
    }

    public void setCompositeDetail(CompositeDetail compositeDetail) {
        this.rOrdNo = compositeDetail.getrOrdNo();
        this.rNo = compositeDetail.getrNo();
    }


/*----------------------原本成員變數的 getter、setter--------------------------*/

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


/*--------------聯合映射用的 getter、setter( rentalorderdetails 是附表)-------------*/
    public RentalOrderVo_ORM getRentalOrderVo() {
        return rentalOrderVo;
    }

    public void setRentalOrderVo(RentalOrderVo_ORM rentalOrderVo) {
        this.rentalOrderVo = rentalOrderVo;
    }



/*----------------------複合主鍵用的內部類別--------------------------*/

    static class CompositeDetail implements Serializable {

        private Integer rOrdNo;
        private Integer rNo;

        public CompositeDetail() {
            super();
        }

        public CompositeDetail(Integer rOrdNo, Integer rNo) {
            this.rOrdNo = rOrdNo;
            this.rNo = rNo;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeDetail that)) return false;
            return Objects.equals(getrOrdNo(), that.getrOrdNo()) && Objects.equals(getrNo(), that.getrNo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getrOrdNo(), getrNo());
        }

    } // 內部類別結束


}


