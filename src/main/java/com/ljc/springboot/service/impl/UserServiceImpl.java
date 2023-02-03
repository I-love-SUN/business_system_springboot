package com.ljc.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljc.springboot.common.Constants;
import com.ljc.springboot.entity.SysMenu;
import com.ljc.springboot.entity.User;
import com.ljc.springboot.entity.dto.UserDTO;
import com.ljc.springboot.exception.ServiceException;
import com.ljc.springboot.mapper.RolemenuMapper;
import com.ljc.springboot.mapper.UserMapper;
import com.ljc.springboot.service.ISysMenuService;
import com.ljc.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-02-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Resource
    private RolemenuMapper rolemenuMapper;

    @Resource
    private ISysMenuService sysMenuService;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one!=null){
            BeanUtil.copyProperties(one,userDTO,true);

//            Integer roleid  =  one.getRoleId();
//            //设置用户的菜单列表
//            userDTO.setRoleId(roleid);
//
//            System.out.println("这里是"+roleid);
//
//            List<SysMenu> roleMenus = getRoleMenus(roleid);
//
//            System.out.println("这里是查询到的菜单表");
//            for(SysMenu sysMenu:roleMenus){
//                System.out.println(sysMenu.getId());
//            }
//            userDTO.setMenus(roleMenus);

            userDTO.setUId(one.getUId());
            return userDTO;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_name", userDTO.getUName());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);

        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    private  List<SysMenu> getRoleMenus( Integer roleId){

        System.out.println("这里是"+roleId);
        //当前角色所有菜单id的集合
//        List<Integer> menuIds = rolemenuMapper.selectByRoleId(roleId);

        //查出系统所有菜单
//        List<SysMenu>  menus = sysMenuService.findMenus("");

        List<SysMenu> roleMenus = new ArrayList<>();
        //筛选当前用户角色的菜单
//        for(SysMenu menu :menus){
//            if(menuIds.contains(menu.getId())){
//                roleMenus.add(menu);
//            }
//            List<SysMenu> children = menu.getChildren();
//            //removeIf() 移除children里不在menuIds集合中的元素
//            children.removeIf(child -> !menuIds.contains(child.getPid()));

//        }

        return  roleMenus;
    }
}
