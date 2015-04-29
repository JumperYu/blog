package mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yu.user.mapper.AccountMapper;
import com.yu.user.po.Account;

public class TestMybatis {

	private SqlSession session;

	@Before
	public void init() throws IOException {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		session = sqlSessionFactory.openSession();
	}

	@After
	public void close() {
		session.close();
	}

	@Test
	public void testSessionFactoryFromPO() throws IOException {
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		Blog blog = mapper.selectBlogFromId(1);
		System.out.println(blog);
	}

	@Test
	public void testSessionFactoryFromXML() throws IOException {
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		Blog blog = mapper.selectBlogFromContent("哈哈");
		System.out.println(blog);
	}

	@Test
	public void testAccout() {
		AccountMapper mapper = session.getMapper(AccountMapper.class);
		System.out.println(mapper.queryAccountByPassport("haha"));
	}

	@Test
	public void testAccountByPackage() {
		Account account = (Account) session.selectOne(
				"com.yu.user.mapper.AccountMapper.queryAccountByPassport",
				"haha");
		System.out.println(account);
	}

	@Test
	public void testMoreParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("index", "0");
		params.put("rows", "10");
		List<Account> accounts = session.selectList(
				"com.yu.user.mapper.AccountMapper.queryAll", params);
		System.out.println(accounts);
	}
}
