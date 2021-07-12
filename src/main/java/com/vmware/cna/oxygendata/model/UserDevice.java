package com.vmware.cna.oxygendata.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "userdevice")
public class UserDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userDeviceId;
    
    private Long deviceId;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private String deviceAddress;
    private String deviceLocation;
    private String deviceLatLong;
    private String remark;

    public Long getUserDeviceId() {
        return this.userDeviceId;
    }

    public void setUserDeviceId(Long userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public Long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceLocation() {
        return this.deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceLatLong() {
        return this.deviceLatLong;
    }

    public void setDeviceLatLong(String deviceLatLong) {
        this.deviceLatLong = deviceLatLong;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
