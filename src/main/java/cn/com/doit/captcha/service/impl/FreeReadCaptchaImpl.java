package cn.com.doit.captcha.service.impl;

import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.com.doit.captcha.provider.xvcode.xvcode.generator.Generator;
import cn.com.doit.captcha.provider.xvcode.xvcode.generator.GifVCGenerator;
import cn.com.doit.captcha.provider.xvcode.xvcode.generator.PngVCGenerator;
import cn.com.doit.captcha.service.FreeReadCaptcha;

@Component(value = "freeReadCaptcha")
public class FreeReadCaptchaImpl implements FreeReadCaptcha {
	@Resource(name = "pngVCGenerator")
	private PngVCGenerator pngVCGenerator;
	@Resource(name = "gifVCGenerator")
	private GifVCGenerator gifVCGenerator;
	private Generator generator;

	public String getImage(String var, int leavel, OutputStream out) {
		char[] strs = null;
		if (leavel == 1) {
			generator = gifVCGenerator;
		} else {
			generator = pngVCGenerator;
		}
		if (var == null || "".equals(var)) {
			strs = generator.alphas();
		} else {
			generator.setLen(var.length());
			strs = var.toCharArray();
		}
		generator.write2out(out, strs);
		return strs.toString();
	}

	public Map<String, Object> getImageWithMath(float num1, float num2,
			int var, int leavel) {
		return null;
	}

	public PngVCGenerator getPngVCGenerator() {
		return pngVCGenerator;
	}

	public void setPngVCGenerator(PngVCGenerator pngVCGenerator) {
		this.pngVCGenerator = pngVCGenerator;
	}

	public GifVCGenerator getGifVCGenerator() {
		return gifVCGenerator;
	}

	public void setGifVCGenerator(GifVCGenerator gifVCGenerator) {
		this.gifVCGenerator = gifVCGenerator;
	}

	public Generator getGenerator() {
		return generator;
	}

	public void setGenerator(Generator generator) {
		this.generator = generator;
	}

}
