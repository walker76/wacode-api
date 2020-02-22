package api.controller;

import api.domain.order.*;
import api.domain.user.User;
import api.repository.OrderRepository;
import api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<Order> getAll(){
        return this.orderRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable("id") long id){
        Optional<Order> orderOpt = this.orderRepository.findById(id);
        return orderOpt.isPresent() ? orderOpt.get() : null;
    }

    @GetMapping("/findByStatus/{orderStatus}")
    public List<Order> getByJobType(@PathVariable("orderStatus") OrderStatus orderStatus){
        return this.orderRepository.findAllByStatusIs(orderStatus);
    }

    @PutMapping("/updateJobStatus/")
    public void getByJobType(@RequestBody OrderUpdateRequest orderUpdateRequest){
        Optional<Order> optionalJob = this.orderRepository.findById(orderUpdateRequest.getId());
        if(optionalJob.isPresent()){
            Order order = optionalJob.get();
            order.setStatus(orderUpdateRequest.getStatus());
            if(orderUpdateRequest.getStatus().equals(OrderStatus.ACCEPTED)){
                Optional<User> optionalUser = userRepository.findById(orderUpdateRequest.getWorkerId());
                if(optionalUser.isPresent()){
                    User user = optionalUser.get();
                    user.getJobsWorking().add(orderUpdateRequest.getId());
                    userRepository.save(user);
                }
            }
            order.setWorkerId(orderUpdateRequest.getWorkerId());
            this.orderRepository.save(order);
        }
    }

    @PutMapping("/insert")
    public void insert(@RequestBody OrderRequest orderRequest){
        Order newOrder = new Order(orderRequest);
        User user = userRepository.findById(newOrder.getPosterId()).get();
        user.getJobsPosted().add(newOrder.getId());
        userRepository.save(user);
        this.orderRepository.insert(newOrder);
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
