/**
 * 
 */
package it.fuesi.kidslearn.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pascotto
 *
 */
public class FlipperItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8264317507873218330L;

	private int id;
	private int label;
	private String name;
	
	public FlipperItem(String name){
		this.name = name;
	}
	
	public FlipperItem(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}
	
	
	public static List<FlipperItem> arrayFactory(String[] array){
		final List<FlipperItem> list = new LinkedList<FlipperItem>();
		
		for(String x : array){
			list.add(new FlipperItem(x));
		}
		
		return list;
	}
	
	public static List<FlipperItem> arrayFactory(int[] array){
		final List<FlipperItem> list = new LinkedList<FlipperItem>();
		
		for(int x : array){
			list.add(new FlipperItem(x));
		}
		
		return list;
	}
	
	public static List<FlipperItem> arrayFactory(int[] array, int[] labels){
		final List<FlipperItem> list = arrayFactory(array);
		
		for(int i=0; i<labels.length; i++){
			list.get(i).setLabel(labels[i]);
		}
		
		return list;
		
	}
	
}
