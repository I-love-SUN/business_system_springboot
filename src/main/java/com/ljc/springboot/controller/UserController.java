package com.ljc.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljc.springboot.common.Constants;
import com.ljc.springboot.common.Result;
import com.ljc.springboot.entity.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import java.util.List;


import com.ljc.springboot.service.IUserService;
import com.ljc.springboot.entity.User;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-02-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Boolean save(@RequestBody User user) {
            return userService.saveOrUpdate(user);
            }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUName();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);

        return Result.success(dto);
    }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return userService.removeById(id);
            }


    @GetMapping
    public List<User> findAll() {
            return userService.list();
            }

    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
            return userService.getById(id);
            }

    @DeleteMapping("del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
            return  userService.removeBatchByIds(ids);
            }

    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String username,
                                    @RequestParam(defaultValue = "") String email,
                                    @RequestParam(defaultValue = "") String address){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if(!"".equals(username)){
            queryWrapper.like("username" ,username);
            }
            if(!"".equals(email)){
            queryWrapper.like("nickname",email);
            }
            if(!"".equals(address)){
            queryWrapper.like("address",address);
            }

                queryWrapper.orderByDesc("id");
            return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
            }

    }

