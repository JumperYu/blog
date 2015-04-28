package mybatis;

import org.apache.ibatis.annotations.Select;

/**
 * 
 * mapper 注解和xml只允许一个
 *
 * @author zengxm
 * @date 2015年4月24日
 *
 */
public interface BlogMapper {
	
	// 注解sql
	@Select("SELECT * FROM blog WHERE id = #{id}")
	Blog selectBlogFromId(int id);
	
	// 从xml读sql
	@Select("SELECT * FROM blog WHERE content = #{content}")
	Blog selectBlogFromContent(String content);
}
