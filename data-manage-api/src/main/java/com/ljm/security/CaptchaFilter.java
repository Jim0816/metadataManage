package com.ljm.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ljm.exception.CaptchaException;
import com.ljm.lang.Const;
import com.ljm.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description  图片验证码过滤器
 * @return
 * @exception
 * @author Jim
 * @date 2022/3/30 15:53
 **/
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	LoginFailureHandler loginFailureHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

		String url = httpServletRequest.getRequestURI();
		// 不是所有请求都需要校验验证码，只有login操作时需要
		if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
			try{
				// 校验验证码
				validate(httpServletRequest);
			} catch (CaptchaException e) {
				// 交给认证失败处理器
				loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
			}
		}

		// 验证码校验通过，请求继续往后走
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	// 校验验证码逻辑
	private void validate(HttpServletRequest httpServletRequest) {

		String code = httpServletRequest.getParameter("code");
		String key = httpServletRequest.getParameter("token");

		if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
			throw new CaptchaException("验证码错误");
		}

		// 比较用户输入的code是否等于缓存的code
		if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, key))) {
			throw new CaptchaException("验证码错误");
		}

		// 一次性使用 (比对完就需要删除)
		redisUtil.hdel(Const.CAPTCHA_KEY, key);
	}
}
