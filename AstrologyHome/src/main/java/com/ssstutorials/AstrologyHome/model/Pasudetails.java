/**
 * 
 */
package com.ssstutorials.AstrologyHome.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author suman
 *
 */
@Entity
@Table(name="pasudetails",schema = "astrologyhome")
@Data
public class Pasudetails {
	  @Id
	  private Integer pasuId;
	  private String pasuname;

}
