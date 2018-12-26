package org.com.testpro.Enum;


/**
 *
 * 配置SpringDatasourceBeanName与DataSource实例的对应关系； 配置完毕后 需要在DataEnum.class中新增枚举数据即可实现自动切换数据源工作
 * 	DynamicDataSource DynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		targetDataSources.put("DataSource13", Get13dataSource());
		targetDataSources.put("DataSource15", Get15dataSource());
		targetDataSources.put("DataSourceHbase", GetDataSourceHbase());
		targetDataSources.put("DataSourceHive", GetDataSourceHive());
		DynamicDataSource.setTargetDataSources(targetDataSources);
		DynamicDataSource.setDefaultTargetDataSource(Get13dataSource());
		return DynamicDataSource;
 * @author Administrator
 *
 */
public enum DataEnum {
	
	

	DATA_SOURCE_FROM_13 ("13","DataSource13"),
	DATA_SOURCE_15 ("15","DataSource15"),
	DATA_SOURCE_HBase ("Hbase","DataSourceHbase"),
	DATA_SOURCE_Hive ("Hive","DataSourceHive"); 

	DataEnum(String dataname, String springDatasourceBeanName) {
		this.dataname = dataname;
		SpringDatasourceBeanName = springDatasourceBeanName;
	}
	public String getDataname() {
		return dataname;
	}

	public String getSpringDatasourceBeanName() {
		return SpringDatasourceBeanName;
	}

	private String dataname;
	private String SpringDatasourceBeanName;
}
