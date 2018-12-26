package org.com.testpro.Bean;



import java.io.Serializable;

public class RedisHbaseVuale implements Serializable {
	
	
	@Override
	public String toString() {
		return "RedisHbaseVuale [HbaseValue=" + HbaseValue + ", RedisValue="
				+ RedisValue + ", ISok=" + ISok + "]";
	}
	public RedisHbaseVuale(String hbaseValue, String redisValue, Boolean iSok) {
		super();
		HbaseValue = hbaseValue;
		RedisValue = redisValue;
		ISok = iSok;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String HbaseValue;
	private String RedisValue;
	private Boolean ISok;
	public String getHbaseValue() {
		return HbaseValue;
	}
	public void setHbaseValue(String hbaseValue) {
		HbaseValue = hbaseValue;
	}
	public String getRedisValue() {
		return RedisValue;
	}
	public void setRedisValue(String redisValue) {
		RedisValue = redisValue;
	}
	public Boolean getISok() {
		return ISok;
	}
	public void setISok(Boolean iSok) {
		ISok = iSok;
	}


}
