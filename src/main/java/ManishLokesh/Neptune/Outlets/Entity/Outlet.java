package ManishLokesh.Neptune.Outlets.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "outlets")
public class Outlet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", initialValue = 1234)
    private Long id;
    private String createdAt;
    private String updatedAt;
    private String outletName;
    private Long minOrderValue;
    private Integer orderTiming;
    private String openingTime;
    private String closingTime;
    private Integer deliveryCost;
    private String address;
    private String city;
    private String state;
    private Boolean prepaid;
    private String companyName;
    private String panCard;
    private String gstNo;
    private String fssaiNo;
    private String fssaiValidUpto;
    private String outletClosedFrom;
    private String outletClosedTo;
    private Boolean active;
    private String logoImage;
    private String emailId;
    private String mobileNo;
    private String stationCode;
    private Integer ratingCount;
    private Integer ratingValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Long getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(Long minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public Integer getOrderTiming() {
        return orderTiming;
    }

    public void setOrderTiming(Integer orderTiming) {
        this.orderTiming = orderTiming;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getFssaiNo() {
        return fssaiNo;
    }

    public void setFssaiNo(String fssaiNo) {
        this.fssaiNo = fssaiNo;
    }

    public String getFssaiValidUpto() {
        return fssaiValidUpto;
    }

    public void setFssaiValidUpto(String fssaiValidUpto) {
        this.fssaiValidUpto = fssaiValidUpto;
    }

    public String getOutletClosedFrom() {
        return outletClosedFrom;
    }

    public void setOutletClosedFrom(String outletClosedFrom) {
        this.outletClosedFrom = outletClosedFrom;
    }

    public String getOutletClosedTo() {
        return outletClosedTo;
    }

    public void setOutletClosedTo(String outletClosedTo) {
        this.outletClosedTo = outletClosedTo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }
}
