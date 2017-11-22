package molab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import molab.db.dao.UserDao;
import molab.db.entity.TUser;
import molab.util.Status;
import weixin.popular.api.ShakeAroundAPI;
import weixin.popular.api.UserAPI;
import weixin.popular.bean.shakearound.user.getshakeinfo.UserGetShakeInfoResult;
import weixin.popular.bean.shakearound.user.getshakeinfo.UserGetShakeInfoResultData;
import weixin.popular.bean.user.User;
import weixin.popular.support.TokenManager;

@Service
public class IndexService {
	
	@Autowired
	private UserDao ud;
	
	public UserGetShakeInfoResultData shake(String ticket) {
		JSONObject param = new JSONObject();
		param.put("ticket", ticket);
		param.put("need_poi", 1);
		String postJson = param.toJSONString();
		
		UserGetShakeInfoResult result = ShakeAroundAPI.userGetShakeInfo(TokenManager.getDefaultToken(), postJson);
		if(result != null && "0".equals(result.getErrcode())) { // 返回成功
			UserGetShakeInfoResultData data = result.getData();
			if(data != null) { // 存在设备及用户信息
				String openId = data.getOpenId();
				User user = UserAPI.userInfo(TokenManager.getDefaultToken(), openId); // 获取用户信息
				if(user.getSubscribe() != Status.Common.NO.getInt()) { // 用户已关注公众号
					TUser tuser = ud.findByOpenid(openId);
					if(tuser == null) { // 如果数据库中没有用户信息，创建并返回新用户
						tuser = addUser(user);
					}
				}
				return data;
			}
		}
		return null;
	}
	
	private TUser addUser(User user) {
		TUser u = new TUser();
		u.setOpenid(user.getOpenid());
		u.setAmount(0f);
		// TODO set other attributes
		ud.save(u);
		return u;
	}
	
}
