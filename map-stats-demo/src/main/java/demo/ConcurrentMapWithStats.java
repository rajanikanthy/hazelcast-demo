package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapWithStats {
	
	private ConcurrentHashMap map;
	private List<ItemListener> listeners;
	
	public ConcurrentMapWithStats() {
		map = new ConcurrentHashMap();
		listeners = new ArrayList<ItemListener>();
	}
	
	public void registerItemListener(ItemListener itemListener) {
		if (itemListener != null) {
			listeners.add(itemListener);
		}
	}
	
	public void unregisterItemListener(ItemListener itemListener) {
		listeners.remove(itemListener);
	}
	
	public Object get(Object k) {
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, null, ItemType.RETRIEVE)));
		Object v = map.get(k);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, null, ItemType.RETRIEVE)));
		return v;
	}
	
	public Object put(Object k, Object v) {
		if (map.containsKey(k)) {
			listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, v, ItemType.UPDATE)));
			Object s = map.put(k, v);
			listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemType.UPDATE)));
			return s;
		}
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, v, ItemType.ADD)));
		Object s = map.put(k, v);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemType.ADD)));
		return s;
	}
	
	public Object remove(Object k) {
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, null, ItemType.REMOVE)));
		Object s = map.remove(k);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemType.REMOVE)));
		return s;
	}

}
