/**
 * 
 */
package com.ssstutorials.AstrologyHome.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssstutorials.AstrologyHome.model.Nakshyatra_property;
import com.ssstutorials.AstrologyHome.repositories.NakshyatraPropertyRepo;
import com.ssstutorials.AstrologyHome.services.DBservices;
import com.ssstutorials.AstrologyHome.services.HomeAstrologyService;
import com.ssstutorials.AstrologyHome.vo.MarriageFactors;

/**
 * @author suman
 *
 */
@Service
public class DBServicesImpl implements DBservices {
	@Autowired
	NakshyatraPropertyRepo npRepo;
	

	@Override
	public int getPasuId(int nakId) {
		int pasuId=0;
         Optional<Nakshyatra_property> optionalProp=npRepo.findById(nakId);
         if(optionalProp.isPresent()) {
        	 pasuId=optionalProp.get().getPasudetails().getPasuId();
         }
         return pasuId;
	}

}
