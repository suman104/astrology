/**
 * 
 */
package com.ssstutorials.AstrologyHome.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import lombok.Data;

/**
 * @author suman
 *
 */
@Entity
@Table(name="nakshyatra_property",schema = "astrologyhome")
@Data
public class Nakshyatra_property {
	  @Id
	  private Integer nakId;
	  private String nakName;
	  @ManyToOne
	  @JoinColumn(name = "pasuId",referencedColumnName ="pasuId",updatable = false,insertable = false )
	  private Pasudetails pasudetails;
	  private String gananame;

}
