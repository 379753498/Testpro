import org.com.testpro.Enum.DataEnum;




public class BridgeData {

	
	public static void main(String[] args) {
		
		
		DataEnum[] values = DataEnum.values();
		for (DataEnum dataEnum : values) {
			System.out.println(dataEnum.getDataname());
			System.out.println(dataEnum.getSpringDatasourceBeanName());

		}
	}
}
