package kr.jay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SubTask
 *
 * @author jaypark
 * @version 1.0.0
 * @since 2023/08/07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
	private String membershipID;
	private String subTaskName;
	private String taskType; // "banking", "membership"
	private String status; // "success", "fail", "ready"
}
