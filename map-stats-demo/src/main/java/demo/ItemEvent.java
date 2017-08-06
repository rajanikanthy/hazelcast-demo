package demo;

import java.io.Serializable;

public class ItemEvent implements Serializable {
	private Object key;
	private Object value;
	private ItemOperationType itemType;
	
	public ItemEvent(Object key, Object value, ItemOperationType itemType) {
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
	public ItemOperationType getItemType() {
		return itemType;
	}
	
}
