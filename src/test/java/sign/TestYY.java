package sign;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

public class TestYY {

	private long time;

	private static final String KEY_93PK = "mmgs4cl3x_jwyegDT2e9oWzyx8iDOgh6";
	private static final String KEY_8090 = "7Zo5aWhjh4949b5G_7bBTnxVpQYTrHmi";

	@Before
	public void init() {
		time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
	}

	@Test
	public void testLogin() {
		// gameName+platformName+serverCode+uid+time+fcm+key
		String signKey = String.format("%s%s%s%s%d%d%s", "smyy", "160yx", "s0",
				"71", 1430898530, 1, "miwEkL5mzCISiXnGgiuGJJPPkxaSw80r");
		String sign = DigestUtils.md5Hex("smyy2323wans02323wan_66769614310519261rvDihMOmyLMrs4EWTgxoSSwWjywkqVLQ").toString();
		System.out.println("---------sign key:" + signKey);
		System.out.println("---------sign result:" + sign);
	}

	@Test
	public void testGetRole() {
		// gameName+platformName+serverCode+uid+time+key
		String signKey = String.format("%s%s%s%s%d%d%s", "smyy", "pt", "s0",
				"uid", time, 1, "key");
		String sign = DigestUtils.md5Hex(signKey).toString();
		System.out.println("---------sign key:" + signKey);
		System.out.println("---------sign result:" + sign);
	}

	// |http://proxy.gop.yy.com/common/smyy/93pk/charge.do?uid=wgole&serverCode=s0&time=1430886898&orderId+=201505061234582998&rolename=%E7%99%BD%E5%BD%B1%E5%AD%90&gameCurrency=10&rmb=1&sign=4306917de7099dbe5552d0271ad943ee

	@Test
	public void testCharge() {
		// gameName+platformName+serverCode+uid+orderId+rmb+gameCurrency+time+key
		String signKey = String.format("%s%s%s%s%s%s%s%d%s", "smyy", "93pk",
				"s0", "wgole", "201505061234582999", "1", "10", time,
				"mmgs4cl3x_jwyegDT2e9oWzyx8iDOgh6");
		String sign = DigestUtils.md5Hex(signKey).toString();
		System.out.println("---------sign key:" + signKey);
		System.out.println("---------sign result:" + sign);
	}
	
	// 测试神魔三国的充值回调
	@Test
	public void testPostCharge() {
		// sign=84f0eb3123a916dbc7f1705a889714f9
		String path = "http://proxy.gop.yy.com/common/smyy/8090/charge.do";
		String game = "smyy";
		String platform = "8090";
		String uid = "qiufuqi";
		String serverCode = "s0";
		String orderId = "cs3165555413132";
		int gameCurrency = 10; // 元宝
		int rmb = 1; // 元或分
		String signKey = String.format("%s%s%s%s%s%s%s%d%s", game, platform,
				serverCode, uid, orderId, rmb, gameCurrency, time, KEY_8090);
		String sign = DigestUtils.md5Hex(signKey).toString();
		String content = String
				.format("uid=%s&serverCode=%s&time=%d&orderId=%s&gameCurrency=%d&rmb=%d&sign=%s",
						uid, serverCode, time, orderId, gameCurrency, rmb, sign);
		request(path, "GET", null, content);
	}

	public void request(String path, String method,
			Map<String, String> headers, String content) {
		String ret = "";
		HttpURLConnection conn = null;
		try {
			URL url = new URL(path);
			if (path.startsWith("https")) {// -->> Begin https init
				// ignoreSSL();
			}// -->> End of https init
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(method); // set GET/POST has problem
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // --> default header
			if (headers != null) {
				for (String key : headers.keySet()) {
					conn.setRequestProperty(key, headers.get(key));
				}// -->> end of for
			}// -->> end of if
			conn.connect();
			if (content != null) {
				DataOutputStream dos = new DataOutputStream(
						conn.getOutputStream());
				// 要传的参数
				dos.writeBytes(content.toString());
				dos.flush();
				dos.close();
			}
			int code = conn.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), "UTF-8"));
				String lines = "";
				while ((lines = reader.readLine()) != null) {
					ret += lines;
				}
				reader.close();
			} else {
				System.out.println("request error");
			}
		} catch (Exception e) {
			ret = "";
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		System.out.println("request result=" + ret);
	}
}
