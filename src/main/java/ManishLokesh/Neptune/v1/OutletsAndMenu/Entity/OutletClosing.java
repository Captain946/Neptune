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
}
