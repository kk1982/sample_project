package com.kk.coding.excercise.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kk.coding.excercise.URLCheckHelper;
import com.kk.coding.excercise.beans.URLCheckResponse;
import com.kk.coding.excercise.model.URLInfo;

@RestController
@RequestMapping("urlinfo/1")
public class URLLookupService {

	private static Logger logger = LogManager.getLogger();

	//@Autowired
	//private URLInfoMongoRepository mongoRepo;
	
	@Autowired
	private URLCheckHelper helper;

	@RequestMapping(value = "/{hostNamePort}/{pathQueryString}", method = RequestMethod.GET, produces = "application/json")
	public URLCheckResponse checkURL(@PathVariable String hostNamePort, @PathVariable String pathQueryString) {
		// System.out.println("In sysout..........");
		logger.info("Inside chekcURL.............hostNamePort:" + hostNamePort + " :pathQueryString" + pathQueryString);

		
		boolean flag = true;
		URLCheckResponse response = null;
		StringBuilder b = new StringBuilder();
		b.append(hostNamePort).append(pathQueryString);
		String urltoCheck = b.toString();

		logger.info("URL to check.........." + urltoCheck);

		flag = helper.isURLSafe(urltoCheck);

		if (flag) {
			response = new URLCheckResponse();
			response.setStatus("SUCCESS");
			response.setMessage("URL is safe to use! ");
		} else {

			response = new URLCheckResponse();
			response.setStatus("FAIL");
			response.setMessage("URL is not safe to use! ");
		}

		return response;
	}

	/**
	 * This method is provided temporarily to add new URLs to DB
	 * 
	 * @param hostNamePort
	 * @param pathQueryString
	 * @return
	 */
	@RequestMapping(value = "/add/{url}", method = RequestMethod.GET, produces = "application/json")
	public URLCheckResponse addURL(@PathVariable String url) {

		logger.info("Inside addURL.............URL:" + url);
		URLCheckResponse response = null;
		URLInfo urlInfo = new URLInfo(url);
		boolean flag = helper.addURL(urlInfo);
		
		logger.info("URL addition Successful..........");

		if (flag) {
			response = new URLCheckResponse();
			response.setStatus("SUCCESS");
			response.setMessage("URL is successfully added! ");
		} else {

			response = new URLCheckResponse();
			response.setStatus("FAIL");
			response.setMessage("URL is not added! ");
		}

		return response;
	}

}
