package com.devonfw.application.jtqj.accesscodemanagement.dataaccess.api;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.devonfw.application.jtqj.queuemanagement.dataaccess.api.QueueEntity;
import com.devonfw.application.jtqj.visitormanagement.dataaccess.api.VisitorEntity;

@Entity
@Table(name = "AccessCode")
public class AccessCodeEntity {

  @Size(min = 2, max = 5)
  private String ticketNumber;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp creationTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp startTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp endTime;

  private VisitorEntity visitor;

  private QueueEntity queue;

  /**
   * @return the ticketNumber
   */
  public String getTicketNumber() {
    return ticketNumber;
  }

  /**
   * @param ticketNumber the ticketNumber to set
   */
  public void setTicketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  /**
   * @return the creationTime
   */
  public Timestamp getCreationTime() {
    return creationTime;
  }

  /**
   * @param creationTime the creationTime to set
   */
  public void setCreationTime(Timestamp creationTime) {
    this.creationTime = creationTime;
  }

  /**
   * @return the startTime
   */
  public Timestamp getStartTime() {
    return startTime;
  }

  /**
   * @param startTime the startTime to set
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  /**
   * @return the endTime
   */
  public Timestamp getEndTime() {
    return endTime;
  }

  /**
   * @param endTime the endTime to set
   */
  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  /**
   * @return the visitor
   */
  @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "idVisitor")
  public VisitorEntity getVisitor() {
    return visitor;
  }

  /**
   * @param visitor the visitor to set
   */
  public void setVisitor(VisitorEntity visitor) {
    this.visitor = visitor;
  }

  /**
   * @return the queue
   */
  @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
  @JoinColumn(name = "idQueue")
  public QueueEntity getQueue() {
    return queue;
  }

  /**
   * @param queue the queue to set
   */
  public void setQueue(QueueEntity queue) {
    this.queue = queue;
  }

}