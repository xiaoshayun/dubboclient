package dubboclient;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
@SpringBootApplication
@ImportResource("classpath:ztev-dubbo-consumer.xml")
public class SimpleApplication implements CommandLineRunner {
	private final static Logger logger = LoggerFactory.getLogger(SimpleApplication.class);
	private static String mypackage = "--mypackage=";
	private static String methodname = "--methodname=";
	private static String methodtype = "--methodtype=";
	private static String parameter = "--parameter=";
	
	@Autowired
	@Qualifier("myService")
	IMyService iMyService;
	public static void main(String args[]) {
		try {
			//args = new String[]{"com.zte.ums.ztev.trade.api.ITradeRecordService","queryTradeRecordByTime","string~string~string~int~int","ztev.driver=440300201706161420580001~2017~07~0~10"};
			System.out.println(Arrays.asList(args));
			if(args==null||args.length==0){
				System.out.println("parameter is null");
				System.exit(-1);
			}
			if(args[0].equalsIgnoreCase("--help")||args[0].equalsIgnoreCase("--h")){
				System.out.println("eg:");
				System.out.println("com.zte.ums.ztev.user.api.IUserMgtExtService queryUserByOID string ztev.driver=440300201706161420580001");
				System.exit(-1);
			}
			if(args==null||args.length<4){
				System.out.println("parameter is error");
				System.exit(-1);
			}
			
			args[0]=mypackage+args[0];
			args[1]=methodname+args[1];
			args[2]=methodtype+args[2];
			args[3]=parameter+args[3];
			
			SpringApplication.run(SimpleApplication.class, args);
			logger.info("springboot启动成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("错误", e);
			System.exit(-1);
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("====begin====");
		System.out.println(Arrays.asList(args));
		String result = iMyService.test();
		System.out.println(result);
		System.out.println("====end====");
		System.exit(-1);
	}
}
