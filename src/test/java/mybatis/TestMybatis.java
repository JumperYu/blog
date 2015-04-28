package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yu.user.mapper.AccountMapper;

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
	public void testAccout(){
		AccountMapper mapper = session.getMapper(AccountMapper.class);
		System.out.println(mapper.queryAccountByPassport("haha"));
	}
	
}
