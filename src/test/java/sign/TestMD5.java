package sign;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

public class TestMD5 {

	private long time;

	@Before
	public void init() {
		time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
	}

	@Test
	public void testLogin() {
		// gameName+platformName+serverCode+uid+time+fcm+key
		String signKey = String.format("%s%s%s%s%d%d%s", "smyy", "160yx", "s0",
				"71", 1430898530, 1, "miwEkL5mzCISiXnGgiuGJJPPkxaSw80r");
		String sign = DigestUtils.md5Hex(signKey).toString();
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

	//|http://proxy.gop.yy.com/common/smyy/93pk/charge.do?uid=wgole&serverCode=s0&time=1430886898&orderId+=201505061234582998&rolename=%E7%99%BD%E5%BD%B1%E5%AD%90&gameCurrency=10&rmb=1&sign=4306917de7099dbe5552d0271ad943ee
	
	@Test
	public void testCharge() {
		// gameName+platformName+serverCode+uid+orderId+rmb+gameCurrency+time+key
		String signKey = String.format("%s%s%s%s%s%s%s%d%s", "smyy", "93pk",
				"s0", "wgole", "201505061234582998", "1", "10", 1430886898, "key");
		String sign = DigestUtils.md5Hex(signKey).toString();
		System.out.println("---------sign key:" + signKey);
		System.out.println("---------sign result:" + sign);
	}
}
