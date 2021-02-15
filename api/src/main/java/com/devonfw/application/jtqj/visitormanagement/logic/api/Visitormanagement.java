package com.devonfw.application.jtqj.visitormanagement.logic.api;


import org.springframework.stereotype.Service;

import com.devonfw.application.jtqj.visitormanagement.logic.api.usecase.UcFindVisitor;
import com.devonfw.application.jtqj.visitormanagement.logic.api.usecase.UcManageVisitor;

/**
 * Interface for Visitormanagement component.
 */
public interface Visitormanagement extends UcFindVisitor, UcManageVisitor {

}
