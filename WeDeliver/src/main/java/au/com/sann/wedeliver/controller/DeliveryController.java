package au.com.sann.wedeliver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
	public ResponseEntity<Delivery> newDelivery(@RequestBody String deliveryJson) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		Delivery deliveryObject = objectMapper.readValue(deliveryJson, Delivery.class);
		
		deliveryObject.setDeliverer(delivererRepository.save(deliveryObject.getDeliverer()));
		return new ResponseEntity<>(deliveryRepository.save(deliveryObject), HttpStatus.CREATED);
	}

}
