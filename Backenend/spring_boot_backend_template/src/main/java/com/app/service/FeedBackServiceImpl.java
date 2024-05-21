package com.app.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.BusDao;
import com.app.dao.FeedBackDao;
import com.app.dao.PassengerDao;
import com.app.dto.ApiResponse;
import com.app.dto.BusDto;
import com.app.dto.FeedbackDTO;
import com.app.entities.Bus;
import com.app.entities.FeedBack;
import com.app.entities.Passenger;

import custom_exceptions.ResourceNotFoundException;

@Service
@Transactional
public class FeedBackServiceImpl implements FeedBackService {
	@Autowired
	private FeedBackDao feedBackDao;

	@Autowired
	private BusDao busDao;

	@Autowired
	private PassengerDao passengerDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public FeedbackDTO saveFeedback(@Valid FeedbackDTO dto) {
//	Bus bus = busDao.findById(dto.getBusid()).orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!"));

		FeedBack feedback = mapper.map(dto, FeedBack.class);
		Bus bus = busDao.findById(dto.getBusid()).orElseThrow();
		Passenger passenger = passengerDao.findById(dto.getPassengerid()).orElseThrow();
		feedback.setBus(bus);
		feedback.setPassenger(passenger);
		FeedBack persistenceFeedBack = feedBackDao.save(feedback);
		FeedbackDTO feedBackDto=new FeedbackDTO();
		feedBackDto.setBusid(persistenceFeedBack.getBus().getId());
		feedBackDto.setPassengerid(persistenceFeedBack.getPassenger().getId());
		feedBackDto.setFeedBack(persistenceFeedBack.getFeedBack());
		return feedBackDto;
	}

	@Override
	public FeedbackDTO getFeedbackDetails(Long id) {
		// TODO Auto-generated method stub
		return mapper.map(
				feedBackDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid Bus Id !!!!")),
				FeedbackDTO.class);
	}

	@Override
	public FeedbackDTO updateFeedback(Long id, @Valid FeedbackDTO feedbackDTO) {
		FeedBack persistentFeedBack = feedBackDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Feedback  Id :" + id));
		System.out.println(FeedbackDTO.class);
		persistentFeedBack.setFeedBack(feedbackDTO.getFeedBack());
		FeedBack updatedFeedBack = feedBackDao.save(persistentFeedBack);
		// return mapper.map(persistentFeedBack, FeedbackDTO.class);
		return mapper.map(updatedFeedBack, FeedbackDTO.class);
	}

	@Override
	public ApiResponse deleteFeedback(Long id) {
		FeedBack persistentFeedBack = feedBackDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid  Id !!!!"));
		feedBackDao.delete(persistentFeedBack);
		return new ApiResponse("Feedback Details of Feedback with ID " + persistentFeedBack.getId() + " deleted....");

	}

}
