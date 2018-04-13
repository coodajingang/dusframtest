package com.dus.dusframework.common;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 运行时异常封装类，  将用于国际化错误信息处理  
 * @author lenovo
 *
 */
public class CommonRuntimeException extends RuntimeException{
	private static final long serialVersionUID = 6326450420472654286L;

	private static final Logger log = LoggerFactory.getLogger(CommonRuntimeException.class);

	public static final String log001 = "无法找到异常信息，code={0}，locale={1}";

	private String code;
	private Object[] params;
	private List<Locale> locales;

	public CommonRuntimeException(String code, Throwable e,
			List<Locale> locales, Object... params) {
		super(e);
		this.code = code;
		this.params = params;
		this.locales = locales;
	}

	public CommonRuntimeException(String code, Throwable e, Object... params) {
		this(code, e, null, params);
	}

	public CommonRuntimeException(String code, Object... params) {
		this(code, null, params);
	}

	public CommonRuntimeException(Throwable e) {
		this(null, e);
	}

	public CommonRuntimeException() {
		this(null,null);
	}

	@Override
	public String getMessage() {
		return assembleMessage(getLocalizedExceptionMessage(null));
	}

	@Override
	public String getLocalizedMessage() {
		if (CollectionUtils.isEmpty(locales)) {
			return getMessage();
		}

		String exceptionMessage = null;
		for (Locale locale : locales) {
			if (locale != null) {
				exceptionMessage = getLocalizedExceptionMessage(locale);
				if (StringUtils.hasText(exceptionMessage)) {
					return assembleMessage(exceptionMessage);
				}
			}
		}

		return getMessage();
	}

	/**
	 * 根据语言国家信息获取本地异常信息
	 * <p>
	 * 
	 * @param locale
	 *            语言国家信息
	 * @return 本地异常信息
	 */
	public String getLocalizedExceptionMessage(Locale locale) {
		if (!StringUtils.hasText(getCode())) {
			return null;
		}

		// TODO 
		/**
		String exceptionMessage = null;
		try {
			exceptionMessage = I18n.getMessage(getCode(), getParameters(),
					locale);
		} catch (NoSuchMessageException e) {
			log.debug("log001", e, getCode(), locale);
		}

		return exceptionMessage;
		**/
		return "";
	}

	private String assembleMessage(String exceptionMessage) {
		
		String msg = "";
		
		if (this.params != null) {
			for (Object obj : this.params) {
				msg += obj.toString();
			}
		}
		
		return new StringBuilder("[ERRORCODE=")
				.append(getCode())
				.append("] [")
				.append(StringUtils.hasText(exceptionMessage) ? exceptionMessage
						: msg).append(']').toString();
	}


	public String getCode() {
		return code;
	}


	public Object[] getParameters() {
		return params;
	}

	public List<Locale> getLocales() {
		return locales;
	}

	public void setLocales(List<Locale> locales) {
		this.locales = locales;
	}

	
}
