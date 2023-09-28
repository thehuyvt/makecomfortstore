package t3h.vn.makecomfortstore.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class ContactEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @Column(name = "name", length = 50)
    private String contactName;

    @Column(name = "email", length = 150)
    private String contactEmail;

    @Column(name = "phoneNumber", length = 20)
    private String contactPhone;

    @Column(name = "title", length = 200)
    private String contactTitle;

    @Column(name = "note", length = 500)
    private String contactNote;
}
