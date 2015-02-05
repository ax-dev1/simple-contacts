package com.demo.dao;

import java.util.List;

public interface IDao<E> {
	public E save(E e);
	
	public E get(int id);
	
	public void delete(int id);
	
	public E update(E e);
	
	public List<E> getAll();

}
