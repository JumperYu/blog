package mybatis;

/**
 * mybatis pojo
 *
 * @author zengxm
 * @date 2015年4月24日
 *
 */
/**
 *
 * @author zengxm
 * @date 2015年4月24日
 *
*/
public class Blog {

	private int id;
	private String content;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", content=" + content + "]";
	}

}
