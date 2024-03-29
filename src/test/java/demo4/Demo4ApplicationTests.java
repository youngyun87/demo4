package demo4;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SpringBootTest
class Demo4ApplicationTests {

    @Test
    void test() {
        try {

            // 오늘날짜 yyyymmdd
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c1 = Calendar.getInstance();
            String strToday = sdf.format(c1.getTime());

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2B5m3O8Rjr04idU6zglHxFz3KCCIwKfrl2hIGwYLK4ykKYV6dVbfPUSux%2FRAv5u6J%2FrU038hJczJtsKnUisHU2g%3D%3D"); /*Service Key*/

            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
            urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(strToday, "UTF-8")); /*‘날짜 yyyymmdd*/


            urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(getCurrentDateHhmm(), "UTF-8")); /*06시 발표(정시단위) */

            // 논현동 x,y
            urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode("61", "UTF-8")); /*예보지점의 X 좌표값*/
            urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode("126", "UTF-8")); /*예보지점의 Y 좌표값*/
            URL url = new URL(urlBuilder.toString());

            // 기상청 api로 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            // 결과 String
            String jsonString = sb.toString();

            System.out.println(jsonString);

           // 가장 큰 JSONObject 부터 가져옵니다.
            JSONObject jObject = new JSONObject(jsonString);
            JSONObject post1bject = jObject.getJSONObject("response");
            JSONObject post2bject = post1bject.getJSONObject("body");
            JSONObject post3bject = post2bject.getJSONObject("items");

            // 배열을 가져옵니다.
            JSONArray jArray = post3bject.getJSONArray("item");


            // 배열의 모든 아이템을 출력합니다.
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);
                String category = obj.getString("category");
                String fcstValue = obj.getString("fcstValue");

            //     System.out.println(i + " : " +category);
            //     System.out.println(i + " : " +obsrValue);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getCurrentDateHhmm() {
		Date today = new Date();
		Locale currentLocale = new Locale("KOREAN", "KOREA");
		String pattern = "hhmmss"; //hhmmss로 시간,분,초만 뽑기도 가능
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, currentLocale);

        int hour = Integer.parseInt(formatter.format(today).substring(0,2));
        String hourStr = "";
        if(hour != 0){
            hour = hour - 1;
        }
        if(hour < 10){
            hourStr = "0" + String.valueOf(hour);
        }else{
            hourStr = String.valueOf(hour);
        }

		return hourStr + "30";
	}

}
