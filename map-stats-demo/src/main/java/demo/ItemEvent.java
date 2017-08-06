package demo;

import java.io.Serializable;

public class ItemEvent implements Serializable {
	private Object key;
	private Object value;
	private ItemType itemType;
	
	public ItemEvent(Object key, Object value, ItemType itemType) {
		this.key = key;
		this.value = value;
		this.itemType = itemType;
	}
	
	public Object getKey() {
		return key;
	}
	public Object getValue() {
		return value;
	}
	public ItemType getItemType() {
		return itemType;
	}
	
}
