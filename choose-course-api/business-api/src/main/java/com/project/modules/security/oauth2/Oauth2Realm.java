

package com.project.modules.security.oauth2;

import com.project.modules.security.entity.SysUserTokenEntity;
import com.project.modules.security.user.UserDetail;
import com.project.modules.sys.entity.SysUserEntity;
import com.project.common.exception.ErrorCode;
import com.project.common.utils.ConvertUtils;
import com.project.common.utils.MessageUtils;
import com.project.modules.security.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 *
 *
 *
 */
@Component
public class Oauth2Realm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Oauth2Token;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserDetail user = (UserDetail)principals.getPrimaryPrincipal();

        //User Rights List
        Set<String> permsSet = shiroService.getUserPermissions(user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     *
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //Query user information based on accessToken
        SysUserTokenEntity tokenEntity = shiroService.getByToken(accessToken);
        //Token fails
        if(tokenEntity == null || tokenEntity.getExpireDate().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException(MessageUtils.getMessage(ErrorCode.TOKEN_INVALID));
        }

        //Querying User Information
        SysUserEntity userEntity = shiroService.getUser(tokenEntity.getUserId());

        //Convert to a UserDetail object
        UserDetail userDetail = ConvertUtils.sourceToTarget(userEntity, UserDetail.class);

        //Obtain the department data permission of the user
        List<Long> deptIdList = shiroService.getDataScopeList(userDetail.getId());
        userDetail.setDeptIdList(deptIdList);


        if(userDetail.getStatus() == 0){
            throw new LockedAccountException(MessageUtils.getMessage(ErrorCode.ACCOUNT_LOCK));
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDetail, accessToken, getName());
        return info;
    }

}