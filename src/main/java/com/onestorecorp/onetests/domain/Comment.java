package com.onestorecorp.onetests.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class Comment extends Domain {

	private String caseId, author, text;

}
