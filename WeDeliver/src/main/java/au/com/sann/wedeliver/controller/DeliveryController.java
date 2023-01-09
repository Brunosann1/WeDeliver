package au.com.sann.wedeliver.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.sann.wedeliver.model.Delivery;
import au.com.sann.wedeliver.model.Rider;
import au.com.sann.wedeliver.repository.DeliveryRepository;
import au.com.sann.wedeliver.repository.RiderRepository;

@RestController
@RequestMapping("/wedeliver")
public class DeliveryController {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private RiderRepository riderRepository;
	
	private final Logger log = LoggerFactory.getLogger(DeliveryController.class);
	
	@GetMapping("/all")
	public Iterable<Delivery> allDeliveries(){
		return deliveryRepository.findAll();
	}
	
	@PostMapping("/new")
	public ResponseEntity<Delivery> newDelivery(@Valid @RequestBody Delivery delivery){
	
		try {
			Rider savedRider = riderRepository.save(delivery.getRider());
			delivery.setRider(savedRider);
			
			Delivery savedDelivery = deliveryRepository.save(delivery);
			
			return new ResponseEntity<>(savedDelivery, HttpStatus.CREATED);
		} catch (Exception e){
			log.error("Error saving delivery: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
/*
 * To manually deserialize from String to Object
 * 	ObjectMapper objectMapper = new ObjectMapper();
 * 	objectMapper.registerModule(new JavaTimeModule());
 * 	Delivery deliveryObject = objectMapper.readValue(delivery, Delivery.class);
*/

}
