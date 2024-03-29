package com.ljm.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.ljm.entity.SysUser;
import com.ljm.lang.Const;
import com.ljm.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

@RestController
public class AuthController extends BaseController{

	@Autowired
    Producer producer; //注入图片验证码生成器

	@GetMapping("/captcha")
	public Result captcha() throws IOException {
		String code = producer.createText();
		String key = UUID.randomUUID().toString();
		BufferedImage image = producer.createImage(code);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		BASE64Encoder encoder = new BASE64Encoder();
		String str = "data:image/jpeg;base64,";
		String base64Img = str + encoder.encode(outputStream.toByteArray());

		// 存储到redis key(Const.CAPTCHA_KEY) : hash(key,code)
		redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);

		return Result.ok(
				MapUtil.builder()
						.put("token", key)
						.put("captchaImg", base64Img)
						.build()

		);
	}

	/**
	 * 获取用户信息接口
	 * @param principal
	 * @return
	 */
	@GetMapping("/sys/userInfo")
	public Result userInfo(Principal principal) {

		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		return Result.ok(MapUtil.builder()
				.put("id", sysUser.getId())
				.put("username", sysUser.getUsername())
				.put("avatar", sysUser.getAvatar())
				.put("created", sysUser.getCreated())
				.map()
		);
	}


}
