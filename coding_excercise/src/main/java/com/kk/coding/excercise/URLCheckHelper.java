package com.kk.coding.excercise;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kk.coding.excercise.dao.URLInfoMongoRepository;
import com.kk.coding.excercise.model.URLInfo;

@Component
public class URLCheckHelper {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private URLInfoMongoRepository mongoRepo;
	
	public boolean isURLSafe(String url) {
		
		
		List<URLInfo> urlList = mongoRepo.findAll();

		for (URLInfo info : urlList) {
			logger.info("URL from DB.....:" + info.getValue());
			if (url.equals(info.getValue())) {
				logger.info("URL is not safe.....:" + url);
				return false;
			}
		}
		return true;
		
	}
	
	public boolean addURL (URLInfo url) {
		boolean returnVar = false; 
		try {
			mongoRepo.save(url);
			returnVar = true;
		}catch (Exception e) {
			logger.error ("Exception while saving the URL to DB.........."+e.toString());	
		}
		return returnVar;
	}
	
	//Test method
	private void listURLSfromDB () {
		List<URLInfo> urlList = mongoRepo.findAll();
		
		for (URLInfo info : urlList) {
			logger.info("URL from DB.....:" + info.getValue());
			
		}
	}

}
