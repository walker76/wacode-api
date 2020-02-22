package api.controller;

import api.domain.order.*;
import api.repository.OrderRepository;
import api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/all")
    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    @GetMapping("/findByOrderId/{id}")
    public Order findById(@PathVariable("id") long id){
        Optional<Order> orderOpt = this.orderRepository.findById(id);
        return orderOpt.isPresent() ? orderOpt.get() : null;
    }

    @GetMapping("/findByDeviceId/{id}")
    public List<Order> findByDeviceId(@PathVariable("id") long id){
        return this.orderRepository.findAllByPosterIdIs(id);
    }

    @GetMapping("/findByWorkerId/{id}")
    public List<Order> findByWorkerId(@PathVariable("id") long id){
        return this.orderRepository.findAllByWorkerIdIs(id);
    }

    @GetMapping("/findByStatus/{orderStatus}")
    public List<Order> getByJobType(@PathVariable("orderStatus") OrderStatus orderStatus){
        return this.orderRepository.findAllByStatusIs(orderStatus);
    }

    @PutMapping("/updateOrderStatus/")
    public void getByJobType(@RequestBody OrderUpdateRequest orderUpdateRequest){
        Optional<Order> optionalOrder = this.orderRepository.findById(orderUpdateRequest.getId());
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setStatus(orderUpdateRequest.getStatus());
            order.setWorkerId(orderUpdateRequest.getWorkerId());
            this.orderRepository.save(order);
        }
    }

    @PutMapping("/makeOrder")
    public void insert(@RequestBody OrderRequest orderRequest){
        this.orderRepository.insert(new Order(orderRequest));
    }

    @PostMapping("/update")
    public void update(@RequestBody Order order){
        this.orderRepository.save(order);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        this.orderRepository.deleteById(id);
    }

}
