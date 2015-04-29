package mybatis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yu.user.dao.AccountDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring/applicationContext**.xml")
public class MybatisWithSpringTest {

	@Resource
	private AccountDao accountDao;

	@Test
	public void testMybatisDao() {
		accountDao.test();
		accountDao.test_2();
		accountDao.test_3();
		accountDao.test_4();
	}

}
