package com.zjf.babasports.contorller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjf.babasports.pojo.Buyer;
import com.zjf.babasports.service.BuyerService;
import com.zjf.babasports.service.SessionService;
import com.zjf.babasports.utils.EncryptUtil;
import com.zjf.babasports.utils.RequestUtils;

@Controller
@RequestMapping("/login")
public class LoginContorller {
	@Autowired
	private BuyerService buyerService;
	@Autowired
	private SessionService sessionService;
	/**
	 * 去登陆页面
	 * @param resultURL
	 * @param model
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin(String resultURL,Model model){
		model.addAttribute("resultURL", resultURL);
		return "login";
	}
	@RequestMapping("/login")
	public String login(Buyer buyer,String resultURL,Model model
			,HttpServletRequest request,HttpServletResponse response){
		if(buyer.getUsername()!=null){
			if(buyer.getPassword()!=null){
				Buyer user = buyerService.selectBuyerByUserName(buyer);
				if(user!=null){
					if(user.getPassword().equals(EncryptUtil.getPasswordByEncrypt(buyer.getPassword()))){
						//dosomthing
						//传入本次回话的request,response找到对应的sessionid
						String CSessionID = RequestUtils.getCSessionID(request, response);
						//根据csessionid将session存入redis并设置对应存活时间
						sessionService.setAttributeForUsername(CSessionID, user.getUsername());
						return "redirect:"+resultURL;
					}else{
						model.addAttribute("error", "密码错误");
					}
				}else{
					model.addAttribute("error", "用户名错误");
				}
			}else{
				model.addAttribute("error","密码不能为空");
			}
		}else{
			model.addAttribute("error","用户名不能为空");
		}
		//登陆失败
		model.addAttribute("resultURL", resultURL);
		return "login";
	}

}
