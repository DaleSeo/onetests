package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Document
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class Domain implements Serializable {

	@Id
	protected String id;

	@CreatedDate
	protected Date createdDate;

	@CreatedBy
	@Indexed
	@DBRef(lazy = true)
	protected User createdBy;

	@LastModifiedDate
	protected Date lastModifiedDate;

	@LastModifiedBy
	@Indexed
	@DBRef(lazy = true)
	protected User lastModifiedBy;

}
