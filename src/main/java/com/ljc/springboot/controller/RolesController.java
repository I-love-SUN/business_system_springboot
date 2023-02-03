package com.ljc.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


import com.ljc.springboot.service.IRolesService;
import com.ljc.springboot.entity.Roles;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-02-02
 */
@RestController
@RequestMapping("//roles")
public class RolesController {

    @Resource
    private IRolesService rolesService;

    @PostMapping
    public Boolean save(@RequestBody Roles roles) {
            return rolesService.saveOrUpdate(roles);
            }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return rolesService.removeById(id);
            }


    @GetMapping
    public List<Roles> findAll() {
            return rolesService.list();
            }

    @GetMapping("/{id}")
    public Roles findOne(@PathVariable Integer id) {
            return rolesService.getById(id);
            }

    @DeleteMapping("del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
            return  rolesService.removeBatchByIds(ids);
            }

    @GetMapping("/page")
    public Page<Roles> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String username,
                                    @RequestParam(defaultValue = "") String email,
                                    @RequestParam(defaultValue = "") String address){
            QueryWrapper<Roles> queryWrapper = new QueryWrapper<>();
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
            return rolesService.page(new Page<>(pageNum, pageSize), queryWrapper);
            }

    }

