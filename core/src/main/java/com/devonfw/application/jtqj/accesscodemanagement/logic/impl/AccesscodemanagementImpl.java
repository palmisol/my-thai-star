package com.devonfw.application.jtqj.accesscodemanagement.logic.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.application.jtqj.accesscodemanagement.logic.api.Accesscodemanagement;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeCto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcFindAccessCode;
import com.devonfw.application.jtqj.general.logic.base.AbstractComponentFacade;

/**
 * Implementation of component interface of accesscodemanagement
 */
@Named
public class AccesscodemanagementImpl extends AbstractComponentFacade implements Accesscodemanagement {

	@Inject
	private UcFindAccessCode ucFindAccessCode;

	@Override
	public AccessCodeCto findAccessCodeCto(long id) {

		return ucFindAccessCode.findAccessCodeCto(id);
	}

	@Override
	public Page<AccessCodeCto> findAccessCodeCtos(AccessCodeSearchCriteriaTo criteria) {

		return ucFindAccessCode.findAccessCodeCtos(criteria);
	}
}
