package com.bnpparibas.irb.dsira.prospects.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import org.springframework.data.annotation.Id;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Prospect {
	@Id
	private Long id;
	private String identifier;
	private String firstname ;
	private String lastname ;
	private String activity ;
	private String address ;
	private String phoneNumber ;
	private String postalCode ;
	private String city ;

}
