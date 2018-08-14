package br.com.relevantTweetService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.relevantTweetService.RelevantTweetServiceApplication;
import br.com.relevantTweetService.service.RTSService;
import br.com.relevantTweetService.util.ErrorUtil;

@RestController
@RequestMapping(path = "api")
public class RTSController {
	
	@Autowired
	private RTSService service;

	/* O sistema deve expor os recursos **em formato JSON */
	
	@RequestMapping(path = "most_relevants", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getMostRevelants() {
		
		try {
			return ResponseEntity.status(200).body(service.getMostRevelants());
		} catch (Exception e) {
			RelevantTweetServiceApplication.Log.info("RTSController.getMostRevelants: " + e.getMessage());
			return ErrorUtil.sendError(500, e);
		}
	}

	@RequestMapping(path = "most_mentions", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getMostMentions() {
		
		try {
			return ResponseEntity.status(200).body(service.getMostMentions());
		} catch (Exception e) {
			RelevantTweetServiceApplication.Log.info("RTSController.getMostMentions: " + e.getMessage());
			return ErrorUtil.sendError(500, e);
		}
	}
	
}
