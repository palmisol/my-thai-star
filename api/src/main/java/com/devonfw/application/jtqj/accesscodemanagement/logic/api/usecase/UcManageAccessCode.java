package com.devonfw.application.jtqj.accesscodemanagement.logic.api.usecase;

import com.devonfw.application.jtqj.accesscodemanagement.logic.api.to.AccessCodeEto;

public interface UcManageAccessCode {
	
	/**
	   * Deletes an accessCode from the database by its ID 'accessCodeId'. Decreases the count of customers of the queue
	   * assigned to the access code by one.
	   *
	   * @param queueId Id of the queue to delete
	   */
	  void deleteAccessCode(long accessCodeId);

	  /**
	   * Saves a queue and stores it in the database. Increases the count of customers of the queue assigned to the access
	   * code by one.
	   *
	   * @param queue the {@link AccessCodeEto} to create.
	   * @return the new {@link AccessCodeEto} that has been saved with ID and version.
	   */
	  AccessCodeEto saveAccessCode(AccessCodeEto accessCodeEto);

}
