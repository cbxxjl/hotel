package facade.hotelManager;

import dos.LoginUserDO;

/**
 * 酒店管理外部接口
 *
 * @author : cbxjl
 * @date : 2024/2/28 15:19
 */
public interface HotelManagerFacade {
    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return 用户
     */
    LoginUserDO getUserByAccount(String account);

    /**
     * 更新用户信息
     *
     * @param loginUserDO 用户信息
     */
    void updateById(LoginUserDO loginUserDO);

    /**
     * 根据id获取用户
     *
     * @param userId 用户id
     * @return LoginUserDO
     */
    LoginUserDO getUserById(Long userId);
}
