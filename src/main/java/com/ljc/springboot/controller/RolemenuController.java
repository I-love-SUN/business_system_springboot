package com.ljc.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


import com.ljc.springboot.service.IRolemenuService;
import com.ljc.springboot.entity.Rolemenu;


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
@RequestMapping("//rolemenu")
public class RolemenuController {

    @Resource
    private IRolemenuService rolemenuService;

    @PostMapping
    public Boolean save(@RequestBody Rolemenu rolemenu) {
            return rolemenuService.saveOrUpdate(rolemenu);
            }


    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
            return rolemenuService.removeById(id);
            }


    @GetMapping
    public List<Rolemenu> findAll() {
            return rolemenuService.list();
            }

    @GetMapping("/{id}")
    public Rolemenu findOne(@PathVariable Integer id) {
            return rolemenuService.getById(id);
            }

    @DeleteMapping("del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
            return  rolemenuService.removeBatchByIds(ids);
            }

    @GetMapping("/page")
    public Page<Rolemenu> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String username,
                                    @RequestParam(defaultValue = "") String email,
                                    @RequestParam(defaultValue = "") String address){
            QueryWrapper<Rolemenu> queryWrapper = new QueryWrapper<>();
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
            return rolemenuService.page(new Page<>(pageNum, pageSize), queryWrapper);
            }

    }

