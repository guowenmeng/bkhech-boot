package org.bkhech.mybatis.extend.generic.model;

import java.io.Serializable;

/**
 * BasePrimaryKeyModel
 *
 * Created by guowm on 18-5-14.
 */
public class BasePrimaryKeyModel<PK extends Serializable> extends BaseModel<PK> {

    private static final long serialVersionUID = -123372934662870964L;

    protected PK id;

    public BasePrimaryKeyModel() {
    }

    public BasePrimaryKeyModel(PK id) {
        this.id = id;
    }

    public PK getId() {
        return this.id;
    }

    public void setId(PK id) {
        this.id = id;
    }
    
}
