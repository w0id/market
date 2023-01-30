package ru.gb.market.order.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "orders",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "order_item_id")
    )
    private Collection<OrderItem> orderItems;

    public OrderDetail(final Customer customer, final DeliveryType deliveryType, final PickUpPoint pickUpPoint, final Collection<OrderItem> orderItems) {
        this.customer = customer;
        this.deliveryType = deliveryType;
        this.pickUpPoint = pickUpPoint;
        this.orderItems = orderItems;
    }

    public OrderDetail(final Customer customer, final DeliveryType deliveryType, final Collection<OrderItem> orderItems) {
        this.customer = customer;
        this.deliveryType = deliveryType;
        this.orderItems = orderItems;
    }
}
