package com.dus.dusframework.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import com.dus.dusframework.common.CommonRuntimeException;

@Component
public class DataBindingFactory {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(DataBindingFactory.class);
	
	private static Map<String, JAXBContext> jaxb_cache = new HashMap<String, JAXBContext>();
	private static Map<String, File> xsdfile_cache = new HashMap<String, File>();
	
	private static boolean lazyLoad = true;

	private static final String XSD_PATH = "classpath:schema/";
	
	static {
		if (!lazyLoad) {
			// 循环读取 xsd文件列表  ， 对于每个交易码 ， 拼接器对应的包类， 生成对应的jaxbcontext 
			// 缓存 xsd文件 
			loadAllJaxbContexts();
		}

	}
	
	/**
	 * 根据交易码 获取对应的unmarshaller ，  beps.121.001.01 
	 * @param txnCode
	 * @return
	 * @throws JAXBException 
	 * @throws SAXException 
	 */
	public static Unmarshaller getUnmarshallerInstance(String txnCode) throws JAXBException, SAXException {
		JAXBContext context = getJaxbContext(txnCode);
		Unmarshaller unm = context.createUnmarshaller();
		
		unm.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
				getXsdFile(txnCode)));
		
		return unm;
	}
	
	/**
	 * 根据交易码 获取对应的marshaller ，  beps.121.001.01 
	 * @param txnCode
	 * @return
	 * @throws JAXBException 
	 * @throws SAXException 
	 */
	public static Marshaller getMarshallerInstance(String txnCode) throws JAXBException, SAXException {
		JAXBContext context = getJaxbContext(txnCode);
		Marshaller mar = context.createMarshaller();
		
		mar.setSchema(SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
				getXsdFile(txnCode)));
		mar.setProperty(Marshaller.JAXB_ENCODING, "utf-8");  
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); 
		return mar;
	}
	
	/**
	 * 根据交易码获取对应的xsd文件  ， 如beps.121.001.01 ==> beps.121.001.01.xsd
	 * @param txnCode
	 * @return
	 */
	public static File getXsdFile(String txnCode) {
		File file = null;
		String key = genKeyFromTxnCode(txnCode);
		
		log.debug("获取交易码对应的xsdfile： " + txnCode);
		
		file = xsdfile_cache.get(key);
		
		if (file != null) {
			return file;
		}
		
		String path = XSD_PATH + ComUtils.trims(txnCode) + ".xsd";
		try {
			file = ResourceUtils.getFile(path);

		} catch (FileNotFoundException e) {
			log.error("读取配置xsd文件异常！" + path);
			e.printStackTrace();
			throw new CommonRuntimeException("", "读取配置xsd文件异常！" + path);
		}
		
		return loadXsdFileCacheOne(key, file);
	}
	
	/**
	 * 输入的key是 交易码 ：　beps.121.001.01 
	 * @param txnCode
	 * @return
	 */
	public static JAXBContext getJaxbContext(String txnCode) {
		JAXBContext context = null;
		
		String key = genKeyFromTxnCode(txnCode);
		
		log.debug("获取交易码对应的jaxbcontext： " + txnCode);
		
		context = jaxb_cache.get(key);
		
		if (context != null) {
			return context;
		}
		
		// 尝试加载key对应的jaxbcontext 
		// 
		context = loadJaxbContextOne(key);
		
		if (context == null) {
			log.error("没有匹配到交易对应的jaxbcontext ， 检查！" + txnCode + " " + key);
			throw new CommonRuntimeException("", "未匹配到jaxb上下文！" + txnCode);
		}
		return context;
	}
	
	private static void loadAllJaxbContexts() {
		File f = null;
		try {
			f = ResourceUtils.getFile(XSD_PATH);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.error("读取配置文件目录异常！" + XSD_PATH);
			e.printStackTrace();
			throw new CommonRuntimeException("", "读取XSD文件目录异常！");
		}
		
		File[] fs = f.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return ComUtils.endWith(name, ".xsd");
			}
		});
		
		if (fs == null || fs.length == 0) {
			log.error("读取到xsd文件为空，异常");
			throw new CommonRuntimeException("", "读取到xsd文件为空异常！");
		}
		log.info("读取到xsd文件条数：" + fs.length);
		
		for (File file : fs) {
			String fname = file.getName();
			log.debug("处理文件：" + fname);
			if (ComUtils.isEmpty(fname)) {
				continue;
			}
			
			String key  = genKeyFromTxnCode(fname);
			loadJaxbContextOne(key);
			loadXsdFileCacheOne(key, file);
		}
		
		log.debug("当前jaxbcache数量：" + jaxb_cache.size());
		
	}
	
	private static File loadXsdFileCacheOne(String key , File file) {
		File oldfile = xsdfile_cache.get(key);
		
		if (oldfile == null) {
			synchronized (xsdfile_cache) {
				oldfile = xsdfile_cache.get(key);
				if (oldfile != null) {
					return oldfile;
				}
				oldfile = file;
				xsdfile_cache.put(key, file);
			}
		}
		return oldfile;
	}

	private static JAXBContext loadJaxbContextOne(String key){
		// like com.gen.beps12100101
		String packageName = key;
		
		JAXBContext context = null;
		
		context = jaxb_cache.get(packageName);
		
		if (context == null) {
			synchronized (jaxb_cache) {
				context = jaxb_cache.get(packageName);
				if (context != null) {
					return context;
				}
				try {
					context  = JAXBContext.newInstance(packageName);
				} catch (JAXBException e) {
					log.error("生成jaxbContext异常 ：" + key  , e );
				}
				jaxb_cache.put(packageName, context);
			}
		}
		return context;
	}
	
	/**
	 * 处理交易码 beps.121.001.01.xsd  ==> beps12100101
	 *  或者  beps.121.001.01 ==>  beps12100101 
	 * @param key
	 * @return
	 */
	public static String keyWithoutDot(String key) {
		key = ComUtils.trims(key);
		if (ComUtils.isEmpty(key)) {
			return "";
		}
		
		if (ComUtils.endWith(key, ".xsd")) {
			key = key.substring(0, key.lastIndexOf("."));
		}
		
		return ComUtils.join(ComUtils.split(key, "\\."), "");
	}
	
	/**
	 * 输入交易码 beps.121.001.01  得到包名 com.gen.beps12100101
	 * @param txnCode
	 * @return
	 */
	public static String genKeyFromTxnCode(String txnCode) {
		return "com.gen." + keyWithoutDot(txnCode);
	}
	
	public static String[] outputkeys() {
		if (jaxb_cache == null) {
			return null;
		}
		
		return null;
	}
	
}
