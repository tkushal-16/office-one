package com.spring.java.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.spring.java.common.id.IdBased;
import com.spring.java.common.id.UUIDBased;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;


public abstract class BaseData<I extends UUIDBased> extends IdBased<I> implements Serializable {

    private static final long serialVersionUID = 5422817607129962637L;
    public static final ObjectMapper mapper = new ObjectMapper();

    //    @Schema(
    //            description = "Entity creation timestamp in milliseconds since Unix epoch",
    //            example = "1746028547220",
    //            accessMode = Schema.AccessMode.READ_ONLY
    //    )
    @Getter
    @Setter
    protected long createdTime;

    public BaseData() {
        super();
    }

    public BaseData(I id) {
        super(id);
    }

    public BaseData(BaseData<I> data) {
        super(data.getId());
        this.createdTime = data.getCreatedTime();
    }

    public BaseData(UUID id) {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Long.hashCode(createdTime);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseData other = (BaseData) obj;
        return createdTime == other.createdTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseData [createdTime=");
        builder.append(createdTime);
        builder.append(", id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
