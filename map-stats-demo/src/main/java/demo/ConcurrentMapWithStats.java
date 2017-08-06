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
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, null, ItemOperationType.RETRIEVE)));
		Object v = map.get(k);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, null, ItemOperationType.RETRIEVE)));
		return v;
	}
	
	public Object put(Object k, Object v) {
		if (map.containsKey(k)) {
			listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, v, ItemOperationType.UPDATE)));
			Object s = map.put(k, v);
			listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemOperationType.UPDATE)));
			return s;
		}
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, v, ItemOperationType.ADD)));
		Object s = map.put(k, v);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemOperationType.ADD)));
		return s;
	}
	
	public Object remove(Object k) {
		listeners.stream().forEach(listener -> listener.before(new ItemEvent(k, null, ItemOperationType.REMOVE)));
		Object s = map.remove(k);
		listeners.stream().forEach(listener -> listener.after(new ItemEvent(k, s, ItemOperationType.REMOVE)));
		return s;
	}

}
