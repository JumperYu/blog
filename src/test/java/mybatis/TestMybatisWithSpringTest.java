package mybatis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yu.user.po.Account;
import com.yu.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring/applicationContext**.xml")
public class TestMybatisWithSpringTest {

	@Resource
	private UserService userService;

	@Test
	public void testMybatisDao() {
		System.out.println(userService
				.validateAccount(new Account("zxm", "123")));
	}

}
