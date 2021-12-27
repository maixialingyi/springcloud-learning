package com.mid.base.gc.MetaSpaceTest;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 *
 */
public class MetaSpaceOOM {

    public static void main(String[] args) {
        for(;;){
            User user = new User().setId("123").setName("1231");

            MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
            mapperFactory.classMap(User.class, UserVo.class)
                    .field("id", "userId")
                    .field("name", "userName")
                    .byDefault().register();
            UserVo userVo = mapperFactory.getMapperFacade().map(user, UserVo.class);
        }
    }
}
