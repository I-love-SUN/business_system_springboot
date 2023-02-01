package com.ljc.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


import com.ljc.springboot.service.ISysMenuService;
import com.ljc.springboot.entity.SysMenu;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-01-31
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;

    @PostMapping
    public Boolean save(@RequestBody SysMenu sysMenu) {
            return sysMenuService.saveOrUpdate(sysMenu);
            }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return sysMenuService.removeById(id);
            }


    @GetMapping
    public List<SysMenu> findAll() {
            return sysMenuService.list();
            }

    @GetMapping("/{id}")
    public SysMenu findOne(@PathVariable Integer id) {
            return sysMenuService.getById(id);
            }

    @DeleteMapping("del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
            return  sysMenuService.removeBatchByIds(ids);
            }

    @GetMapping("/page")
    public Page<SysMenu> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String username,
                                    @RequestParam(defaultValue = "") String email,
                                    @RequestParam(defaultValue = "") String address){
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
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
            return sysMenuService.page(new Page<>(pageNum, pageSize), queryWrapper);
            }

    }

