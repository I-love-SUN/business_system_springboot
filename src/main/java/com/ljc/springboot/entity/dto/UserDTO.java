package com.ljc.springboot.entity.dto;

import com.ljc.springboot.entity.SysMenu;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserDTO {
    private Integer uId;
    private String uName;
    private Integer roleId;
    private String password;
    private String roleName;
    private Integer permissionLevel;
    private  String token;
    private List<SysMenu> menus;
}
