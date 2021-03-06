package com.zjmzxfzhl.common.permission.provider;

import com.zjmzxfzhl.common.permission.wrapper.PermissionWrapper;

/**
 * 空的DataPermissionProvider，可以用于测试
 * 
 * @author 庄金明
 *
 */
public class NullDataPermissionProvider extends AbstractDataPermissionProvider {
    @Override
    public PermissionWrapper wrap(PermissionWrapper permissionWrapper) {
        return permissionWrapper;
    }
}
