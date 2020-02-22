package api.controller;

import api.domain.device.Device;
import api.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/all")
    public List<Device> getAll(){
        return this.deviceRepository.findAll();
    }

    @GetMapping("/findById/{id:.+}")
    public Device findById(@PathVariable("id") long id){
        Optional<Device> deviceOpt = this.deviceRepository.findById(id);
        return deviceOpt.isPresent() ? deviceOpt.get() : null;
    }

    @PutMapping("/register")
    public Device register(){
        Device device = new Device();
        this.deviceRepository.insert(device);
        return device;
    }

    @PutMapping("/insert")
    public void insert(@RequestBody Device device){
        this.deviceRepository.insert(device);
    }

    @PostMapping("/update")
    public void update(@RequestBody Device device){
        this.deviceRepository.save(device);
    }

    @DeleteMapping("/delete/{id:.+}")
    public void delete(@PathVariable("id") Long id){
        this.deviceRepository.deleteById(id);
    }
}
