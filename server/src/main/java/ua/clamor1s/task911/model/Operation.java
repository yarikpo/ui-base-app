package ua.clamor1s.task911.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Operation")
@Table(name = "operation")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"card"})
@Builder
public class Operation {

    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationId;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private int amount;

    @Column(name = "operation_datetime")
    private Timestamp operationDatetime;

}
