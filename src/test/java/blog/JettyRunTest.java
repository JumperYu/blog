package blog;


public class JettyRunTest {

/*	public static void main(String[] args) throws Exception {
		System.setProperty("PROJECT_NAME", "blog");
		System.setProperty("ENV", "dev");
		String dir = System.getProperty("user.dir");

		Server server = buildNormalServer(80, "/");
		server.start();
	}

	*//**
	 * 创建用于正常运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 *//*
	public static Server buildNormalServer(int port, String contextPath) {
		Server server = new Server(port);
		WebAppContext webContext = new WebAppContext("src/main/webapp",
				contextPath);
		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		server.setHandler(webContext);
		server.setStopAtShutdown(true);
		return server;
	}*/
}
