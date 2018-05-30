package com.kk.coding.excercise.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kk.coding.excercise.URLCheckHelper;
import com.kk.coding.excercise.dto.CustomAPIResponse;
import com.kk.coding.excercise.dto.URLDetails;

@RestController
@RequestMapping("urlinfo/1")
public class URLLookupService {

	private static Logger logger = LogManager.getLogger();

	@Autowired
	private URLCheckHelper helper;

	@RequestMapping(value = "/{hostNamePort}/{queryPath}", method = RequestMethod.GET, produces = "application/json")
	public CustomAPIResponse checkURL(HttpServletRequest request, @PathVariable String hostNamePort, @PathVariable String queryPath) {
		
		String queryString = request.getQueryString();
		logger.info("Inside chekcURL.............hostNamePort: " + hostNamePort + " :queryPath " + queryPath + " queryString: "+queryString);
		boolean flag = true;
		CustomAPIResponse response = null;
		StringBuilder b = new StringBuilder();
		b.append(hostNamePort).append("/").append(queryPath).append("?").append(queryString);
		String urltoCheck = b.toString();

		logger.info("URL to check.........." + urltoCheck);

		flag = helper.isURLSafe(urltoCheck);

		if (flag) {
			response = new CustomAPIResponse();
			response.setStatus("SUCCESS");
			response.setMessage("URL is safe to use! ");
		} else {

			response = new CustomAPIResponse();
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
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
	public CustomAPIResponse addURL(@RequestBody URLDetails url) {

		logger.info("Inside addURL.............URL:" + url.toString());
		CustomAPIResponse response = null;
	
		boolean flag = helper.addURL(url);
		
		if (flag) {
			response = new CustomAPIResponse();
			response.setStatus("SUCCESS");
			response.setMessage("URL is successfully added! ");
			logger.info("URL addition Successful..........");
		} else {

			response = new CustomAPIResponse();
			response.setStatus("FAIL");
			response.setMessage("URL is not added! ");
			logger.info("URL addition failed..........");
		}

		return response;
	}

}
