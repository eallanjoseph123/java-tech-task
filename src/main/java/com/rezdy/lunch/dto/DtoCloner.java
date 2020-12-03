package com.rezdy.lunch.dto;

public interface DtoCloner<T> {
	
	void copy(final T t);
}
