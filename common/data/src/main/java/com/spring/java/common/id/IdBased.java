package com.spring.java.common.id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class IdBased<I extends UUIDBased> implements HasId<I> {
	
	protected I id;
	
	public IdBased() {
		super();
	}
	
	public IdBased(I id) {
		super();
		this.id = id;
	}

    @JsonSetter
	public void setId(I id) {
		this.id = id;
	}

    @JsonIgnore
	public UUID getUuidId() {
		if (id != null) {
			return id.getId();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdBased other = (IdBased) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
