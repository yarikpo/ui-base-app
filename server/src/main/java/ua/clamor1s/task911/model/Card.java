package ua.clamor1s.task911.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Card")
@Table(name = "card")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "operations")
@Builder
public class Card {

    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    private String name;

    private String surname;

    private String code;

    private int cvv;

    @Column(name = "creation_date")
    private Date creationDate;

    @OneToMany(mappedBy = "card")
    private Set<Operation> operations = new HashSet<>();

}
