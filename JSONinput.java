import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.util.Iterator;
 
public class JSONinput {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("dailyElectricityUsage_2020_02_28.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray electricityUsage = (JSONArray) jsonObject.get("Electricity Usage");
			Iterator<JSONObject> iterator = electricityUsage.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
// File read in used in code already but this is going to be sued for output file pull