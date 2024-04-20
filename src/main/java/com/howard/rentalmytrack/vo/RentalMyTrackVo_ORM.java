package com.howard.rentalmytrack.vo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

@Entity
@Table(name = "rentalmytrack")
@IdClass(RentalMyTrackVo_ORM.CompositeTrack.class)
public class RentalMyTrackVo_ORM implements Serializable {

	/*
	 * rNo -> 租借品編號
	 * memNo -> 會員編號
	 * rTrackTime -> 加入追蹤時間
	 * expRentalDate -> 期望租借時間
	 */
    @Id
    @Column(name = "rNo", updatable = false)
    private Integer rNo;
    @Id
    @Column(name = "memNo", updatable = false)
    private Integer memNo;
	@Column(name = "rTrackTime")
    private Timestamp rTrackTime;
	@Column(name = "expRentalDate")
    private Date expRentalDate;

/*----------------------內部類別的 getter、setter--------------------------*/

    public CompositeTrack getCompositeKey() {
        return new CompositeTrack(rNo, memNo);
    }

    public void setCompositeKey(CompositeTrack key) {
        this.rNo = key.getrNo();
        this.memNo = key.getMemNo();
    }

/*----------------------原本成員變數的 getter、setter--------------------------*/
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



/*----------------------複合主鍵用的內部類別--------------------------*/

    static class CompositeTrack implements Serializable {

        private Integer rNo;
        private Integer memNo;

        public CompositeTrack() {
            super();
        }

        public CompositeTrack(Integer rNo, Integer memNo) {
            super();
            this.rNo = rNo;
            this.memNo = memNo;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeTrack that)) return false;
            return Objects.equals(getrNo(), that.getrNo()) && Objects.equals(getMemNo(), that.getMemNo());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getrNo(), getMemNo());
        }

    } // 內部類別結束

}
