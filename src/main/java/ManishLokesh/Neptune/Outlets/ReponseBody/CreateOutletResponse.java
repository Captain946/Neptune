package ManishLokesh.Neptune.Outlets.ReponseBody;

public class CreateOutletResponse {

    private Long id;
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


    public CreateOutletResponse(Long id,String outletName, Long minOrderValue, Integer orderTiming, String openingTime,
                        String closingTime, Integer deliveryCost, String address, String city, String state,
                        Boolean prepaid, String companyName, String panCard, String gstNo, String fssaiNo,
                        String fssaiValidUpto, String outletClosedFrom, String outletClosedTo, Boolean active,
                        String logoImage, String emailId, String mobileNo, String stationCode){
        this.id = id;
        this.outletName = outletName;
        this.minOrderValue = minOrderValue;
        this.orderTiming = orderTiming;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.deliveryCost = deliveryCost;
        this.address = address;
        this.city = city;
        this.state = state;
        this.prepaid = prepaid;
        this.companyName = companyName;
        this.panCard = panCard;
        this.gstNo = gstNo;
        this.fssaiNo = fssaiNo;
        this.fssaiValidUpto = fssaiValidUpto;
        this.outletClosedFrom = outletClosedFrom;
        this.outletClosedTo = outletClosedTo;
        this.active = active;
        this.logoImage = logoImage;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.stationCode = stationCode;
    }

}
