/**
 * 
 */
package com.ssstutorials.AstrologyHome.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.ssstutorials.AstrologyHome.model.Nakshyatra_property;
import com.ssstutorials.AstrologyHome.model.Pasudetails;

/**
 * @author suman
 *
 */
@Repository
@EnableJpaRepositories
public interface PasudetailsRepo extends JpaRepository<Pasudetails,Integer> {

}
