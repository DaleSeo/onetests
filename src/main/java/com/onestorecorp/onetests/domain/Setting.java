package com.onestorecorp.onetests.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
@Data
public class Setting extends Domain {

	List<String> bodyExclusions;

	List<String> headerExclusions;

}
