 package kr.jay.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

 /**
  * FirmBankingRequestCreatedEvent
  *
  * @author jaypark
  * @version 1.0.0
  * @since 2023/09/07
  */

 @Data
 @EqualsAndHashCode(callSuper = false)
 @AllArgsConstructor
 @NoArgsConstructor
 public class FirmBankingRequestUpdatedEvent {
	 private int firmBankingStatus;
 }
