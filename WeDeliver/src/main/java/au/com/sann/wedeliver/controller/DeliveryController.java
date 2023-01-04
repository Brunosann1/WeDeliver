package au.com.sann.wedeliver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.sann.wedeliver.model.Delivery;
import au.com.sann.wedeliver.repository.DelivererRepository;
import au.com.sann.wedeliver.repository.DeliveryRepository;

@RestController
@RequestMapping("/wedeliver")
public class DeliveryController {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private DelivererRepository delivererRepository;
	
	@GetMapping("/all")
	public Iterable<Delivery> allDeliveries(){
		return deliveryRepository.findAll();
	}
	
	@PostMapping("/newDelivery")
	public ResponseEntity<Delivery> newDelivery(@RequestBody Delivery delivery){
		
		delivery.setDeliverer(delivererRepository.save(delivery.getDeliverer()));
		return new ResponseEntity<>(deliveryRepository.save(delivery), HttpStatus.CREATED);
	}
}
