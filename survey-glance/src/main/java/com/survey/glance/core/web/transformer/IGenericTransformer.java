package com.survey.glance.core.web.transformer;

/**
 * @author Administrator
 *
 * @param <E>
 * @param <T>
 */
public interface IGenericTransformer<E, T> {

	/**
	 * @param e
	 * @return
	 */
	T transformEntityToTO(final E e);

	/**
	 * @param t
	 * @return
	 */
	E transformTOToEntity(final T t);

}
