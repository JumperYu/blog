package mybatis;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yu.article.po.Article;
import com.yu.service.ArticleService;
import com.yu.user.po.Account;
import com.yu.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring/applicationContext**.xml")
public class TestMybatisWithSpringTest {

	@Resource
	private UserService userService;

	@Resource
	private ArticleService articleService;

	@Test
	public void testUserService() {
		System.out.println(userService
				.validateAccount(new Account("zxm", "123")));
	}

	@Test
	public void testArticleServiceAdd() {
		Article article = new Article();
		article.setTitle("Do not go gentle into that good night");
		article.setContent("Do not go gentle into that good night, rage, rage agianst the dying of time.");
		System.out.println(articleService.addArticle(article));
	}
	
	@Test
	public void testArticleServiceQuery() {
		System.out.println(articleService.getArticle(null));
	}
}
