package org.com.testpro.DataresultBean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class BridgeDataResult {

	private String dataType;
	
	private int level;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	private String[] values;
	
}
