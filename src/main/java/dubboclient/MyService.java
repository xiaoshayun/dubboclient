package dubboclient;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
@Service
public class MyService implements IMyService{
	private final static Logger logger = LoggerFactory.getLogger(MyService.class);
	@Autowired(required = false)
	Environment env;
	@Autowired
	@Qualifier("myclient")
	Object service;
	@Override
	public String test() {
		Object result = null ;
		try {
			String methodname = env.getProperty("methodname");//方法名
			String methodtypeStr= env.getProperty("methodtype");//参数类型
			String parameter= env.getProperty("parameter");//参数类型
			
			
			String[] methdtypeStrs = methodtypeStr.split("~");
			Class<?>[] methodtype = new Class<?>[methdtypeStrs.length];
			
			
			String[] parameters = parameter.split("~");
			Object[] targetParameters = new Object[parameters.length];
			
			for(int i=0;i<methdtypeStrs.length;i++){
				String type = methdtypeStrs[i];
				parameter= parameters[i];
				
				if(type.equalsIgnoreCase("string")){
					methodtype[i] = java.lang.String.class;
					targetParameters[i] = parameter;
				}else if(type.equalsIgnoreCase("map")){
					methodtype[i] = java.util.Map.class;
					targetParameters[i] = JsonUtil.buildMap(parameter);
				}else if(type.equalsIgnoreCase("int")){
					methodtype[i] = int.class;
					targetParameters[i] = Integer.parseInt(parameter);
				}else{
					methodtype[i] = Class.forName(type);
					targetParameters[i] = JsonUtil.buildObject(parameter, Class.forName(type));				}
			}			result =  ReflectUtils.invokeMethodName(service, methodname, methodtype, targetParameters);
			if (result!=null && result instanceof Serializable) {
				result = JsonUtil.buildJson(result);
			}
		} catch (ClassNotFoundException e) {
			logger.error("出现错误:",e);
			e.printStackTrace();
		}
		return result!=null?result.toString():null;
	}

	
	public String test2(String a,String b,int inta,int intb) {
		return a+b+inta+intb;
	}
	
	public String test3(String a,String b) {
		return a+b;
	}
	
	public String test4(String a) {
		return a;
	}
	
	public String test5(int a) {
		return a+"";
	}
}
