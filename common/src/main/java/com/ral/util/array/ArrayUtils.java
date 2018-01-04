package com.ral.util.array;

import java.util.List;

public final class ArrayUtils {

	private ArrayUtils (){
		
	}
	
	/**
	 * 转String数组 实际上是ValueOf方法
	 * @param list
	 * @return
	 */
	public static <T> String[] toArray(List<T> list){
		if(list == null || list.isEmpty()){
			return new String[0];
		}
		String [] array = new String[list.size()];
		int index = 0;
		for(T item : list){
			if(item != null ){
				array[index] = item.toString();
				index ++ ;
			}
		}
		return array;
	}
}
