package entity;

import jakarta.persistence.*;
//@NamedQuery(name="findallIncome",query="SELECT t FROM TotalincomeEntity t limit 10")

@Entity
@Table(name = "totalincome", schema = "censusdb", catalog = "")
public class TotalincomeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private short id;
    @Basic
    @Column(name = "description", nullable = false, length = 40)
    private String description;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String displayInfo() {
        return "ID: " + this.getId() + " Name: " + this.getDescription();
    }
}
