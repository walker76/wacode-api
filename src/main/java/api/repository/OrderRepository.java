package api.repository;

import api.domain.order.Order;
import api.domain.order.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findAllByStatusIs(OrderStatus status);
    List<Order> findAllByPosterIdIs(Long deviceId);
    List<Order> findAllByWorkerIdIs(Long workerId);
}
