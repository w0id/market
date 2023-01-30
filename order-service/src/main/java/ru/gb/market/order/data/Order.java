package ru.gb.market.order.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "delivery_type_id")
    private DeliveryType deliveryType;

    @OneToOne
    @JoinColumn(name = "pickup_point_id")
    private PickUpPoint pickUpPoint;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Collection<OrderItem> orderItems;

    public Order(final Customer customer, final DeliveryType deliveryType, final PickUpPoint pickUpPoint, final List<OrderItem> orderItems) {
        this.customer = customer;
        this.deliveryType = deliveryType;
        this.pickUpPoint = pickUpPoint;
        this.orderItems = orderItems;
    }

    public Order(final Customer customer, final DeliveryType deliveryType, final List<OrderItem> orderItems) {
        this.customer = customer;
        this.deliveryType = deliveryType;
        this.orderItems = orderItems;
    }
}
