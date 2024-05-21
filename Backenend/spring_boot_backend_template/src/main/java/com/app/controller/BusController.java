package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.app.dto.BusDto;
import com.app.entities.Bus;
import com.app.service.BusService;

@RestController
@RequestMapping("/bus")
@CrossOrigin(origins="http://localhost:3000")
public class BusController {
	

	@Autowired
	private BusService busService;
	

	
    //1.add new Bus 
	//http://host:port/bus  , method = post
	@PostMapping
	public ResponseEntity<?> addNewBus(@RequestBody @Valid BusDto dto){
		System.out.println("in add Bus " + dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(busService.addNewBus(dto));
	}
	
	
//	@GetMapping("/source")
//	public List<String> getBusSource(){
//		System.out.println("in get Bus by source ");
//		return busService.getBusSource();
//	}
	
	//2.get all bus
		@GetMapping({"/source"})
		public ResponseEntity<List<BusDto>> getAllBus(){
			System.out.println("in get Bus " );
			return ResponseEntity.ok(busService.getAllBus());
		}
	
//	@GetMapping({"/source"})
//	public ResponseEntity<List<Bus>> getAllBusSource(){
//		System.out.println("in get Bus " );
//		return ResponseEntity.ok(busService.getAllBus());
//	}
	
	//2.get all buses by id
	@GetMapping("/{busId}")
	public ResponseEntity<?> getBusDetails(@PathVariable Long busId){
		System.out.println("in get Bus by id " + busId);
		return ResponseEntity.ok(busService.getBusDeails(busId));
	}
	
	
	//3.get  buses by source and destination
	//http://host:port/bus  , method =get
	@GetMapping
	public ResponseEntity<List<BusDto>> getBusDetailsBySourceAndDestination(@RequestParam("source") String source,@RequestParam("destination") String destination  ){
		System.out.println("in get Bus by source and destination " + source + destination );
		return ResponseEntity.ok(busService.getBusDetailsBySourceAndDestination(source,destination));
	}
	
	//4. update existing  Bus
			// http://host:port/bus/{bustId}
			@PutMapping("/{busId}")
			public ResponseEntity<?> updateBus(@PathVariable Long busId,
					@RequestBody @Valid BusDto bus) {
				System.out.println("in update dept " +busId+" "+ bus);		
				return ResponseEntity.ok(busService.updateBus(busId , bus));
			
	
}
			
			//5.delete bus
			// http://host:port/bus/{bustId}
			@DeleteMapping("/{busId}")
			public ResponseEntity<?> deleteBus(@PathVariable Long busId) {
				System.out.println("in delete dept " +busId);		
				return ResponseEntity.ok(busService.deleteBus(busId ));
			
	
}
}