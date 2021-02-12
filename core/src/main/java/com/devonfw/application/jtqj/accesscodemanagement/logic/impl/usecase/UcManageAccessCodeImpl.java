package com.devonfw.application.jtqj.accesscodemanagement.logic.impl.usecase;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api.AccessCodeEntity;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.Accesscodemanagement;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeSearchCriteriaTo;
import com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase.UcManageAccessCode;
import com.devonfw.application.jtqj.accesscodemanagement.logic.base.usecase.AbstractAccessCodeUc;
import com.devonfw.application.jtqj.queuemanagement.logic.api.Queuemanagement;
import com.devonfw.application.jtqj.queuemanagement.logic.impl.usecase.UcManageQueueImpl;

@Named
@Validated
@Transactional
public class UcManageAccessCodeImpl extends AbstractAccessCodeUc implements UcManageAccessCode {

	  @Inject
	  private Queuemanagement queuemanagement;

	  @Inject
	  private Accesscodemanagement accesscodemanagement;

	  /** Logger instance. */
	  private static final Logger LOG = LoggerFactory.getLogger(UcManageQueueImpl.class);

	  @Override
	  public void deleteAccessCode(long accessCodeId) {

	    // we get the queueId using the AccessCodeRepository
	    long queueId = getAccessCodeRepository().find(accessCodeId).getQueueId();

	    /**
	     * Using the method getQueuemanagement() gives access to the methods that were created earlier in the usecasemanage
	     * (inside the queue component). This is done so each component takes care of its own modifications.
	     */
	    this.queuemanagement.decreaseQueueCustomer(queueId);

	    LOG.debug("The queue with id '{}' has decreased its customers.", queueId);

	    // then we delete the accesscode
	    getAccessCodeRepository().deleteById(accessCodeId);
	    LOG.debug("The accesscode with id '{}' has been deleted.", accessCodeId);

	  }

	  @Override
	  public AccessCodeEto saveAccessCode(AccessCodeEto accessCodeEto) {

	    // make sure the object is not null
	    Objects.requireNonNull(accessCodeEto, "UcManageAccessImpl accessCode null");

	    AccessCodeEntity accessCodeEntity = getBeanMapper().map(accessCodeEto, AccessCodeEntity.class);

	    long queueEntityId = accessCodeEntity.getQueueId();

	    AccessCodeSearchCriteriaTo accessCodeSearchCriteriaTo = new AccessCodeSearchCriteriaTo();
	    accessCodeSearchCriteriaTo.setQueueId(queueEntityId);
	    Pageable pageable = PageRequest.of(0, 1000);
	    accessCodeSearchCriteriaTo.setPageable(pageable);

	    /**
	     * Calling the parent with the method getAccesscodemanagement() we use the method findAccessCodeEtos() that will
	     * call the implementation of the method inside (UcFindAccessCodeImpl) through the interface. This allows us to use
	     * the {@link UcFindAccessCodeImpl}.
	     */
	    List<AccessCodeEto> accessCodeEtosInQueue = getAccesscodemanagement().findAccessCodeEtos(accessCodeSearchCriteriaTo)
	        .getContent();

	    // if there are no ETOs, we set the ticket to the first code
	    // else we get the digit of the last ticket in the list and generate a new code for the ticket
	    if (accessCodeEtosInQueue.isEmpty()) {
	      accessCodeEntity.setTicketNumber("Q000");
	    } else {
	      AccessCodeEto lastAccessCode = accessCodeEtosInQueue.get(accessCodeEtosInQueue.size() - 1);
	      int lastTicketDigit = Integer.parseInt(lastAccessCode.getTicketNumber().substring(1));
	      accessCodeEntity.setTicketNumber(generateTicketCode(lastTicketDigit));
	    }

	    // set the creation time, startTime and endTime
	    accessCodeEntity.setCreationTime(Timestamp.from(Instant.now()));
	    accessCodeEntity.setStartTime(null);
	    accessCodeEntity.setEndTime(null);

	    // save the AccessCode
	    AccessCodeEntity accessCodeEntitySaved = getAccessCodeRepository().save(accessCodeEntity);
	    LOG.debug("The accesscode with id '{}' has been saved.", accessCodeEntitySaved.getId());

	    /**
	     * Using the method getQueuemanagement() gives access to the methods that were created earlier in the usecasemanage
	     * (inside the queue component). This is done so each component takes care of its own modifications.
	     */
	    getQueuemanagement().increaseQueueCustomer(accessCodeEntitySaved.getQueueId());

	    LOG.debug("The queue with id '{}' has increased its customers.", accessCodeEntitySaved.getQueueId());

	    return getBeanMapper().map(accessCodeEntitySaved, AccessCodeEto.class);
	  }

	  /**
	   * Generates a new ticked code using the ticket digit of the last codeaccess created.
	   *
	   * @param lastTicketDigit the int of the last codeaccess created.
	   * @return the String with the new ticket code (example: 'Q005').
	   */
	  public String generateTicketCode(int lastTicketDigit) {

	    int newTicketDigit = lastTicketDigit + 1;
	    String newTicketCode = "";
	    if (newTicketDigit == 1000) {
	      newTicketCode = "Q000";
	    } else {
	      StringBuilder stringBuilder = new StringBuilder();
	      stringBuilder.append(newTicketDigit);
	      while (stringBuilder.length() < 3) {
	        stringBuilder.insert(0, "0");
	      }
	      stringBuilder.insert(0, "Q");
	      newTicketCode = stringBuilder.toString();
	    }
	    return newTicketCode;
	  }

	  public Queuemanagement getQueuemanagement() {

	    return this.queuemanagement;
	  }

	  public Accesscodemanagement getAccesscodemanagement() {

	    return this.accesscodemanagement;
	  }

	}
