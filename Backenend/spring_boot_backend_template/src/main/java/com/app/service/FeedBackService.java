package com.app.service;

import javax.validation.Valid;

import com.app.dto.ApiResponse;
import com.app.dto.FeedbackDTO;
import com.app.entities.FeedBack;

public interface FeedBackService {
 
	FeedbackDTO saveFeedback(FeedbackDTO feedbackDTO);

	FeedbackDTO getFeedbackDetails(Long id);

	FeedbackDTO updateFeedback(Long id, @Valid FeedbackDTO feedbackDTO);

	//  deleteFeedback(Long id);
	
	//ApiResponse deleteBus(Long busId);

	ApiResponse deleteFeedback(Long id);
}
