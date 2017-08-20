package com.anye.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jxkj.readapp.ioc.bean.UserInfo;
import com.jxkj.readapp.bean.CollectionBookBean;

import com.anye.greendao.gen.UserInfoDao;
import com.anye.greendao.gen.CollectionBookBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userInfoDaoConfig;
    private final DaoConfig collectionBookBeanDaoConfig;

    private final UserInfoDao userInfoDao;
    private final CollectionBookBeanDao collectionBookBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        collectionBookBeanDaoConfig = daoConfigMap.get(CollectionBookBeanDao.class).clone();
        collectionBookBeanDaoConfig.initIdentityScope(type);

        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);
        collectionBookBeanDao = new CollectionBookBeanDao(collectionBookBeanDaoConfig, this);

        registerDao(UserInfo.class, userInfoDao);
        registerDao(CollectionBookBean.class, collectionBookBeanDao);
    }
    
    public void clear() {
        userInfoDaoConfig.clearIdentityScope();
        collectionBookBeanDaoConfig.clearIdentityScope();
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

    public CollectionBookBeanDao getCollectionBookBeanDao() {
        return collectionBookBeanDao;
    }

}
