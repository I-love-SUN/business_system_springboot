package com.ljc.springboot.service;

import com.ljc.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljc.springboot.entity.dto.UserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-02-01
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);
}
