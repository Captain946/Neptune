package ManishLokesh.Neptune.v1.OutletsAndMenu.Entity;


import javax.persistence.*;

@Entity
@Table(name = "outletClosing")
public class OutletClosing {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long outletId;
    private String closedFrom;
    private String closedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutletId() {
        return outletId;
    }

    public void setOutletId(Long outletId) {
        this.outletId = outletId;
    }

    public String getClosedFrom() {
        return closedFrom;
    }

    public void setClosedFrom(String closedFrom) {
        this.closedFrom = closedFrom;
    }

    public String getClosedTo() {
        return closedTo;
    }

    public void setClosedTo(String closedTo) {
        this.closedTo = closedTo;
    }
}
