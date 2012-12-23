package com.nelsonjrodrigues.twitter.data.model.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.style.ToStringCreator;

public class BaseDomain {

	private UUID id = UUID.randomUUID();

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseDomain other = (BaseDomain) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		ToStringCreator creator = new ToStringCreator(this);

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(getClass());

			for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
				String name = desc.getName();
				Object result = null;

				if (Collection.class.isAssignableFrom(desc.getPropertyType())) {
					result = "[collection]";
				} else if (Map.class.isAssignableFrom(desc.getPropertyType())) {
					result = "[map]";
				} else {
					Object invocationResult = desc.getReadMethod().invoke(this);
					if (BaseDomain.class.isAssignableFrom(desc.getPropertyType()) && invocationResult != null) {
						result = "" + desc.getPropertyType().getName() + " - " + ((BaseDomain) invocationResult).getId();
					} else {
						if (invocationResult == null) {
							result = "null";
						} else {
							result = invocationResult;
						}
					}
				}
				creator.append(name, result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return creator.toString();
	}

}
