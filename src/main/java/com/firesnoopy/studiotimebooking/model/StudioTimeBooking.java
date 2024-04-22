package com.firesnoopy.studiotimebooking.model;


import com.firesnoopy.studioinfo.model.StudioInfoVO;
import com.firesnoopy.studioorder.model.StudioOrderVO;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "StudioTimeBooking")
public class StudioTimeBooking {
    @Id
    @Column(name = "sTimeNo")
    private Integer sTimeNo;
    @ManyToOne
    @JoinColumn(name = "sOrdNo", referencedColumnName = "sOrdNo")
    private StudioOrderVO studioOrder;
    @ManyToOne
    @JoinColumn(name = "sNo", referencedColumnName = "sNo")
    private StudioInfoVO studioInfo;
    @Column(name = "closeDate")
    private Date closeDate;
    @Column(name = "closeTimeMornig")
    private Boolean closeTimeMorning;
    @Column(name = "closeTimeAfternoon")
    private Boolean closeTimeAfternoon;
    @Column(name = "closeTimeNight")
    private Boolean closeTimeNight;

    public Integer getsTimeNo() {
        return sTimeNo;
    }

    public void setsTimeNo(Integer sTimeNo) {
        this.sTimeNo = sTimeNo;
    }

    public StudioOrderVO getStudioOrder() {
        return studioOrder;
    }

    public void setStudioOrder(StudioOrderVO studioOrder) {
        this.studioOrder = studioOrder;
    }

    public StudioInfoVO getStudioInfo() {
        return studioInfo;
    }

    public void setStudioInfo(StudioInfoVO studioInfo) {
        this.studioInfo = studioInfo;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Boolean getCloseTimeMorning() {
        return closeTimeMorning;
    }

    public void setCloseTimeMorning(Boolean closeTimeMorning) {
        this.closeTimeMorning = closeTimeMorning;
    }

    public Boolean getCloseTimeAfternoon() {
        return closeTimeAfternoon;
    }

    public void setCloseTimeAfternoon(Boolean closeTimeAfternoon) {
        this.closeTimeAfternoon = closeTimeAfternoon;
    }

    public Boolean getCloseTimeNight() {
        return closeTimeNight;
    }

    public void setCloseTimeNight(Boolean closeTimeNight) {
        this.closeTimeNight = closeTimeNight;
    }
}
