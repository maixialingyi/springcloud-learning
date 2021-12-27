package com.mid.zxadapter.request.zxadapter.api.request.aliyun;

import com.mid.zxadapter.api.request.zxadapter.api.request.AbstractRequest;
import com.mid.zxadapter.api.request.zxadapter.api.request.enums.ActionAlias;
import com.mid.zxadapter.api.request.zxadapter.api.request.enums.ServiceAlias;
import com.mid.zxadapter.api.request.zxadapter.api.request.enums.SupplierAlias;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EcsCreateRequest extends AbstractRequest {

    public EcsCreateRequest() {
        super(SupplierAlias.aliyun, ServiceAlias.ecs, ActionAlias.create);
    }

    /**
     * 规格
     */
    private String instanceType;

    /**
     * 系统磁盘
     */
    private String systemDisk;
}
